package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.Util.Utils;
import com.example.demo.conversion.Conversions;
import com.example.demo.entity.AvionEntity;
import com.example.demo.entity.PilotoEntity;
import com.example.demo.entity.VueloEntity;
import com.example.demo.repository.AvionRepository;
import com.example.demo.repository.PilotoRepository;
import com.example.demo.repository.VueloRepository;
import com.example.demo.request.VueloRequest;
import com.example.demo.response.PilotoResponse;
import com.example.demo.response.ResponseBase;
import com.example.demo.response.VueloResponse;
import com.example.demo.service.RedisService;
import com.example.demo.service.VueloService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {
    private final VueloRepository vueloRepository;
    private final AvionRepository avionRepository;
    private final PilotoRepository pilotoRepository;
    private final RedisService redisService;

    public VueloServiceImpl(VueloRepository vueloRepository, AvionRepository avionRepository, PilotoRepository pilotoRepository, RedisService redisService) {
        this.vueloRepository = vueloRepository;
        this.avionRepository = avionRepository;
        this.pilotoRepository = pilotoRepository;
        this.redisService = redisService;
    }

    @Override
    public ResponseBase<VueloResponse> create(VueloRequest vueloRequest) {
        Optional<VueloEntity> vueloEntityOptional = getVueloEntity(vueloRequest);
        
        if(vueloEntityOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        
        VueloEntity vueloEntity = vueloEntityOptional.get();
        
        VueloEntity vueloEntitySaved = vueloRepository.save(vueloEntity);
        
        VueloResponse vueloResponse = Conversions.entityToVueloResponse(vueloEntitySaved);
        return new ResponseBase<>(Constants.CODE_CREATED,
                Constants.MESSAGE_SUCCESFULL,
                Optional.of(vueloResponse));
    }

    @Override
    public ResponseBase<VueloResponse> findByID(int id) {
        Optional<VueloEntity> vueloEntityOptional = vueloRepository.findById(id);
        if (vueloEntityOptional.isEmpty()) {
            return new ResponseBase<>(
                    Constants.CODE_NOT_FOUND,
                    Constants.MESSAGE_NOT_FOUND,
                    Optional.empty());
        }
            VueloEntity vueloEntity = vueloEntityOptional.get();
            VueloResponse vueloResponse = Conversions.entityToVueloResponse(vueloEntity);
            return new ResponseBase<>(Constants.CODE_SUCCESFULL, Constants.MESSAGE_FIND, Optional.of(vueloResponse));
    }

    @Override
    public ResponseBase<List<VueloResponse>> findByFechaOrigen(String fecha) {
        String responseRedis = redisService.getValueByKey(fecha);
        if(responseRedis != null && !responseRedis.isEmpty()) {
            return Utils.convertFromJson(responseRedis);
        }
        List<VueloEntity> vueloEntities = vueloRepository.findByFechaSalidaAfter(fecha);
        List<VueloResponse> vueloResponses = new ArrayList<>();
        
        for(VueloEntity vuelo: vueloEntities) {
            VueloResponse vueloResponse = Conversions.entityToVueloResponse(vuelo);
            vueloResponses.add(vueloResponse);
        }
        ResponseBase<List<VueloResponse>> responseBase =  new ResponseBase<>(
                Constants.CODE_SUCCESFULL,
                Constants.MESSAGE_FIND,
                Optional.of(vueloResponses));


        String responseBaseJson = Utils.convertToJson(responseBase);
        redisService.saveKeyValue(fecha, responseBaseJson, 5);
        return responseBase;
    }

    @Override
    public ResponseBase<VueloResponse> updateById(List<Integer> ids, int idVuelo) {
        VueloEntity vueloEntity = vueloRepository.findById(idVuelo).orElse(null);

        if(vueloEntity == null) {
            return new ResponseBase<>(
                    Constants.CODE_NOT_FOUND,
                    Constants.MESSAGE_NOT_FOUND,
                    Optional.empty());
        }
        List<PilotoEntity> pilotoEntities = new ArrayList<>();

        for(Integer id: ids) {
            PilotoEntity pilotoEntity = pilotoRepository.findById(id).orElse(null);
            if(pilotoEntity == null) {
                return new ResponseBase<>(
                        Constants.CODE_NOT_FOUND,
                        Constants.MESSAGE_NOT_FOUND,
                        Optional.empty()
                );
            }
            pilotoEntities.add(pilotoEntity);
        }

        vueloEntity.setPilotoEntities(pilotoEntities);
        vueloRepository.save(vueloEntity);

        VueloResponse vueloResponse = Conversions.entityToVueloResponse(vueloEntity);

        return new ResponseBase<>(
                Constants.CODE_UPDATED,
                Constants.MESSAGE_SUCCESFULL_UPDATE,
                Optional.of(vueloResponse));
    }

    private Optional<VueloEntity> getVueloEntity(VueloRequest vueloRequest) {
        Optional<AvionEntity> avionOptional = avionRepository.findById(vueloRequest.getIdAvion());
        if(avionOptional.isEmpty()) {
            return Optional.empty();
        }
        
        List<PilotoEntity> pilotoEntities = new ArrayList<>();
        for (int id : vueloRequest.getPilotos()) {
            PilotoEntity pilotoEntity = pilotoRepository.findById(id).orElse(null);
            if (pilotoEntity == null) {
                return Optional.empty();
            }
            pilotoEntities.add(pilotoEntity);
        }
        
        VueloEntity vueloEntity = new VueloEntity();
        vueloEntity.setFechaSalida(vueloRequest.getFechaSalida());
        vueloEntity.setFechaLlegada(vueloRequest.getFechaLlegada());
        vueloEntity.setOrigen(vueloRequest.getOrigen());
        vueloEntity.setDestino(vueloRequest.getDestino());
        vueloEntity.setAvionEntity(avionOptional.get());
        vueloEntity.setPilotoEntities(pilotoEntities);
        return Optional.of(vueloEntity);
    }
}

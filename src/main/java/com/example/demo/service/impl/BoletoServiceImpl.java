package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.Util.Utils;
import com.example.demo.conversion.Conversions;
import com.example.demo.entity.BoletosEntity;
import com.example.demo.entity.PasajeroEntity;
import com.example.demo.entity.VueloEntity;
import com.example.demo.repository.BoletoRepository;
import com.example.demo.repository.PasajeroRepository;
import com.example.demo.repository.VueloRepository;
import com.example.demo.request.BoletoRequest;
import com.example.demo.response.BoletoResponse;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.BoletoService;
import com.example.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService {
    private final BoletoRepository boletoRepository;
    private final VueloRepository vueloRepository;
    private final PasajeroRepository pasajeroRepository;
    private final RedisService redisService;

    public BoletoServiceImpl(BoletoRepository boletoRepository, VueloRepository vueloRepository, PasajeroRepository pasajeroRepository, RedisService redisService) {
        this.boletoRepository = boletoRepository;
        this.vueloRepository = vueloRepository;
        this.pasajeroRepository = pasajeroRepository;
        this.redisService = redisService;
    }


    @Override
    public ResponseBase<BoletoResponse> createBoleto(BoletoRequest boletoRequest) {
        Optional<PasajeroEntity> pasajeroEntityOptional = 
                pasajeroRepository.findById(boletoRequest.getIdPasajero());
        
        Optional<VueloEntity> vueloEntityOptional = 
                vueloRepository.findById(boletoRequest.getIdVuelo());
        
        if(pasajeroEntityOptional.isEmpty() || vueloEntityOptional.isEmpty()) {
            return new ResponseBase<>(
                    Constants.CODE_NOT_FOUND,
                    Constants.MESSAGE_NOT_FOUND,
                    Optional.empty());
        }
        VueloEntity vueloEntity = vueloEntityOptional.get();
        int capacidadAvion = vueloEntity.getAvionEntity().getCapacidad();
        if(boletoRequest.getAsiento() < 0 || boletoRequest.getAsiento() > capacidadAvion ) {
            return new ResponseBase<>(
                    Constants.CODE_BAD_REQUEST,
                    Constants.MESSAGE_BAD_REQUEST,
                    Optional.empty());
        }
        
        BoletosEntity boletosEntity = new BoletosEntity();
        boletosEntity.setAsiento(boletoRequest.getAsiento());
        boletosEntity.setPasajeroEntity(pasajeroEntityOptional.get());
        boletosEntity.setVueloEntity(vueloEntity);
        
        boletoRepository.save(boletosEntity);
        
        BoletoResponse boletoResponse = new BoletoResponse(
                boletosEntity.getAsiento(),
                boletosEntity.getPasajeroEntity().getNombre(),
                boletosEntity.getPasajeroEntity().getApellido(),
                boletosEntity.getVueloEntity().getFechaSalida(), 
                boletosEntity.getVueloEntity().getFechaLlegada(),
                boletosEntity.getVueloEntity().getOrigen(),
                boletosEntity.getVueloEntity().getDestino(),
                boletosEntity.getVueloEntity().getAvionEntity().getModelo(),
                boletosEntity.getVueloEntity().getAvionEntity().getAerolineaEntity().getNombre()
        );
        return new ResponseBase<>(
                Constants.CODE_CREATED,
                Constants.MESSAGE_SUCCESFULL,
                Optional.of(boletoResponse));
    }

    @Override
    public ResponseBase<BoletoResponse> findById(int id) {
        // implementar la funcionalidad de poder guardar en redis
        // una vez se busque por id
        // en caso el dato este en redis devolver el dato de redis
        String redisInfo = redisService.getValueByKey(String.valueOf(id));
        if(redisInfo != null && !redisInfo.isEmpty()) {
            return Utils.convertFromJsonBoleto(redisInfo);
        }
        Optional<BoletosEntity> boletosEntity = boletoRepository.findById(id);
        if(boletosEntity.isEmpty()) {
            return new ResponseBase<>(
                    Constants.CODE_NOT_FOUND,
                    Constants.MESSAGE_NOT_FOUND,
                    Optional.empty());
        }

        ResponseBase<BoletoResponse> responseBase = new ResponseBase<>(
                Constants.CODE_SUCCESFULL,
                Constants.MESSAGE_FIND,
                Optional.of(Conversions.entityToBoletoResponse(boletosEntity.get())));
        redisService.saveKeyValue(String.valueOf(id), Utils.fromBoletoResponseJson(responseBase), 10);
        return responseBase;

    }

    @Override
    public ResponseBase<BoletoResponse> updateAsiento(int idBoleto, int newAsiento) {
        Optional<BoletosEntity> boletosEntityOptional = boletoRepository.findById(idBoleto);
        if(boletosEntityOptional.isEmpty()) {
            return new ResponseBase<>(
                    Constants.CODE_NOT_FOUND,
                    Constants.MESSAGE_NOT_FOUND,
                    Optional.empty());
        }
        BoletosEntity boletosEntity = boletosEntityOptional.get();
        Optional<BoletosEntity> boletosEntityFiltered = boletoRepository.findByAsientoAndVueloEntity(newAsiento, boletosEntity.getVueloEntity());
        if(boletosEntityFiltered.isPresent()) {
            return new ResponseBase<>(
                    Constants.CODE_BAD_REQUEST,
                    Constants.MESSAGE_BAD_REQUEST,
                    Optional.empty());
        }

        boletosEntity.setAsiento(newAsiento);
        boletoRepository.save(boletosEntity);
        ResponseBase<BoletoResponse> responseBase = new ResponseBase<>(
                Constants.CODE_SUCCESFULL,
                Constants.MESSAGE_FIND,
                Optional.of(Conversions.entityToBoletoResponse(boletosEntity)));

        String redisData = redisService.getValueByKey(String.valueOf(idBoleto));
        if(redisData != null && !redisData.isEmpty()) {
            redisService.saveKeyValue(
                    String.valueOf(idBoleto),
                    Utils.fromBoletoResponseJson(responseBase),
                    10);
        }
        return responseBase;
    }
}

package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
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
import com.example.demo.service.VueloService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VueloServiceImpl implements VueloService {
    private VueloRepository vueloRepository;
    private AvionRepository avionRepository;
    private PilotoRepository pilotoRepository;

    @Override
    public ResponseBase<VueloResponse> create(VueloRequest vueloRequest) {
        Optional<AvionEntity> avionOptional = avionRepository.findById(vueloRequest.getIdAvion());
        if (avionOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        List<PilotoEntity> pilotoEntityList = new ArrayList<>();
        List<PilotoResponse> pilotoResponseList = new ArrayList<>();

        for (int id : vueloRequest.getPilotos()) {
            PilotoEntity pilotoEntity = pilotoRepository.findById(id).orElse(null);
            if (pilotoEntity == null) {
                return new ResponseBase<>(
                        Constants.CODE_NOT_FOUND,
                        Constants.MESSAGE_NOT_FOUND,
                        Optional.empty());
            }
            pilotoEntityList.add(pilotoEntity);
            String nombre = pilotoEntity.getNombre();
            String apellido = pilotoEntity.getApellido();
            String pilotoConcat = nombre.charAt(0) + ". " + apellido;
            pilotoResponseList.add(new PilotoResponse(pilotoConcat));
        }
        VueloEntity vueloEntity = new VueloEntity();
        vueloEntity.setFechaSalida(vueloRequest.getFechaSalida());
        vueloEntity.setFechaLlegada(vueloRequest.getFechaLlegada());
        vueloEntity.setOrigen(vueloRequest.getOrigen());
        vueloEntity.setDestino(vueloRequest.getDestino());
        vueloEntity.setAvionEntity(avionOptional.get());
        vueloEntity.setPilotoEntities(pilotoEntityList);
        vueloRepository.save(vueloEntity);


        VueloResponse vueloResponse = new VueloResponse(
                vueloEntity.getFechaSalida(),
                vueloEntity.getFechaLlegada(),
                vueloEntity.getOrigen(),
                vueloEntity.getDestino(),
                vueloEntity.getAvionEntity().getModelo(),
                vueloEntity.getAvionEntity().getAerolineaEntity().getNombre(),
                pilotoResponseList
        );

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
            List<PilotoResponse> pilotoResponseList = new ArrayList<>();
            VueloEntity vueloEntity = vueloEntityOptional.get();
            for (PilotoEntity piloto : vueloEntity.getPilotoEntities()) {
                String pilotoSring = toStringPiloto(piloto);
                pilotoResponseList.add(new PilotoResponse(pilotoSring));
            }
            VueloResponse vueloResponse = new VueloResponse(
                    vueloEntity.getFechaSalida(),
                    vueloEntity.getFechaLlegada(),
                    vueloEntity.getOrigen(),
                    vueloEntity.getDestino(),
                    vueloEntity.getAvionEntity().getModelo(),
                    vueloEntity.getAvionEntity().getAerolineaEntity().getNombre(),
                    pilotoResponseList
            );
            return new ResponseBase<>(Constants.CODE_SUCCESFULL, Constants.MESSAGE_FIND, Optional.of(vueloResponse));
    }

    @Override
    public ResponseBase<List<VueloResponse>> findByFechaOrigen(String fecha) {
        List<VueloEntity> vueloEntities = vueloRepository.findByFechaSalidaAfter(fecha);
        return null;
    }

    private String toStringPiloto(PilotoEntity pilotoEntity) {
        return pilotoEntity.getNombre().charAt(0) + ". " + pilotoEntity.getNombre();
    }
}

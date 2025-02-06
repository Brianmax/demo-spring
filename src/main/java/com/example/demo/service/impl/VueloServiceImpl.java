package com.example.demo.service.impl;

import com.example.demo.entity.AvionEntity;
import com.example.demo.entity.VueloEntity;
import com.example.demo.repository.AvionRepository;
import com.example.demo.repository.VueloRepository;
import com.example.demo.request.VueloRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.VueloService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VueloServiceImpl implements VueloService {
    private VueloRepository vueloRepository;
    private AvionRepository avionRepository;
    @Override
    public ResponseBase<VueloEntity> create(VueloRequest vueloRequest) {
        Optional<AvionEntity> avionOptional = avionRepository.findById(vueloRequest.getIdAvion());
        if(avionOptional.isEmpty()) {
            return new ResponseBase<>(400, "Avion no existe", Optional.empty());
        }
        
        VueloEntity vueloEntity = new VueloEntity();
        vueloEntity.setFechaSalida(vueloRequest.getFechaSalida());
        vueloEntity.setFechaLlegada(vueloRequest.getFechaLlegada());
        vueloEntity.setOrigen(vueloRequest.getOrigen());
        vueloEntity.setDestino(vueloRequest.getDestino());
        vueloEntity.setAvionEntity(avionOptional.get());
        
        vueloRepository.save(vueloEntity);
        
        return new ResponseBase<>(200, "Vuelo creado con exito", Optional.of(vueloEntity));
    }
}

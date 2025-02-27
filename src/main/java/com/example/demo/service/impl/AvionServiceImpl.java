package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.entity.AerolineaEntity;
import com.example.demo.entity.AvionEntity;
import com.example.demo.repository.AerolineaRepository;
import com.example.demo.repository.AvionRepository;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.AvionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvionServiceImpl implements AvionService {
    private AvionRepository avionRepository;
    private AerolineaRepository aerolineaRepository;
    public AvionServiceImpl(AvionRepository avionRepository, AerolineaRepository aerolineaRepository) {
        this.avionRepository = avionRepository;
        this.aerolineaRepository = aerolineaRepository;
    }
    @Override
    public ResponseBase<AvionEntity> create(AvionEntity avionEntity) {
        // validar la aerolinea
        AerolineaEntity aerolineaEntity = avionEntity.getAerolineaEntity();
        int idAerolinea = aerolineaEntity.getId();
        Optional<AerolineaEntity> optionalAerolinea = 
                aerolineaRepository.findById(idAerolinea);
        
        if(optionalAerolinea.isEmpty() || avionEntity.getCapacidad() < 40 || avionEntity.getPeso() < 10) {
            return new ResponseBase<>(400, "Datos incorrectos", Optional.empty());
        }
        
        AvionEntity avionSaved = avionRepository.save(avionEntity);
        return new ResponseBase<>(200, "Avion guardado con exito", Optional.of(avionSaved));
    }

    @Override
    public ResponseBase<AvionEntity> findById(int id) {
        AvionEntity avionEntity = avionRepository.findById(id).orElse(null);
        if(avionEntity == null) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        return new ResponseBase<>(Constants.CODE_SUCCESFULL, Constants.MESSAGE_FIND, Optional.of(avionEntity));
    }

    @Override
    public ResponseBase<AvionEntity> updateAerolinea(int idAvion, int idNuevaAerolinea) {
        Optional<AvionEntity> avionEntityOptional = avionRepository.findById(idAvion);
        Optional<AerolineaEntity> aerolineaEntityOptional = aerolineaRepository.findById(idNuevaAerolinea);

        if(avionEntityOptional.isEmpty() || aerolineaEntityOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        AvionEntity avionEntity = avionEntityOptional.get();

        avionEntity.setAerolineaEntity(aerolineaEntityOptional.get());

        avionRepository.save(avionEntity);

        return new ResponseBase<>(
                Constants.CODE_UPDATED,
                Constants.MESSAGE_SUCCESFULL_UPDATE,
                Optional.of(avionEntity));
    }
}

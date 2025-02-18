package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.entity.AerolineaEntity;
import com.example.demo.repository.AerolineaRepository;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.AerolineaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AerolineServiceImpl implements AerolineaService {
    private AerolineaRepository aerolineaRepository;

    public AerolineServiceImpl(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    @Override
    public ResponseBase<AerolineaEntity> save(AerolineaEntity aerolineaEntity) {
        aerolineaRepository.save(aerolineaEntity);
        return new ResponseBase<>(Constants.CODE_SUCCESFULL, Constants.MESSAGE_SUCCESFULL, Optional.of(aerolineaEntity));
    }

    @Override
    public ResponseBase<AerolineaEntity> findByNombre(String nombre) {
        AerolineaEntity aerolineaEntity = aerolineaRepository.findByNombre(nombre).stream().findFirst().orElse(null);
        if (aerolineaEntity == null) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        return new ResponseBase<>(Constants.CODE_SUCCESFULL, Constants.MESSAGE_FIND, Optional.of(aerolineaEntity));

    }

    @Override
    public ResponseBase<AerolineaEntity> updateById(AerolineaEntity aerolineaEntity, int id) {
        AerolineaEntity aerolineaEntityUpdate = aerolineaRepository.findById(id).orElse(null);
        if (aerolineaEntityUpdate == null) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        // actualizando los datos
        aerolineaEntityUpdate.setNombre(aerolineaEntity.getNombre());
        aerolineaRepository.save(aerolineaEntityUpdate);

        return new ResponseBase<>(Constants.CODE_UPDATED, Constants.MESSAGE_SUCCESFULL_UPDATE, Optional.of(aerolineaEntityUpdate));

    }
}

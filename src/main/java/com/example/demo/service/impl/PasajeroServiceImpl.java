package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.entity.PasajeroEntity;
import com.example.demo.repository.PasajeroRepository;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasajeroServiceImpl implements PasajeroService {
    @Autowired
    private PasajeroRepository pasajeroRepository;
    @Override
    public ResponseBase<PasajeroEntity> createPasajero(PasajeroEntity pasajeroEntity) {
        pasajeroRepository.save(pasajeroEntity);
        return new ResponseBase<>(Constants.CODE_CREATED, Constants.MESSAGE_SUCCESFULL, Optional.of(pasajeroEntity));
    }

    @Override
    public ResponseBase<PasajeroEntity> findById(int id) {
        Optional<PasajeroEntity> pasajeroEntity = pasajeroRepository.findById(id);
        if(pasajeroEntity.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        return new ResponseBase<>(Constants.CODE_SUCCESFULL, Constants.MESSAGE_SUCCESFULL, pasajeroEntity);
    }

    @Override
    public ResponseBase<PasajeroEntity> updatePasajero(PasajeroEntity pasajeroEntity) {
        return null;
    }

    @Override
    public ResponseBase<PasajeroEntity> deletePasajero(int id) {
        return null;
    }
}

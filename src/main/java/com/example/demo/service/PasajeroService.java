package com.example.demo.service;

import com.example.demo.entity.PasajeroEntity;
import com.example.demo.response.ResponseBase;

public interface PasajeroService {
    ResponseBase<PasajeroEntity> createPasajero(PasajeroEntity pasajeroEntity);
    ResponseBase<PasajeroEntity> findById(int id);
    ResponseBase<PasajeroEntity> updatePasajero(PasajeroEntity pasajeroEntity);
    ResponseBase<PasajeroEntity> deletePasajero(int id);
}

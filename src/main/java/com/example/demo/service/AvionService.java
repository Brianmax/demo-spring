package com.example.demo.service;

import com.example.demo.entity.AvionEntity;
import com.example.demo.response.ResponseBase;

public interface AvionService {
    ResponseBase<AvionEntity> create(AvionEntity avionEntity);
    ResponseBase<AvionEntity> findById(int id);
    ResponseBase<AvionEntity> updateAerolinea(int idAvion, int idNuevaAerolinea);
}

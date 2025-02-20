package com.example.demo.service;

import com.example.demo.controller.AerolineaController;
import com.example.demo.entity.AerolineaEntity;
import com.example.demo.entity.AvionEntity;
import com.example.demo.response.ResponseBase;

public interface AerolineaService {
    ResponseBase<AerolineaEntity> save(AerolineaEntity aerolineaEntity);
    ResponseBase<AerolineaEntity> findByNombre(String nombre);
    ResponseBase<AerolineaEntity> updateById(AerolineaEntity aerolineaEntity, int id);
    boolean deleteById(int id);
}

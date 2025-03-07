package com.example.demo.service;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.request.PilotoRequest;
import com.example.demo.response.ResponseBase;

import java.util.List;

public interface PilotoService {
    // metodo para guardar un piloto
    ResponseBase<PilotoEntity> pilotoSave(String dni);
    ResponseBase<PilotoEntity> findById(int id);
    ResponseBase<List<PilotoEntity>> findByNombre(String nombre);
    ResponseBase<PilotoRequest> updatePiloto(PilotoRequest piloto, int id);
}

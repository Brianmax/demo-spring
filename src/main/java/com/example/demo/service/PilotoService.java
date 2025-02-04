package com.example.demo.service;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.request.PilotoRequest;
import com.example.demo.response.ResponseBase;

public interface PilotoService {
    // metodo para guardar un piloto
    ResponseBase<PilotoEntity> pilotoSave(PilotoRequest piloto);
    PilotoEntity findById(int id);
}

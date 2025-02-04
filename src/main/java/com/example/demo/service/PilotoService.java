package com.example.demo.service;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.request.PilotoRequest;

public interface PilotoService {
    // metodo para guardar un piloto
    PilotoEntity pilotoSave(PilotoRequest piloto);
    PilotoEntity findById(int id);
}

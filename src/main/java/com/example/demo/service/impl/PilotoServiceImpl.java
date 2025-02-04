package com.example.demo.service.impl;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.repository.PilotoRepository;
import com.example.demo.request.PilotoRequest;
import com.example.demo.service.PilotoService;
import org.springframework.stereotype.Service;

@Service
public class PilotoServiceImpl implements PilotoService {
    private PilotoRepository pilotoRepository;
    
    public PilotoServiceImpl(PilotoRepository pilotoRepository) {
        this.pilotoRepository = pilotoRepository;
    }
    
    @Override
    public PilotoEntity pilotoSave(PilotoRequest piloto) {
        // validar la longitud del nombre
        if(piloto.getNombre().length() > 20) {
            return null;
        }
        PilotoEntity pilotoEntity = new PilotoEntity();
        pilotoEntity.setNombre(piloto.getNombre());
        pilotoEntity.setApellido(piloto.getApellido());
        pilotoEntity.setEstado(true);
        return pilotoRepository.save(pilotoEntity);
    }

    @Override
    public PilotoEntity findById(int id) {
        return null;
    }
}

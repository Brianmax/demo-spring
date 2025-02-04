package com.example.demo.service.impl;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.repository.PilotoRepository;
import com.example.demo.request.PilotoRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.PilotoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PilotoServiceImpl implements PilotoService {
    private PilotoRepository pilotoRepository;
    
    public PilotoServiceImpl(PilotoRepository pilotoRepository) {
        this.pilotoRepository = pilotoRepository;
    }
    
    @Override
    public ResponseBase<PilotoEntity> pilotoSave(PilotoRequest piloto) {
        // validar la longitud del nombre
        if(piloto.getNombre().length() > 20) {
            return new ResponseBase<>(
                    400,
                    "El nombre excede la cantidad de caracteres",
                    Optional.empty());
        }
        PilotoEntity pilotoEntity = new PilotoEntity();
        pilotoEntity.setNombre(piloto.getNombre());
        pilotoEntity.setApellido(piloto.getApellido());
        pilotoEntity.setEstado(true);
        pilotoRepository.save(pilotoEntity);
        
         return new ResponseBase<>(
                200,
                "Exito",
                Optional.of(pilotoEntity));
    }

    @Override
    public PilotoEntity findById(int id) {
        Optional<PilotoEntity> pilotoEntityOptional = pilotoRepository.findById(id);
        if(pilotoEntityOptional.isEmpty()) {
            return null;
        }
        return pilotoEntityOptional.get();
    }
}

package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.entity.PilotoEntity;
import com.example.demo.repository.PilotoRepository;
import com.example.demo.request.PilotoRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.PilotoService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ResponseBase<PilotoEntity> findById(int id) {
        Optional<PilotoEntity> pilotoEntityOptional = pilotoRepository.findById(id);
        if(pilotoEntityOptional.isEmpty()) {
            return new ResponseBase<>(
                    404,
                    "No existe registro con el ID ingresado",
                    Optional.empty());
        }
        return new ResponseBase<>(
                200,
                "Registro encontrado",
                pilotoEntityOptional);
    }

    @Override
    public ResponseBase<List<PilotoEntity>> findByNombre(String nombre) {
        List<PilotoEntity> pilotoEntities = pilotoRepository.findByNombre(nombre);
        if(pilotoEntities.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        
        return new ResponseBase<>(Constants.CODE_SUCCESFULL, Constants.MESSAGE_FIND, Optional.of(pilotoEntities));
    }
}

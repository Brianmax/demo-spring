package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.conversion.Conversions;
import com.example.demo.entity.PilotoEntity;
import com.example.demo.feignClient.ReniecClient;
import com.example.demo.repository.PilotoRepository;
import com.example.demo.request.PilotoRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.response.ResponseReniec;
import com.example.demo.service.PilotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PilotoServiceImpl implements PilotoService {
    private final PilotoRepository pilotoRepository;
    private final ReniecClient reniecClient;
    @Value("${token.api.reniec}")
    private String token;
    
    public PilotoServiceImpl(PilotoRepository pilotoRepository, ReniecClient reniecClient) {
        this.pilotoRepository = pilotoRepository;
        this.reniecClient = reniecClient;
    }
    
    @Override
    public ResponseBase<PilotoEntity> pilotoSave(String dni) {
        // validar la longitud del nombre
        ResponseReniec responseReniec = reniecClient.getInfoReniec(dni, token);
        if(responseReniec == null) {
            return new ResponseBase<>(
                    Constants.CODE_NOT_FOUND,
                    Constants.MESSAGE_NOT_FOUND,
                    Optional.empty());
        }
        PilotoEntity pilotoEntity = new PilotoEntity();
        pilotoEntity.setNombre(responseReniec.getNombres().split(" ")[0]);
        pilotoEntity.setApellido(responseReniec.getApellidoPaterno());
        pilotoEntity.setDni(dni);
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

    @Override
    public ResponseBase<PilotoRequest> updatePiloto(PilotoRequest piloto, int id) {
        Optional<PilotoEntity> pilotoEntityOptional = pilotoRepository.findById(id);
        if(pilotoEntityOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        PilotoEntity pilotoEntity = pilotoEntityOptional.get();
        pilotoEntity.setNombre(piloto.getNombre());
        pilotoEntity.setApellido(piloto.getApellido());

        pilotoRepository.save(pilotoEntity);


        return new ResponseBase<PilotoRequest>(
                Constants.CODE_UPDATED,
                Constants.MESSAGE_SUCCESFULL_UPDATE,
                Optional.of(Conversions.fromPilotoEntity(pilotoEntity)));
    }
}

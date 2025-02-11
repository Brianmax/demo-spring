package com.example.demo.controller;

import com.example.demo.entity.PasajeroEntity;
import com.example.demo.repository.PasajeroRepository;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pasajero")
public class PasajeroController {
    @Autowired
    private PasajeroService pasajeroService;
    
    @PostMapping("/save")
    public ResponseBase<PasajeroEntity> createPasajero(@RequestBody PasajeroEntity pasajeroEntity){
        return pasajeroService.createPasajero(pasajeroEntity);
    }
}

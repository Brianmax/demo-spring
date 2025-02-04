package com.example.demo.controller;

import com.example.demo.entity.AerolineaEntity;
import com.example.demo.repository.AerolineaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aerolinea")
public class AerolineaController {

    private AerolineaRepository aerolineaRepository;

    public AerolineaController(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    @PostMapping("/save")
    public AerolineaEntity saveAerolinea(@RequestBody AerolineaEntity aerolineaEntity) {
        AerolineaEntity aerolineaEntitySave = aerolineaRepository.save(aerolineaEntity);
        return aerolineaEntitySave;
    }
}

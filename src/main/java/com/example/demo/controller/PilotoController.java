package com.example.demo.controller;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.request.PilotoRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.PilotoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/piloto")
public class PilotoController {
    
    private PilotoService pilotoService;
    
    public PilotoController(PilotoService pilotoService) {
        this.pilotoService = pilotoService;
    }
    
    @PostMapping("/save")
    public ResponseBase<PilotoEntity> pilotoSave(@RequestBody PilotoRequest pilotoRequest) {
        return pilotoService.pilotoSave(pilotoRequest);
    }
    
    @GetMapping("/find/{id}")
    public PilotoEntity findById(@PathVariable int id) {
        return pilotoService.findById(id);
    }
    // crear un service para aerolinea
}

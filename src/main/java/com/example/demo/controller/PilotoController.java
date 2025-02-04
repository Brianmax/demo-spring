package com.example.demo.controller;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.entity.TestEntity;
import com.example.demo.repository.PilotoRepository;
import com.example.demo.request.PilotoRequest;
import com.example.demo.service.PilotoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/piloto")
public class PilotoController {
    
    private PilotoRepository pilotoRepository;
    private PilotoService pilotoService;
    
    public PilotoController(PilotoRepository pilotoRepository, PilotoService pilotoService) {
        this.pilotoRepository = pilotoRepository;
        this.pilotoService = pilotoService;
    }
    
    @PostMapping("/save")
    public PilotoEntity pilotoSave(@RequestBody PilotoRequest pilotoRequest) {
        return pilotoService.pilotoSave(pilotoRequest);
    }
    
    @GetMapping("/find/{id}")
    public PilotoEntity findById(@PathVariable int id) {
        Optional<PilotoEntity> pilotoOptional = pilotoRepository.findById(id);
        if(pilotoOptional.isEmpty()) {
            return null;
        }
        return pilotoOptional.get();
    }
}

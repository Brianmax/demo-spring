package com.example.demo.controller;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.entity.TestEntity;
import com.example.demo.repository.PilotoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/piloto")
public class PilotoController {
    
    private PilotoRepository pilotoRepository;
    
    public PilotoController(PilotoRepository pilotoRepository) {
        this.pilotoRepository = pilotoRepository;
    }
    
    @PostMapping("/save")
    public PilotoEntity pilotoSave(@RequestBody PilotoEntity piloto) {
        return pilotoRepository.save(piloto);
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

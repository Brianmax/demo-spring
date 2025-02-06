package com.example.demo.controller;

import com.example.demo.entity.AvionEntity;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.AvionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/avion")
public class AvionController {
    
    private AvionService avionService;
    
    public AvionController(AvionService avionService) {
        this.avionService = avionService;
    }
    
    @PostMapping("/save")
    public ResponseBase<AvionEntity> create(@RequestBody AvionEntity avionEntity) {
        return avionService.create(avionEntity);
    }
}

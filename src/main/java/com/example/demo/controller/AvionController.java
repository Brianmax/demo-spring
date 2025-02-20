package com.example.demo.controller;

import com.example.demo.entity.AvionEntity;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.AvionService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/find/{id}")
    public ResponseBase<AvionEntity> findById(@PathVariable int id) {
        return avionService.findById(id);
    }

    @PutMapping("/update/")
    public ResponseBase<AvionEntity> updateAerolinea(@RequestParam int idAvion, @RequestParam int idAerolinea) {
        return avionService.updateAerolinea(idAvion, idAerolinea);
    }

}

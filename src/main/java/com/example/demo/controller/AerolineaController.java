package com.example.demo.controller;

import com.example.demo.entity.AerolineaEntity;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.AerolineaService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/aerolinea")
public class AerolineaController {

    private AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @PostMapping("/save")
    public ResponseBase<AerolineaEntity> saveAerolinea(@RequestBody AerolineaEntity aerolineaEntity) {
        return aerolineaService.save(aerolineaEntity);
    }

    @GetMapping("/find")
    public ResponseBase<AerolineaEntity> findByNombre(@RequestParam String nombre) {
        return aerolineaService.findByNombre(nombre);
    }

    @PutMapping("/update/{id}")
    public ResponseBase<AerolineaEntity> updateAerolinea(@RequestBody AerolineaEntity aerolineaEntity, @PathVariable int id) {
        return aerolineaService.updateById(aerolineaEntity, id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteAerolinea(@PathVariable int id) {
        return aerolineaService.deleteById(id);
    }

}

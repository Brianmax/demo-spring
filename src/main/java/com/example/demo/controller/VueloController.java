package com.example.demo.controller;

import com.example.demo.entity.VueloEntity;
import com.example.demo.request.VueloRequest;
import com.example.demo.request.VueloUpdateRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.response.VueloResponse;
import com.example.demo.service.AvionService;
import com.example.demo.service.VueloService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vuelo")
public class VueloController {
    private VueloService vueloService;
    
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }
    
    @PostMapping("/save")
    public ResponseBase<VueloResponse> create(@RequestBody VueloRequest vueloRequest) {
        return vueloService.create(vueloRequest);
    }
    
    @GetMapping("/find/{id}")
    public ResponseBase<VueloResponse> findById(@PathVariable int id) {
        return vueloService.findByID(id);
    }
    
    @GetMapping("/find/date")
    public ResponseBase<List<VueloResponse>> findByFechaOrigen(@RequestParam String fechaOrigen) {
        return vueloService.findByFechaOrigen(fechaOrigen);
    }

    @PutMapping("/update/{id}")
    public ResponseBase<VueloResponse> update(@RequestBody List<Integer> ids, @PathVariable int id) {
        return vueloService.updateById(ids, id);
    }

}

package com.example.demo.controller;

import com.example.demo.entity.VueloEntity;
import com.example.demo.request.VueloRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.response.VueloResponse;
import com.example.demo.service.AvionService;
import com.example.demo.service.VueloService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

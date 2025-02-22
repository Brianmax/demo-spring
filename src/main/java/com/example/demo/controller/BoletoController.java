package com.example.demo.controller;

import com.example.demo.entity.BoletosEntity;
import com.example.demo.request.BoletoRequest;
import com.example.demo.response.BoletoResponse;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boleto")
public class BoletoController {
    @Autowired
    private BoletoService boletoService;
    
    @PostMapping("/save")
    public ResponseBase<BoletoResponse> createBoleto(@RequestBody BoletoRequest boletoRequest) {
        return boletoService.createBoleto(boletoRequest);
    }

    @GetMapping("/find/{id}")
    public ResponseBase<BoletoResponse> findBoleto(@PathVariable int id) {
        return boletoService.findById(id);
    }

    @PutMapping("/update/")
    public ResponseBase<BoletoResponse> updateBoleto(
            @RequestParam int idBoleto,
            @RequestParam int asiento) {
        return boletoService.updateAsiento(idBoleto, asiento);
    }
}

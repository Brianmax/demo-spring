package com.example.demo.controller;

import com.example.demo.entity.BoletosEntity;
import com.example.demo.request.BoletoRequest;
import com.example.demo.response.BoletoResponse;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/boleto")
public class BoletoController {
    @Autowired
    private BoletoService boletoService;
    
    @PostMapping("/save")
    public ResponseBase<BoletoResponse> createBoleto(@RequestBody BoletoRequest boletoRequest) {
        return boletoService.createBoleto(boletoRequest);
    }
}

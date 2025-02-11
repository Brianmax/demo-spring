package com.example.demo.service.impl;

import com.example.demo.entity.BoletosEntity;
import com.example.demo.repository.BoletoRepository;
import com.example.demo.request.BoletoRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoletoServiceImpl implements BoletoService {
    @Autowired
    private BoletoRepository boletoRepository;
    @Override
    public ResponseBase<BoletosEntity> createBoleto(BoletoRequest boletoRequest) {
        // implementar create boleto
    }
}

package com.example.demo.service;

import com.example.demo.entity.BoletosEntity;
import com.example.demo.request.BoletoRequest;
import com.example.demo.response.ResponseBase;

public interface BoletoService {
    ResponseBase<BoletosEntity> createBoleto(BoletoRequest boletoRequest);
}

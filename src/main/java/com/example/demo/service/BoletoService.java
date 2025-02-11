package com.example.demo.service;

import com.example.demo.entity.BoletosEntity;
import com.example.demo.request.BoletoRequest;
import com.example.demo.response.BoletoResponse;
import com.example.demo.response.ResponseBase;

public interface BoletoService {
    ResponseBase<BoletoResponse> createBoleto(BoletoRequest boletoRequest);
}

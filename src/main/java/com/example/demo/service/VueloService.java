package com.example.demo.service;

import com.example.demo.entity.VueloEntity;
import com.example.demo.request.VueloRequest;
import com.example.demo.response.ResponseBase;

public interface VueloService {
    ResponseBase<VueloEntity> create(VueloRequest vueloRequest);
}

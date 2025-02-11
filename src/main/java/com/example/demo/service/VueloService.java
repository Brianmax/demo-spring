package com.example.demo.service;

import com.example.demo.request.VueloRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.response.VueloResponse;

public interface VueloService {
    ResponseBase<VueloResponse> create(VueloRequest vueloRequest);
}

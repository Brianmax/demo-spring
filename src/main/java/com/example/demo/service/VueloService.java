package com.example.demo.service;

import com.example.demo.request.VueloRequest;
import com.example.demo.response.ResponseBase;
import com.example.demo.response.VueloResponse;

import java.util.List;

public interface VueloService {
    ResponseBase<VueloResponse> create(VueloRequest vueloRequest);
    ResponseBase<VueloResponse> findByID(int id);
    ResponseBase<List<VueloResponse>> findByFechaOrigen(String fecha);
    ResponseBase<VueloResponse> updateById(List<Integer> ids, int idVuelo);
}

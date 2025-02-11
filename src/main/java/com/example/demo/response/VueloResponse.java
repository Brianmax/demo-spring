package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VueloResponse {
    private String fechaSalida;
    private String fechaLlegada;
    private String origen;
    private String destino;
    private String avionModelo;
    private String nombreAerolinea;
    private List<PilotoResponse> pilotos;
}

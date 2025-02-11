package com.example.demo.request;

import com.example.demo.entity.PilotoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VueloRequest {
    private String fechaSalida;
    private String fechaLlegada;
    private String origen;
    private String destino;
    private int idAvion;
    private List<Integer> pilotos;
}

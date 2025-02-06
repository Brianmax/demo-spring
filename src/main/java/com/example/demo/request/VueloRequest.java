package com.example.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VueloRequest {
    private String fechaSalida;
    private String fechaLlegada;
    private String origen;
    private String destino;
    private int idAvion;
}

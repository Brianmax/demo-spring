package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoletoResponse {
    private int asiento;
    private String nombre;
    private String apellido;
    private String fechaSalida;
    private String fechaLlegada;
    private String origen;
    private String destino;
    private String modeloAvion;
    private String aerolineaNombre;
}

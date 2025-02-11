package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoletoRequest {
    private int asiento;
    private int idPasajero;
    private int idVuelo;
}

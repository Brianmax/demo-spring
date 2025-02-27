package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pasajeros")
public class PasajeroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero")
    private int id;
    private String nombre;
    private String apellido;
    // modificar el codigo para que se cree un
    // pasajero solo usando el dni
}

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pilotos")
@Getter
@Setter
public class PilotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_piloto")
    private int id;
    private String nombre;
    private String apellido;
    private boolean estado;
    
}

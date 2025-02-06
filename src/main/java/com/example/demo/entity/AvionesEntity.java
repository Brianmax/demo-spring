package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aviones")
public class AvionesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avion")
    private int id;
    private int capacidad;
    private int peso;
    private String modelo;
    
    @ManyToOne
    @JoinTable(name = "id_aerolinea_fk")
    private AerolineaEntity aerolineaEntity;
}

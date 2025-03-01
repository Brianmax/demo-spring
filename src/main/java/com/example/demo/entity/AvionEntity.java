package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aviones")
@AllArgsConstructor
@NoArgsConstructor
public class AvionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avion")
    private int id;
    private int capacidad;
    private int peso;
    private String modelo;
    
    @ManyToOne
    @JoinColumn(name = "id_aerolinea_fk")
    private AerolineaEntity aerolineaEntity;

    // crear un endpoint para poder
    // cambiar de aerolinea al avion
}

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "vuelos")
@AllArgsConstructor
@NoArgsConstructor
public class VueloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private int id;
    private String fechaSalida; // cammel case
    private String fechaLlegada;
    private String origen;
    private String destino;
    
    @ManyToOne
    @JoinColumn(name = "id_avion_fk")
    private AvionEntity avionEntity;
}

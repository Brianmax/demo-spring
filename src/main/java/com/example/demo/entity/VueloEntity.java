package com.example.demo.entity;

import com.example.demo.response.VueloResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


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
    
    @ManyToMany
    @JoinTable(name = "vuelo_piloto",
    joinColumns = @JoinColumn(name = "id_vuelo_fk"),
    inverseJoinColumns = @JoinColumn(name = "id_piloto_fk"))
    private List<PilotoEntity> pilotoEntities;
    
}

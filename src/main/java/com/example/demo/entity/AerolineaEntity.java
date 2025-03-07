package com.example.demo.entity;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Entity
@Table(name = "aerolineas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AerolineaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aerolinea")
    private int id;
    private String nombre;
}

package com.example.demo.repository;

import com.example.demo.entity.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VueloRepository extends JpaRepository<VueloEntity, Integer> {
    List<VueloEntity> findByFechaSalidaAfter(String fechaSalida);
}

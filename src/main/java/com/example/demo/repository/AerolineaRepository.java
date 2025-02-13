package com.example.demo.repository;

import com.example.demo.entity.AerolineaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AerolineaRepository extends JpaRepository<AerolineaEntity, Integer> {
    List<AerolineaEntity> findByNombre(String nombre);
}

package com.example.demo.repository;

import com.example.demo.entity.PilotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PilotoRepository extends JpaRepository<PilotoEntity, Integer> {
    List<PilotoEntity> findByNombre(String nombre);
}

package com.example.demo.repository;

import com.example.demo.entity.PasajeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasajeroRepository extends JpaRepository<PasajeroEntity, Integer> {
}

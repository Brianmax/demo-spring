package com.example.demo.repository;

import com.example.demo.entity.BoletosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<BoletosEntity, Integer> {
}

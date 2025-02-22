package com.example.demo.repository;

import com.example.demo.entity.BoletosEntity;
import com.example.demo.entity.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BoletoRepository extends JpaRepository<BoletosEntity, Integer> {
    Optional<BoletosEntity> findByAsientoAndVueloEntity(int asiento, VueloEntity vueloEntity);
}

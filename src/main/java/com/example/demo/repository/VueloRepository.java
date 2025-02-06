package com.example.demo.repository;

import com.example.demo.entity.AvionEntity;
import com.example.demo.entity.VueloEntity;
import com.example.demo.request.VueloRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<VueloEntity, Integer> {
}

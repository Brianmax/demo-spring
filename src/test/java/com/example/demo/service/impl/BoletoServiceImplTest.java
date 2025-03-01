package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.entity.*;
import com.example.demo.repository.BoletoRepository;
import com.example.demo.repository.PasajeroRepository;
import com.example.demo.repository.VueloRepository;
import com.example.demo.request.BoletoRequest;
import com.example.demo.response.BoletoResponse;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoletoServiceImplTest {
    @Mock
    private BoletoRepository boletoRepository;
    @Mock
    private VueloRepository vueloRepository;
    @Mock
    private PasajeroRepository pasajeroRepository;
    @Mock
    private RedisService redisService;

    private BoletoServiceImpl boletoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        boletoService = new BoletoServiceImpl(boletoRepository, vueloRepository, pasajeroRepository, redisService);
    }

    @Test
    void createBoleto() {
        BoletoRequest boletoRequest = new BoletoRequest(20, 1, 2);

        PasajeroEntity pasajeroEntity = new PasajeroEntity();
        pasajeroEntity.setId(1);
        pasajeroEntity.setNombre("George");
        pasajeroEntity.setApellido("Maxi");

        // vuelo entity

        VueloEntity vueloEntity = new VueloEntity();
        vueloEntity.setFechaSalida("10-10-2024");
        vueloEntity.setFechaLlegada("10-10-2024");
        vueloEntity.setOrigen("AQP");
        vueloEntity.setDestino("LIM");
        vueloEntity.setAvionEntity(new AvionEntity(
                1,
                100,
                50,
                "GHC",
                new AerolineaEntity(1, "TWC")
                ));
        vueloEntity.setPilotoEntities(new ArrayList<>());

        // boleto entity
        BoletosEntity boletosEntity = new BoletosEntity(
                10,
                20,
                pasajeroEntity,
                vueloEntity);

        Mockito.when(pasajeroRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(pasajeroEntity));

        Mockito.when(vueloRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(vueloEntity));

        Mockito.when(boletoRepository.save(Mockito.any(BoletosEntity.class)))
                .thenReturn(boletosEntity);


        ResponseBase<BoletoResponse> response = boletoService.createBoleto(boletoRequest);

        assertEquals(Constants.CODE_CREATED, response.getCodigo());
        assertEquals(Constants.MESSAGE_SUCCESFULL, response.getMensaje());
        assertTrue(response.getData().isPresent());
        assertEquals(
                vueloEntity.getAvionEntity().getModelo(),
                response.getData().get().getModeloAvion());
        assertEquals(
                vueloEntity.getAvionEntity().getAerolineaEntity().getNombre(),
                response.getData().get().getAerolineaNombre());

    }

    @Test
    void findById() {
    }

    @Test
    void updateAsiento() {
    }
}
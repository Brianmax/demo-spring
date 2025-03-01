package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.entity.AerolineaEntity;
import com.example.demo.entity.AvionEntity;
import com.example.demo.repository.AerolineaRepository;
import com.example.demo.repository.AvionRepository;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.AerolineaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AvionServiceImplTest {

    @Mock
    private AvionRepository avionRepository;
    @Mock
    private AerolineaRepository aerolineaRepository;

    @InjectMocks
    private AvionServiceImpl avionService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        avionService = new AvionServiceImpl(avionRepository, aerolineaRepository);
    }

    @Test
    void create() {
        AvionEntity avionEntity = new AvionEntity();
        avionEntity.setModelo("BW3-89");
        avionEntity.setPeso(100);
        avionEntity.setCapacidad(150);
        AerolineaEntity aerolineaEntity = new AerolineaEntity();
        aerolineaEntity.setNombre("ABC");
        aerolineaEntity.setId(40);
        avionEntity.setAerolineaEntity(aerolineaEntity);

        AvionEntity avionEntitySaved = new AvionEntity();
        avionEntitySaved.setId(1);
        avionEntitySaved.setModelo("BW3-89");
        avionEntitySaved.setPeso(100);
        avionEntitySaved.setCapacidad(150);
        avionEntitySaved.setAerolineaEntity(aerolineaEntity);

        Mockito.when(aerolineaRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(aerolineaEntity));


        Mockito.when(avionRepository.save(Mockito.any(AvionEntity.class)))
                .thenReturn(avionEntitySaved);


        ResponseBase<AvionEntity> response = avionService.create(avionEntity);

        assertEquals(200, response.getCodigo());
        assertTrue(response.getData().isPresent());
        assertEquals(1, response.getData().get().getId());
        assertNotNull(response.getData().get().getAerolineaEntity());
    }

    @Test
    void createFail() {
        AvionEntity avionEntity = new AvionEntity();
        avionEntity.setModelo("BW3-89");
        avionEntity.setPeso(5);
        avionEntity.setCapacidad(35);
        AerolineaEntity aerolineaEntity = new AerolineaEntity();
        aerolineaEntity.setId(40);
        avionEntity.setAerolineaEntity(aerolineaEntity);

        Mockito.when(aerolineaRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        ResponseBase<AvionEntity> responseBase = avionService.create(avionEntity);

        assertEquals(400, responseBase.getCodigo());
        assertTrue(responseBase.getData().isEmpty());
    }

    @Test
    void findById() {
    }

    @Test
    void updateAerolinea() {
    }
}
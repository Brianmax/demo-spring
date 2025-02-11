package com.example.demo.service.impl;

import com.example.demo.Util.Constants;
import com.example.demo.entity.BoletosEntity;
import com.example.demo.entity.PasajeroEntity;
import com.example.demo.entity.VueloEntity;
import com.example.demo.repository.BoletoRepository;
import com.example.demo.repository.PasajeroRepository;
import com.example.demo.repository.VueloRepository;
import com.example.demo.request.BoletoRequest;
import com.example.demo.response.BoletoResponse;
import com.example.demo.response.ResponseBase;
import com.example.demo.service.BoletoService;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService {
    @Autowired
    private BoletoRepository boletoRepository;
    @Autowired
    private VueloRepository vueloRepository;
    @Autowired
    private PasajeroRepository pasajeroRepository;
    @Override
    public ResponseBase<BoletoResponse> createBoleto(BoletoRequest boletoRequest) {
        Optional<PasajeroEntity> pasajeroEntityOptional = 
                pasajeroRepository.findById(boletoRequest.getIdPasajero());
        
        Optional<VueloEntity> vueloEntityOptional = 
                vueloRepository.findById(boletoRequest.getIdVuelo());
        
        if(pasajeroEntityOptional.isEmpty() || vueloEntityOptional.isEmpty()) {
            return new ResponseBase<>(
                    Constants.CODE_NOT_FOUND,
                    Constants.MESSAGE_NOT_FOUND,
                    Optional.empty());
        }
        VueloEntity vueloEntity = vueloEntityOptional.get();
        int capacidadAvion = vueloEntity.getAvionEntity().getCapacidad();
        if(boletoRequest.getAsiento() < 0 || boletoRequest.getAsiento() > capacidadAvion ) {
            return new ResponseBase<>(
                    Constants.CODE_BAD_REQUEST,
                    Constants.MESSAGE_BAD_REQUEST,
                    Optional.empty());
        }
        
        BoletosEntity boletosEntity = new BoletosEntity();
        boletosEntity.setAsiento(boletoRequest.getAsiento());
        boletosEntity.setPasajeroEntity(pasajeroEntityOptional.get());
        boletosEntity.setVueloEntity(vueloEntity);
        
        boletoRepository.save(boletosEntity);
        
        BoletoResponse boletoResponse = new BoletoResponse(
                boletosEntity.getAsiento(),
                boletosEntity.getPasajeroEntity().getNombre(),
                boletosEntity.getPasajeroEntity().getApellido(),
                boletosEntity.getVueloEntity().getFechaSalida(), 
                boletosEntity.getVueloEntity().getFechaLlegada(),
                boletosEntity.getVueloEntity().getOrigen(),
                boletosEntity.getVueloEntity().getDestino(),
                boletosEntity.getVueloEntity().getAvionEntity().getModelo(),
                boletosEntity.getVueloEntity().getAvionEntity().getAerolineaEntity().getNombre()
        );
        return new ResponseBase<>(
                Constants.CODE_CREATED,
                Constants.MESSAGE_SUCCESFULL,
                Optional.of(boletoResponse));
    }
}

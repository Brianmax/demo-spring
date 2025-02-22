package com.example.demo.conversion;

import com.example.demo.entity.BoletosEntity;
import com.example.demo.entity.PilotoEntity;
import com.example.demo.entity.VueloEntity;
import com.example.demo.request.PilotoRequest;
import com.example.demo.request.VueloRequest;
import com.example.demo.response.BoletoResponse;
import com.example.demo.response.PilotoResponse;
import com.example.demo.response.VueloResponse;

import java.util.ArrayList;
import java.util.List;

public class Conversions {
    public static VueloResponse entityToVueloResponse(VueloEntity vueloEntity) {
        return new VueloResponse(
                vueloEntity.getFechaSalida(),
                vueloEntity.getFechaLlegada(),
                vueloEntity.getOrigen(),
                vueloEntity.getDestino(),
                vueloEntity.getAvionEntity().getModelo(),
                vueloEntity.getAvionEntity().getAerolineaEntity().getNombre(),
                getListPilotoResponse(vueloEntity.getPilotoEntities())
        );
    }
    
    private static String toStringPiloto(PilotoEntity pilotoEntity) {
        return pilotoEntity.getNombre().charAt(0) + ". " + pilotoEntity.getApellido();
    }
    private static List<PilotoResponse> getListPilotoResponse(List<PilotoEntity> pilotoEntities) {
        List<PilotoResponse> list = new ArrayList<>();
        for(PilotoEntity piloto: pilotoEntities) {
            list.add(new PilotoResponse(toStringPiloto(piloto)));
        }
        return list;
    }

    public static PilotoRequest fromPilotoEntity(PilotoEntity pilotoEntity) {
        return new PilotoRequest(pilotoEntity.getNombre(), pilotoEntity.getApellido());
    }

    public static BoletoResponse entityToBoletoResponse(BoletosEntity boletosEntity) {
        return new BoletoResponse(
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
    }

    public static VueloEntity fromVueloRequest(VueloRequest vueloRequest) {
        VueloEntity vueloEntity = new VueloEntity();
        vueloEntity.setFechaSalida(vueloRequest.getFechaSalida());
        vueloEntity.setFechaLlegada(vueloRequest.getFechaLlegada());
        vueloEntity.setOrigen(vueloRequest.getOrigen());
        vueloEntity.setDestino(vueloRequest.getDestino());
        return vueloEntity;
    }
}

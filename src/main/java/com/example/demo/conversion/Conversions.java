package com.example.demo.conversion;

import com.example.demo.entity.PilotoEntity;
import com.example.demo.entity.VueloEntity;
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
            PilotoResponse pilotoResponse = new PilotoResponse(toStringPiloto(piloto));
            list.add(pilotoResponse);
        }
        return list;
    }
}

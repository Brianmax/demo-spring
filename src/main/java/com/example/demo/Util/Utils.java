package com.example.demo.Util;

import com.example.demo.response.ResponseBase;
import com.example.demo.response.VueloResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Utils {
    public static String convertToJson(ResponseBase<List<VueloResponse>> responseBase) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(responseBase);
        } catch (Exception e) {
            // manejar la excepcion
            System.out.println(e.getMessage());
            return "";
        }
    }
    
    public static ResponseBase<List<VueloResponse>> convertFromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, ResponseBase.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}

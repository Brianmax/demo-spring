package com.example.demo.feignClient;

import com.example.demo.response.ResponseReniec;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reniec", url = "https://api.apis.net.pe/v2/reniec/dni")
public interface ReniecClient {

    @GetMapping()
    ResponseReniec getInfoReniec(
            @RequestParam("numero") String numero,
            @RequestHeader("Authorization") String token);
}

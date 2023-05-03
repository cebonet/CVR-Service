package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.dto.CvrRegistryResponseDto;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;


public interface CvrClient {

    @GetExchange("/api")
    List<CvrRegistryResponseDto> getCvr();

}

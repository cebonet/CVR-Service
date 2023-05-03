package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.dto.CvrRegistryResponseDto;
import com.cebrailerdogan.cvrservice.dto.CvrResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CvrRegistryService implements CvrService {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final String GET_CVR_DATA_URL = "https://cvrapi.dk/api?search={search}&country={country}";

    public CvrResponseDto getCvrData(String search, String country) {
        CvrRegistryResponseDto registryResponseDto = restTemplate.exchange(GET_CVR_DATA_URL,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        CvrRegistryResponseDto.class,
                        search,
                        country)
                .getBody();
        return objectMapper.convertValue(registryResponseDto, CvrResponseDto.class);
    }

    @Override
    public CvrResponseDto getCachedCvrData() {
        return null;
    }
}

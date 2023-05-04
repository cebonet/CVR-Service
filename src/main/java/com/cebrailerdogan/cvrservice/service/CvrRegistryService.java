package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.dto.CvrRegistryResponseDto;
import com.cebrailerdogan.cvrservice.dto.CvrResponseDto;
import com.cebrailerdogan.cvrservice.exception.MissingRequiredParametersException;
import com.cebrailerdogan.cvrservice.exception.OnlyOneSearchParameterAllowedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CvrRegistryService implements CvrService {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final String GET_CVR_DATA_URL = "https://cvrapi.dk/api";

    public CvrResponseDto getCvrData(String search, String country, Long vat, String name) {

        if(Optional.ofNullable(search).isEmpty() || Optional.ofNullable(country).isEmpty()){
            throw new MissingRequiredParametersException("Both search and country parameters are required");
        }

        Map<String, String> requiredParameters = new HashMap<>();
        requiredParameters.putAll(Map.of("search", search, "country", country));
        Map<String, String> searchParameters = new HashMap<>();

        if(Optional.ofNullable(vat).isPresent()){
            searchParameters.put("vat", String.valueOf(vat));
        }

        if(Optional.ofNullable(name).isPresent()){
            searchParameters.put("name", name);
        }

        if(!searchParameters.isEmpty()){
            requiredParameters.remove("search");
        }

        if(searchParameters.size() > 1){
            throw new OnlyOneSearchParameterAllowedException("Only one search parameter is accepted");
        }

        Map<String, String> allParameters = new HashMap<>();
        allParameters.putAll(requiredParameters);
        allParameters.putAll(searchParameters);

        UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(GET_CVR_DATA_URL);
        allParameters.entrySet().stream().forEach( e -> uriBuilder.queryParam(e.getKey(), e.getValue()));
        CvrRegistryResponseDto registryResponseDto = restTemplate.exchange(uriBuilder.build().toString(),
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        CvrRegistryResponseDto.class)
                .getBody();

        return objectMapper.convertValue(registryResponseDto, CvrResponseDto.class);
    }

    @Override
    public CvrResponseDto getCachedCvrData() {
        return null;
    }
}

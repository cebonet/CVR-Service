package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.dto.CvrRegistryResponseDto;
import com.cebrailerdogan.cvrservice.exception.CompanyNotFoundException;
import com.cebrailerdogan.cvrservice.exception.MissingRequiredParametersException;
import com.cebrailerdogan.cvrservice.exception.OnlyOneSearchParameterAllowedException;
import com.cebrailerdogan.cvrservice.repository.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyRegistryService implements CompanyService {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final CompanyRepository companyRepository;

    private final String GET_CVR_DATA_URL = "https://cvrapi.dk/api";

    public Optional<Company> getCvrData(String search, String country, Long vat, String name) {

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
        allParameters.forEach((key, value) -> uriBuilder.queryParam(key, value));


        ResponseEntity<CvrRegistryResponseDto> response = null;
        try{
            response = restTemplate.exchange(uriBuilder.build().toString(),
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    CvrRegistryResponseDto.class);

        } catch (HttpClientErrorException errorException){
            if (errorException.getStatusCode() == HttpStatusCode.valueOf(404)){
                throw new CompanyNotFoundException("Company not found in register");
            }
        }

        Company fetchedCompany = objectMapper.convertValue(response.getBody(), Company.class);
        fetchedCompany.getProductionUnits().forEach(productionUnit -> productionUnit.setVat(fetchedCompany));
        companyRepository.save(fetchedCompany);
        return Optional.ofNullable(fetchedCompany);
    }

}

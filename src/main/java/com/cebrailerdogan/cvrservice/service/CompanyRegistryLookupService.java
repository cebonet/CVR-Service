package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.dao.CompanyDao;
import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.dto.registry.CompanyRegistryResponseDto;
import com.cebrailerdogan.cvrservice.exception.CompanyNotFoundException;
import com.cebrailerdogan.cvrservice.exception.MissingRequiredParametersException;
import com.cebrailerdogan.cvrservice.exception.OnlyOneSearchParameterAllowedException;
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
public class CompanyRegistryLookupService implements CompanyLookupService {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final CompanyDao companyDao;

    private static final String CVR_API_URL = "https://cvrapi.dk/api";

    public Optional<Company> getCvrData(String search, String country, Long vat, String name) {

        if (Optional.ofNullable(search).isEmpty() || Optional.ofNullable(country).isEmpty()) {
            throw new MissingRequiredParametersException("Both search and country parameters are required");
        }

        Map<String, String> allParameters = buildQueryParametersForCall(search, country, vat, name);
        UriBuilder uriBuilder = UriComponentsBuilder.fromUriString(CVR_API_URL);
        allParameters.forEach(uriBuilder::queryParam);

        ResponseEntity<CompanyRegistryResponseDto> response = null;
        try {
            response = restTemplate.exchange(uriBuilder.build().toString(),
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    CompanyRegistryResponseDto.class);

        } catch (HttpClientErrorException errorException) {
            if (errorException.getStatusCode() == HttpStatusCode.valueOf(404)) {
                throw new CompanyNotFoundException("Company not found in register");
            }
        }

        Company fetchedCompany = objectMapper.convertValue(response.getBody(), Company.class);
        companyDao.save(fetchedCompany);
        return Optional.of(fetchedCompany);
    }

    private Map<String, String> buildQueryParametersForCall(String search, String country, Long vat, String name) {
        Map<String, String> requiredParameters = new HashMap<>(Map.of("search", search, "country", country));
        Map<String, String> searchParameters = new HashMap<>();

        Optional.ofNullable(vat).ifPresent(v -> searchParameters.put("vat", String.valueOf(v)));
        Optional.ofNullable(name).ifPresent(n -> searchParameters.put("name", name));

        if (!searchParameters.isEmpty()) {
            requiredParameters.remove("search");
        }

        if (searchParameters.size() > 1) {
            throw new OnlyOneSearchParameterAllowedException("Only one search parameter is accepted");
        }

        Map<String, String> allParameters = new HashMap<>();
        allParameters.putAll(requiredParameters);
        allParameters.putAll(searchParameters);
        return allParameters;
    }

}

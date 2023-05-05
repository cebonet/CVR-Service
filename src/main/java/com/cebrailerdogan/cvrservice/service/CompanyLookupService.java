package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.dto.CvrResponseDto;
import com.cebrailerdogan.cvrservice.exception.CompanyNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyLookupService {

    private final CompanyRegistryService companyRegistryService;
    private final CompanyLocalService companyLocalService;
    private final ObjectMapper objectMapper;

    public CvrResponseDto getCvrData(String search, String country, Long vat, String name){

        Optional<Company> companyFromDatabase = companyLocalService.getCvrData(search, country, vat, name);

        if (companyFromDatabase.isPresent()) {
            log.info("Entry found in cache");
            return objectMapper.convertValue(companyFromDatabase.get(), CvrResponseDto.class);
        }

        Optional<Company> companyFromRegistry = companyRegistryService.getCvrData(search, country, vat, name);
        return companyFromRegistry
                .map(company -> objectMapper.convertValue(company, CvrResponseDto.class))
                .orElseThrow(CompanyNotFoundException::new);
    }
}

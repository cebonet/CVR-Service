package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.dto.CompanyResponseDto;
import com.cebrailerdogan.cvrservice.exception.CompanyNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRegistryLookupService companyRegistryService;
    private final CompanyLocalLookupService companyLocalService;
    private final ObjectMapper objectMapper;

    public CompanyResponseDto getCvrData(String search, String country, Long vat, String name){

        Optional<Company> companyFromDatabase = companyLocalService.getCvrData(search, country, vat, name);

        if (companyFromDatabase.isPresent()) {
            log.debug("Entry found in cache");
            return objectMapper.convertValue(companyFromDatabase.get(), CompanyResponseDto.class);
        }

        log.debug("Calling registry for data");
        Optional<Company> companyFromRegistry = companyRegistryService.getCvrData(search, country, vat, name);
        return companyFromRegistry
                .map(company -> objectMapper.convertValue(company, CompanyResponseDto.class))
                .orElseThrow(CompanyNotFoundException::new);
    }
}

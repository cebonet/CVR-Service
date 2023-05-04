package com.cebrailerdogan.cvrservice.controller;

import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.dto.CvrResponseDto;
import com.cebrailerdogan.cvrservice.exception.CompanyNotFoundException;
import com.cebrailerdogan.cvrservice.service.CompanyLocalService;
import com.cebrailerdogan.cvrservice.service.CompanyRegistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/cvr")
@RequiredArgsConstructor
public class CvrController {

    private final CompanyRegistryService companyRegistryService;
    private final CompanyLocalService companyLocalService;


    @GetMapping
    ResponseEntity<Company> getCvrData(@RequestParam String search,
                                       @RequestParam String country,
                                       @RequestParam(required = false) Long vat,
                                       @RequestParam(required = false) String name) {

        Optional<Company> companyFromDatabase = companyLocalService.getCvrData(search, country, vat, name);
        if (companyFromDatabase.isPresent()) {
            log.info("Entry found in cache");
            return ResponseEntity.ok().body(companyFromDatabase.get());
        }


        Optional<Company> companyFromRegistry = companyRegistryService.getCvrData(search, country, vat, name);
        return companyFromRegistry
                .map(ResponseEntity::ok)
                .orElseThrow(CompanyNotFoundException::new);
    }

    @GetMapping("cached")
    ResponseEntity<List<Company>> getCachedCvrData() {
        return ResponseEntity.ok().body(companyLocalService.getAllCompanies());
    }
}

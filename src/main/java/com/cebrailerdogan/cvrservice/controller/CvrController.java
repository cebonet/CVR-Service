package com.cebrailerdogan.cvrservice.controller;

import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.dto.CvrResponseDto;
import com.cebrailerdogan.cvrservice.service.CompanyLocalLookupService;
import com.cebrailerdogan.cvrservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/cvr")
@RequiredArgsConstructor
public class CvrController {

    private final CompanyService companyService;
    private final CompanyLocalLookupService companyLocalService;


    @GetMapping
    ResponseEntity<CvrResponseDto> getCvrData(@RequestParam String search,
                                       @RequestParam String country,
                                       @RequestParam(required = false) Long vat,
                                       @RequestParam(required = false) String name) {
        return ResponseEntity.ok().body(companyService.getCvrData(search, country, vat, name));
    }

    @GetMapping("cached")
    ResponseEntity<List<Company>> getCachedCvrData() {
        return ResponseEntity.ok().body(companyLocalService.getAllCompanies());
    }
}

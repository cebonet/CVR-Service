package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.domain.Company;

import java.util.Optional;


interface CompanyLookupService {
    Optional<Company> getCvrData(String search, String country, Long vat, String name);

}

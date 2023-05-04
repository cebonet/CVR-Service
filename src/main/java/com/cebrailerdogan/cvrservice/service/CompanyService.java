package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.domain.Company;

import java.util.Optional;


interface CompanyService {
    Optional<Company> getCvrData(String search, String country, Long vat, String name);

}

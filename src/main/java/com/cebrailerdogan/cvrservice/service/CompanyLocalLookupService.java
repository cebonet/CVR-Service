package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyLocalLookupService implements CompanyLookupService {

    private final CompanyRepository repository;


    public Optional<Company> getCvrData(String search, String country, Long vat, String name) {

        if(Optional.ofNullable(vat).isPresent()){
            Optional<Company> company = repository.findByVat(vat);
            if(company.isPresent()){
                return company;
            }
        }

        if(Optional.ofNullable(search).isPresent()){
            Optional<Company> company = repository.findByVat(vat); // todo create a sql that joins and compares fields
            if(company.isPresent()){
                return company;
            }
        }

        if(Optional.ofNullable(name).isPresent()){
            Optional<Company> company = repository.findByName(name);
            if(company.isPresent()){
                return company;
            }
        }
        return Optional.empty();
    }

    public List<Company> getAllCompanies() {
        return repository.findAll();
    }
}

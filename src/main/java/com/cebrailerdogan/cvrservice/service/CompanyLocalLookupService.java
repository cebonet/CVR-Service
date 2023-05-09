package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.exception.OnlyOneSearchParameterAllowedException;
import com.cebrailerdogan.cvrservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyLocalLookupService implements CompanyLookupService {

    private final CompanyRepository repository;


    public Optional<Company> getCvrData(String search, String country, Long vat, String name) {
        Map<String, String> searchParameters = new HashMap<>();
        Optional.ofNullable(vat).ifPresent(v -> searchParameters.put("vat", String.valueOf(v)));
        Optional.ofNullable(name).ifPresent(n -> searchParameters.put("name", name));

        if (searchParameters.size() > 1) {
            throw new OnlyOneSearchParameterAllowedException("Only one search parameter is accepted");
        }

        if(Optional.ofNullable(vat).isPresent()){
            Optional<Company> company = repository.findByVat(vat);
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

        if(Optional.ofNullable(search).isPresent()){
            Optional<Company> company = repository.findByVatOrNameOrProductionUnit(search);
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

package com.cebrailerdogan.cvrservice.dao;

import com.cebrailerdogan.cvrservice.domain.Company;
import com.cebrailerdogan.cvrservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyDao {

    private final CompanyRepository repository;

    public Company save(Company company){
        Optional.ofNullable(company.getProductionUnits()).ifPresent(pUnits -> pUnits.forEach(productionUnit -> productionUnit.setCompany(company)));
        Optional.ofNullable(company.getOwners()).ifPresent(owners -> owners.forEach(owner -> owner.setCompany(company)));
        return repository.save(company);
    }

}

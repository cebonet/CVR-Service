package com.cebrailerdogan.cvrservice.repository;

import com.cebrailerdogan.cvrservice.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Long> {


    Optional<Company> findByVat(Long vat);

    Optional<Company> findByName(String name);

//    Optional<Company> findByVatOrPnoOrName(String search);
}

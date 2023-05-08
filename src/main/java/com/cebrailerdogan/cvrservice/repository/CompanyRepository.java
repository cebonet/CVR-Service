package com.cebrailerdogan.cvrservice.repository;

import com.cebrailerdogan.cvrservice.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByVat(Long vat);

    Optional<Company> findByName(String name);

    @Query("SELECT c from Company c " +
            "LEFT JOIN c.productionUnits pus " +
            "WHERE CAST (pus.pno AS string) = :search " +
            "OR lower(c.name) like concat('%', lower(:search), '%') " +
            "OR CAST (c.vat AS string) = :search")
    Optional<Company> findByVatOrNameOrProductionUnit(@Param("search") String search);
}

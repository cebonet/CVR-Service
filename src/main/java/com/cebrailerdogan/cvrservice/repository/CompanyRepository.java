package com.cebrailerdogan.cvrservice.repository;

import com.cebrailerdogan.cvrservice.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Long> {


    Optional<Company> findByVat(Long vat);

    Optional<Company> findByName(String name);

    @Query(value = "SELECT vat, _protected, c.address, address_co, city, city_name, companycode, companydesc, creditbankrupt, creditstartdate, creditstatus, email, employees, end_date, fax, industry_code, industrydesc, c.name, owners, phone, start_date, t, version, zip_code\n" +
            "FROM public.company as c " +
            "LEFT JOIN public.production_unit as p on c.vat = p.vat_id " +
            "WHERE CAST(c.vat AS character varying) = ?1 " +
            "OR c.name = ?1 " +
            "OR CAST(p.pno AS character varying) = ?1",
            nativeQuery = true)
    Optional<Company> findByVatOrNameOrProductionUnit(String search);

}

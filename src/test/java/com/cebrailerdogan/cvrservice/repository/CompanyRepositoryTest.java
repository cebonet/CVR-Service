package com.cebrailerdogan.cvrservice.repository;

import com.cebrailerdogan.cvrservice.dao.CompanyDao;
import com.cebrailerdogan.cvrservice.domain.Company;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
    @ActiveProfiles("postgres")
@Import(CompanyDao.class)
class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyDao companyDao;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findByVat() throws IOException {

        // Given
        Company company = objectMapper.readValue(new File("src/test/resources/static/ResponseFromRegistryAsCompany.json"), Company.class);
        companyDao.save(company);

        // When
        Optional<Company> companyOptional = companyRepository.findByVat(30502566L);

        // Then
        assertTrue(companyOptional.isPresent());
    }

    @Test
    void findByName() throws IOException {
        // Given
        Company company = objectMapper.readValue(new File("src/test/resources/static/ResponseFromRegistryAsCompany.json"), Company.class);
        companyDao.save(company);

        // When
        Optional<Company> companyOptional = companyRepository.findByName("Uni-tel A/S");

        // Then
        assertTrue(companyOptional.isPresent());
    }

    @Test
    void findByVatOrNameOrProductionUnit() throws IOException {
        // Given
        Company company = objectMapper.readValue(new File("src/test/resources/static/ResponseFromRegistryAsCompany.json"), Company.class);
        companyDao.save(company);

        // When
        Optional<Company> companyOptionalByVat = companyRepository.findByVatOrNameOrProductionUnit("30502566");
        Optional<Company> companyOptionalByName = companyRepository.findByVatOrNameOrProductionUnit("Uni-tel A/S");
        Optional<Company> companyOptionalByPu = companyRepository.findByVatOrNameOrProductionUnit("1013206712");


        // Then
        assertTrue(companyOptionalByVat.isPresent());
        assertTrue(companyOptionalByName.isPresent());
        assertTrue(companyOptionalByPu.isPresent());
    }
}
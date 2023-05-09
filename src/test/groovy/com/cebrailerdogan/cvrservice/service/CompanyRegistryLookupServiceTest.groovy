package com.cebrailerdogan.cvrservice.service

import com.cebrailerdogan.cvrservice.dao.CompanyDao
import com.cebrailerdogan.cvrservice.domain.Company
import com.cebrailerdogan.cvrservice.dto.registry.CompanyRegistryResponseDto
import com.cebrailerdogan.cvrservice.exception.CompanyNotFoundException
import com.cebrailerdogan.cvrservice.exception.MissingRequiredParametersException
import com.cebrailerdogan.cvrservice.exception.OnlyOneSearchParameterAllowedException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class CompanyRegistryLookupServiceTest extends Specification {

    ObjectMapper objectMapper = new ObjectMapper()
    CompanyDao companyDao = Mock(CompanyDao)
    RestTemplate restTemplate = Mock()
    CompanyRegistryLookupService service = new CompanyRegistryLookupService(restTemplate, objectMapper, companyDao)


    def "Get company data from registry with only mandatory fields"() {
        given: "Mocked data from registry API call"
        CompanyRegistryResponseDto companyRegistryResponseDto = objectMapper.readValue(new File("src/test/resources/static/ResponseFromRegistry.json"), CompanyRegistryResponseDto.class)

        when: "Calling registry"
        1 * restTemplate.exchange(_, _, _, _) >> ResponseEntity.of(Optional.of(companyRegistryResponseDto))
        1 * companyDao.save(_)
        Optional<Company> companyOptional = service.getCvrData("uni-tel", "dk", null, null)

        then: "Response contains a company"
        companyOptional.isPresent()
        with(companyOptional.get()) {
            vat == 30502566
            productionUnits.size() == 1
        }
    }

    def "Get company data from registry with specific search field"() {
        given: "Mocked data from registry API call"
        CompanyRegistryResponseDto companyRegistryResponseDto = objectMapper.readValue(new File("src/test/resources/static/ResponseFromRegistry.json"), CompanyRegistryResponseDto.class)

        when: "Calling registry"
        1 * restTemplate.exchange(_, _, _, _) >> ResponseEntity.of(Optional.of(companyRegistryResponseDto))
        1 * companyDao.save(_)
        Optional<Company> companyOptional = service.getCvrData("uni-tel", "dk", 30502566, null)

        then: "Response contains a company"
        companyOptional.isPresent()
        with(companyOptional.get()) {
            vat == 30502566
            productionUnits.size() == 1
        }
    }


    def "HttpClientErrorException.NotFound from API is converted to custom exception"() {

        when: "Calling registry"
        1 * restTemplate.exchange(_, _, _, _) >> { throw new HttpClientErrorException.NotFound(null, null, null, null) }
        0 * companyDao.save(_)
        service.getCvrData("uni-tel", "dk", null, null)

        then: "An exception is thrown"
        thrown(CompanyNotFoundException)
    }

    def "Throw exception if missing required fields"() {

        when: "Calling registry with missing exception"
        0 * restTemplate.exchange(_, _, _, _)
        0 * companyDao.save(_)
        service.getCvrData("uni-tel", null, null, null)

        then: "An exception is thrown"
        thrown(MissingRequiredParametersException)
    }

    def "Throw exception when calling with multiple specific fields"() {

        when: "Calling registry"
        0 * restTemplate.exchange(_, _, _, _)
        0 * companyDao.save(_)
        service.getCvrData("uni-tel", "dk", 30502566, "uni-tel")

        then: "An exception is thrown"
        thrown(OnlyOneSearchParameterAllowedException)
    }


}

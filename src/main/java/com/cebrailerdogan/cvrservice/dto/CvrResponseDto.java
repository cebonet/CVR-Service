package com.cebrailerdogan.cvrservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CvrResponseDto(
        long vat,
        String name,
        String address,
        @JsonProperty("zipcode")
        String zipCode,
        String city,
        @JsonProperty("cityname")
        String cityName,
        @JsonProperty("protected")
        boolean _protected,
        String phone,
        String email,
        String fax,
        @JsonProperty("startdate")
        String startDate,
        @JsonProperty("enddate")
        String endDate,
        String employees,
        @JsonProperty("addressco")
        String addressCo,
        @JsonProperty("industrycode")
        int industryCode,
        @JsonProperty("industryDesc")
        String industrydesc,
        @JsonProperty("companyCode")
        int companycode,
        @JsonProperty("companyDesc")
        String companydesc,
        @JsonProperty("creditStartDate")
        String creditstartdate,
        @JsonProperty("creditBankrupt")
        String creditbankrupt,
        @JsonProperty("creditStatus")
        String creditstatus,
        String owners,
        @JsonProperty("productionunits")
        List<ProductionUnitRegistryResponseDto> productionUnits,
        int t,
        int version) {
}

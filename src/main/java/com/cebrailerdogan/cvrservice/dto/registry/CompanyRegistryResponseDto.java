package com.cebrailerdogan.cvrservice.dto.registry;

import com.cebrailerdogan.cvrservice.dto.ProductionUnitResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class CompanyRegistryResponseDto {
        private long vat;
        private String name;
        private String address;
        private String zipCode;
        private String city;
        private String cityName;
        @JsonProperty("protected")
        private boolean _protected;
        private String phone;
        private String email;
        private String fax;
        private String startDate;
        private String endDate;
        private String employees;
        private String addressCo;
        private int industryCode;
        private String industryDesc;
        private int companyCode;
        private String companyDesc;
        private String creditStartDate;
        private String creditBankrupt;
        private String creditStatus;
        private String owners;
        private List<ProductionUnitResponseDto> productionUnits;
}


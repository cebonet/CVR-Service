package com.cebrailerdogan.cvrservice.dto.registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionUnitRegistryResponseDto {
    private Long pno;
    private boolean main;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String cityName;
    @JsonProperty("protected")
    private String _protected;
    private String phone;
    private String email;
    private String fax;
    private String startDate;
    private String endDate;
    private String employees;
    private String addressCo;
    private String industryCode;
    private String industryDesc;
}
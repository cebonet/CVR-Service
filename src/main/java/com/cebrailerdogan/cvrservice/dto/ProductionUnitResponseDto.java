package com.cebrailerdogan.cvrservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class ProductionUnitResponseDto implements Serializable {
    private Long pno;
    private boolean main;
    private String name;
    private String address;
    private String zipCode;
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
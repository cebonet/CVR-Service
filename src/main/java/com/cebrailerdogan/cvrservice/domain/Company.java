package com.cebrailerdogan.cvrservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public final class Company {

    @Id
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
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Owner> owners = new ArrayList<>();
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductionUnit> productionUnits = new ArrayList<>();
}

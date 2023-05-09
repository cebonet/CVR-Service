package com.cebrailerdogan.cvrservice.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    @OneToMany(mappedBy = "vat", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ProductionUnit> productionUnits;
}

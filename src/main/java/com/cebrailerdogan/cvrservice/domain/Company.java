package com.cebrailerdogan.cvrservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Company {
    @Id
    private long vat;
    private String name;
    private String address;
    @JsonProperty("zipcode")
    private String zipCode;
    private String city;
    @JsonProperty("cityname")
    private String cityName;
    @JsonProperty("protected")
    private boolean _protected;
    private String phone;
    private String email;
    private String fax;
    @JsonProperty("startdate")
    private String startDate;
    @JsonProperty("enddate")
    private String endDate;
    private String employees;
    @JsonProperty("addressco")
    private String addressCo;
    @JsonProperty("industrycode")
    private int industryCode;
    @JsonProperty("industryDesc")
    private String industrydesc;
    @JsonProperty("companyCode")
    private int companycode;
    @JsonProperty("companyDesc")
    private String companydesc;
    @JsonProperty("creditStartDate")
    private String creditstartdate;
    @JsonProperty("creditBankrupt")
    private String creditbankrupt;
    @JsonProperty("creditStatus")
    private String creditstatus;
    private String owners;
    @JsonProperty("productionunits")
    @OneToMany(mappedBy = "vat", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ProductionUnit> productionUnits;
    private int t;
    private int version;
}

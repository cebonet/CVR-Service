package com.cebrailerdogan.cvrservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class ProductionUnit {

    @Id
    Long pno;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "company")
    @JsonIgnore
    private Company company;
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

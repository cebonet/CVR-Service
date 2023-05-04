package com.cebrailerdogan.cvrservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class ProductionUnit {

    @Id
    Long pno;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vat_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Company vat;

    boolean main;
    String name;
    String address;
}

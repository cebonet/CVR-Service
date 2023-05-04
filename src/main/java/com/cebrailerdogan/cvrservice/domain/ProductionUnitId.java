package com.cebrailerdogan.cvrservice.domain;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class ProductionUnitId {

    @NotNull
    private Long pno;

    @NotNull
    private Long vat;
}

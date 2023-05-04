package com.cebrailerdogan.cvrservice.dto;

import jakarta.persistence.Entity;


public record ProductionUnitRegistryResponseDto(
        Long pno,
        boolean main,
        String name,

        String address
) {
}

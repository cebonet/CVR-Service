package com.cebrailerdogan.cvrservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public final class Owner {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "company")
    @JsonIgnore
    Company company;
    private  String name;

}

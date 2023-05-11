package com.cebrailerdogan.cvrservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CvrServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvrServiceApplication.class, args);
    }

}

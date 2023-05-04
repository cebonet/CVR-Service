package com.cebrailerdogan.cvrservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CvrServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvrServiceApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


}

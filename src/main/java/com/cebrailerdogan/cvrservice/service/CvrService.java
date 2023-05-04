package com.cebrailerdogan.cvrservice.service;

import com.cebrailerdogan.cvrservice.dto.CvrResponseDto;
import org.springframework.stereotype.Service;


interface CvrService {
    CvrResponseDto getCvrData(String search, String country, Long vat, String name);

    CvrResponseDto getCachedCvrData();
}

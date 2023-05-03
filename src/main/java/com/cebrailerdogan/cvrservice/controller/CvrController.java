package com.cebrailerdogan.cvrservice.controller;

import com.cebrailerdogan.cvrservice.dto.CvrResponseDto;
import com.cebrailerdogan.cvrservice.service.CvrRegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/v1/cvr")
@RequiredArgsConstructor
public class CvrController {

    private final CvrRegistryService cvrService;

    @GetMapping
    ResponseEntity<CvrResponseDto> getCvrData(@RequestParam String search, @RequestParam String country){
        return ResponseEntity.ok().body(cvrService.getCvrData(search, country));
    }

    @GetMapping("cached")
    ResponseEntity<CvrResponseDto> getCachedCvrData(){
        return ResponseEntity.ok().body(cvrService.getCachedCvrData());
    }

}

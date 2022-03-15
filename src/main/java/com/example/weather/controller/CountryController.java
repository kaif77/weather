package com.example.weather.controller;

import com.example.weather.service.CountryService;
import com.example.weather.service.impl.CountryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @PostMapping(path ="/create-country")
    public ResponseEntity<?> createCountry(double lan, double lon){
        return this.countryService.createCountry(lan, lon);
    }

    @GetMapping(path ="/get-active-country")
    public List<CountryImpl> getCountries(){
        return this.countryService.getActiveCountry();
    }

    @PutMapping(path ="/delete-country")
    public ResponseEntity<?> getCountries(Long id){
        return this.countryService.deleteCountry(id);
    }
}

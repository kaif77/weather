package com.example.weather.controller;

import com.example.weather.model.WeatherData;
import com.example.weather.service.WeatherDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherDataController {
    private WeatherDataService weatherDataService;

    public WeatherDataController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @GetMapping(path = "/weather-data/get-all")
    public List<WeatherData> getAllData(){
        return this.weatherDataService.getActiveWeatherData();
    }
}

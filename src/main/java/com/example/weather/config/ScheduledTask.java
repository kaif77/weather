package com.example.weather.config;

import com.example.weather.service.WeatherDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private WeatherDataService weatherDataService;

    public ScheduledTask(WeatherDataService weatherDataService){
        this.weatherDataService = weatherDataService;
    }

//    calling openWeatherData hourly
    @Scheduled(cron = "@hourly",zone = "Asia/Kolkata")
    public void scheduleTaskUsingCronExpression() {
        this.weatherDataService.WeatherData();
    }
}

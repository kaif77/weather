package com.example.weather.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdDate;
    private String country;
    private String city;
    private double latitude;
    private double longitude;
    private String weather;
    private String weatherDescription;
    private double temperature;
    private boolean archived;

    public WeatherData() {
    }

    public WeatherData(String country, String city, double latitude, double longitude, String weather,
                       String weatherDescription, double temperature,Date createdDate,boolean archived) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.createdDate = createdDate;
        this.archived = archived;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}

package com.example.weather.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
    private long id;
    private double longitude;
    private double latitude;
    private String countryCode;
    private String name;
    private boolean archived;

    public Country() {
    }

    public Country(Long Id, double longitude, double latitude, String countryCode, String name, boolean archived) {
        this.id = Id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.countryCode = countryCode;
        this.name = name;
        this.archived = archived;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}

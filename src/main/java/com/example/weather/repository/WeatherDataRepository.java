package com.example.weather.repository;

import com.example.weather.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData,Long> {

    List<WeatherData> getAllByArchivedIsFalse();

    @Query(value = "select * from weather_data w where w.city= ?1 and w.country= ?2 and w.archived=false ",nativeQuery = true)
    List<WeatherData> getActiveWeatherData(String city, String country_code);
}

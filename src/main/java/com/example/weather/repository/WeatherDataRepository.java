package com.example.weather.repository;

import com.example.weather.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData,Long> {

    List<WeatherData> getAllByArchivedIsFalse();
}

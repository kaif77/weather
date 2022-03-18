package com.example.weather.repository;

import com.example.weather.model.Country;
import com.example.weather.service.impl.CountryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> getAllByArchivedIsFalse();

    @Query(value = "select id as 'ID',name as 'Country' from country where archived=false order by name",nativeQuery = true)
    List<CountryImpl> getCountryNames();
}

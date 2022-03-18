package com.example.weather.service;

import com.example.weather.model.Country;
import com.example.weather.repository.CountryRepository;
import com.example.weather.service.impl.CountryImpl;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final WeatherDataService weatherDataService;

    public CountryService(CountryRepository countryRepository, WeatherDataService weatherDataService){
        this.countryRepository = countryRepository;
        this.weatherDataService = weatherDataService;
    }

//    creating new country based on latitude and longitude
    public ResponseEntity<String> createCountry(double ulat, double ulon){
        final String uri = "https://api.openweathermap.org/data/2.5/weather";
        String APPID = "d9b1616ce8363168c45c25a1f0679340";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri + "?lat=" + ulat +"&lon="+ulon+ "&APPID=" + APPID, String.class);
        JSONObject jsonObject = new JSONObject(result);
        System.out.println();
        if(jsonObject.getString("name").trim().equals("")){
            return ResponseEntity.ok("No Cities in the given Coordinates");
        }
        Long countryId = jsonObject.getLong("id");
        Country countryAvailable = this.countryRepository.findById(jsonObject.getLong("id")).orElse(null);
//        if country is not added in database
        if(countryAvailable==null){
            Country newCountry = new Country(countryId,jsonObject.getJSONObject("coord").getDouble("lat"),
                    jsonObject.getJSONObject("coord").getDouble("lon"),jsonObject.getJSONObject("sys").getString("country"),
                    jsonObject.getString("name"),false);
            this.countryRepository.save(newCountry);
        }
        else {
//            if country has been already added in database
            if(countryAvailable.isArchived()){
                countryAvailable.setArchived(false);
                this.countryRepository.save(countryAvailable);
            }
//            when user add the same country
            else {
                return ResponseEntity.ok("City Already Added");
            }
        }
//        if a new country or activating an existing country get weather data for that country
        String para = jsonObject.getString("name")+','+jsonObject.getJSONObject("sys").getString("country");
        JSONObject jsonObject1 = this.weatherDataService.callOpenWeather(para);
        this.weatherDataService.setWeatherData(jsonObject1);
        return ResponseEntity.ok(("Successfully Added"));
    }



//    returning sorted country list
    public List<CountryImpl> getActiveCountry(){
        return  this.countryRepository.getCountryNames();
    }

//    deleting a country from user view
    public ResponseEntity<String> deleteCountry(Long id){
        Country country = this.countryRepository.findById(id).orElseThrow(null);
        if(country != null){
            country.setArchived(true);
            this.countryRepository.save(country);
            this.weatherDataService.deleteWeatherData(country.getName(),country.getCountryCode());
            return ResponseEntity.ok("City is Deleted");
        }
        else {
            return ResponseEntity.ok("City Not Found");
        }
    }
}

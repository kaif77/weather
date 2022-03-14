package com.example.weather.service;

import com.example.weather.model.Country;
import com.example.weather.model.WeatherData;
import com.example.weather.repository.CountryRepository;
import com.example.weather.repository.WeatherDataRepository;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherDataService {
    private final WeatherDataRepository weatherDataRepository;
    private final CountryRepository countryRepository;

//  weatherData service constructor
    public WeatherDataService(WeatherDataRepository weatherDataRepository, CountryRepository countryRepository){
        this.weatherDataRepository = weatherDataRepository;
        this.countryRepository = countryRepository;
    }

//    find active weather data from database
    public List<WeatherData> getActiveWeatherData(){
        return this.weatherDataRepository.getAllByArchivedIsFalse();
    }

    public void WeatherData() {
        List<String> countries = getCountry();

        for (String p : countries) {
            JSONObject jsonObject = callOpenWeather(p);
            setWeatherData(jsonObject);
        }
    }

//    mapping api object to weatherData class
    public void setWeatherData(JSONObject jsonObject){
        JSONObject coord = jsonObject.getJSONObject("coord");
        JSONObject temp = jsonObject.getJSONObject("main");
        JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
        WeatherData weatherData = new WeatherData(
                jsonObject.getJSONObject("sys").getString("country"), jsonObject.getString("name"),
                coord.getDouble("lat"), coord.getDouble("lon"), weather.getString("main"),
                weather.getString("description"), temp.getDouble("temp"), getDate(),false
        );
        saveWeatherData(weatherData);
    }

//    save weather data in database
    public void saveWeatherData(WeatherData weatherData) {
        this.weatherDataRepository.save(weatherData);
    }

//    finding active country from database
    public List<String> getCountry() {
        List<String> para = new ArrayList();
        List<Country> country = this.countryRepository.getAllByArchivedIsFalse();
        for (int a = 0; a < country.size(); a++) {
            para.add(country.get(a).getName() + "," + country.get(a).getCountryCode());
        }
        return para;
    }

//    Generate current Date and Time
    public Date getDate() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date dateTime = ts;
        return dateTime;
    }

//    calling openWeatherData api
    public JSONObject callOpenWeather(String paras) {
        final String uri = "https://api.openweathermap.org/data/2.5/weather";
        String q = paras;
        String APPID = "d9b1616ce8363168c45c25a1f0679340";
        waiting();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri + "?q=" + q + "&APPID=" + APPID, String.class);
        return new JSONObject(result);
    }

//    setting wait time to keep the request count below 60 per minute
    @Async
    void waiting() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getDate());
    }
}

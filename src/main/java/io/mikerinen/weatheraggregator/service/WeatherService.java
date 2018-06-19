package io.mikerinen.weatheraggregator.service;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.repository.WeatherDataRepository;
import io.mikerinen.weatheraggregator.webservice.openweathermap.OpenWeatherMapService;
import io.mikerinen.weatheraggregator.webservice.yahoo.YahooWeatherService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
@Service("weatherService")
public class WeatherService {

    @Resource(name = "weatherDataRepository")
    private WeatherDataRepository weatherDataRepository;

    @Resource(name = "openWeatherMapService")
    OpenWeatherMapService openWeatherMapService;

    @Resource(name = "yahooWeatherService")
    YahooWeatherService yahooWeatherService;

    public WeatherData getWeatherDataForLocation(String location) {
        Optional<WeatherData> weatherFromDB = Optional.ofNullable(weatherDataRepository.findByLocationName(location));

        if (weatherFromDB.isPresent()) {
            LocalDateTime twoHoursAgo = LocalDateTime.now().minusMinutes(120);

            if (twoHoursAgo.isAfter(weatherFromDB.get().getUpdated())) {
                return fetchWeatherFromAPIs(location);
                // TODO: assign a confidence score for the current weather
                // TODO: save the temperature original values to a list
            } else
                return weatherFromDB.get();
        } else
            return fetchWeatherFromAPIs(location);
    }

    private WeatherData fetchWeatherFromAPIs(String location) {
        WeatherData openWeatherMapData = openWeatherMapService.getWeatherDataForLocation(location);
        WeatherData yahooWeatherData = yahooWeatherService.getWeatherDataForLocation(location);

        yahooWeatherData.setLongDescription("OpenWeatherMap Temp: " + openWeatherMapData.getTemperature() + ", YahooWeather Temp: " + yahooWeatherData.getTemperature());

        if (openWeatherMapData.getTemperature().equals(yahooWeatherData.getTemperature()))
            yahooWeatherData.setTemperature(yahooWeatherData.getTemperature());
        else {
            yahooWeatherData.setTemperature(
                    (openWeatherMapData.getTemperature() + yahooWeatherData.getTemperature()) / 2);
        }
        return yahooWeatherData;
    }
}

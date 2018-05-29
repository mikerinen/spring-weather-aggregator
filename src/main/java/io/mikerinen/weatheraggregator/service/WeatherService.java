package io.mikerinen.weatheraggregator.service;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.repository.WeatherDataRepository;
import io.mikerinen.weatheraggregator.webservice.openweathermap.OpenWeatherMapService;
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

    public WeatherData getWeatherDataForLocation(String location) {
        Optional<WeatherData> weatherFromDB = Optional.ofNullable(weatherDataRepository.findByLocationName(location));

        if (weatherFromDB.isPresent()) {
            LocalDateTime twoHoursAgo = LocalDateTime.now().minusMinutes(120);

            if (twoHoursAgo.isAfter(weatherFromDB.get().getUpdated()))
                return openWeatherMapService.getWeatherDataForLocation(location);
            else
                return weatherFromDB.get();
        } else
            return openWeatherMapService.getWeatherDataForLocation(location);
    }
}

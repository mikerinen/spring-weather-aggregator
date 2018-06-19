package io.mikerinen.weatheraggregator.webservice.openweathermap;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.webservice.openweathermap.model.OpenWeatherMapResponse;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
@Service("openWeatherMapService")
public class OpenWeatherMapService {

    @Resource(name = "openWeatherMapClient")
    private OpenWeatherMapClient openWeatherMapClient;

    Clock clock = Clock.systemDefaultZone();

    public WeatherData getWeatherDataForLocation(String location) {
        ResponseEntity<OpenWeatherMapResponse> responseEntity = openWeatherMapClient.fetchWeatherData(location);

        Optional<OpenWeatherMapResponse> responseOptional = Optional.ofNullable(responseEntity.getBody());

        if (responseEntity.getStatusCode().is2xxSuccessful() && responseOptional.isPresent()) {
            OpenWeatherMapResponse response = responseOptional.get();
            WeatherData weatherData = new WeatherData();

            weatherData.setUpdated(LocalDateTime.now(clock));
            weatherData.setTemperature(response.getMainInformation().getTemperature());
            weatherData.setAirPressure(response.getMainInformation().getPressure());
            weatherData.setHumidityPercentage(response.getMainInformation().getHumidity());
            weatherData.setTemperatureMax(response.getMainInformation().getTemperatureMax());
            weatherData.setTemperatureMin(response.getMainInformation().getTemperatureMin());

            weatherData.setDescription(response.getWeather().get(0).getMain());
            weatherData.setIcon(response.getWeather().get(0).getIcon());
            weatherData.setLongDescription(response.getWeather().get(0).getDescription());

            weatherData.setLocationName(response.getLocationName());
            weatherData.setWindDirectionDegrees(response.getWind().getDegrees());
            weatherData.setWindSpeedMetersPerSecond(response.getWind().getSpeed());

            return weatherData;

        } else
            return new WeatherData();
    }
}
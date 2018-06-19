package io.mikerinen.weatheraggregator.webservice.yahoo;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.webservice.yahoo.model.YahooWeatherResponse;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Setter
@Service("yahooWeatherService")
public class YahooWeatherService {

    @Resource(name = "yahooWeatherClient")
    private YahooWeatherClient yahooWeatherClient;

    public WeatherData getWeatherDataForLocation(String location) {
        ResponseEntity<YahooWeatherResponse> responseEntity = yahooWeatherClient.fetchWeatherData(location);

        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {

            YahooWeatherResponse response = responseEntity.getBody();

            WeatherData weatherData = new WeatherData();
            weatherData.setUpdated(LocalDateTime.now());
            weatherData.setTemperature(response.getQuery().getResults().getChannel().getItem().getCondition().getTemp());
            weatherData.setAirPressure(response.getQuery().getResults().getChannel().getAtmosphere().getPressure());
            weatherData.setHumidityPercentage(response.getQuery().getResults().getChannel().getAtmosphere().getHumidity());
            weatherData.setTemperatureMax(response.getQuery().getResults().getChannel().getItem().getForecasts().get(0).getHigh());
            weatherData.setTemperatureMin(response.getQuery().getResults().getChannel().getItem().getForecasts().get(0).getLow());

            weatherData.setDescription(response.getQuery().getResults().getChannel().getItem().getCondition().getText());
            weatherData.setIcon(response.getQuery().getResults().getChannel().getImage().getUrl());
            weatherData.setLongDescription(response.getQuery().getResults().getChannel().getItem().getCondition().getText());

            weatherData.setLocationName(response.getQuery().getResults().getChannel().getLocation().getCity());
            weatherData.setWindDirectionDegrees(response.getQuery().getResults().getChannel().getWind().getDirection());
            weatherData.setWindSpeedMetersPerSecond(response.getQuery().getResults().getChannel().getWind().getSpeed());

            return weatherData;

        } else
            return new WeatherData();
    }
}

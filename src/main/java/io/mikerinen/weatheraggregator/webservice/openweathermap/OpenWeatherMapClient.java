package io.mikerinen.weatheraggregator.webservice.openweathermap;

import io.mikerinen.weatheraggregator.webservice.openweathermap.model.OpenWeatherMapResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Setter
@Component("openWeatherMapClient")
public class OpenWeatherMapClient {

    private final RestTemplate restTemplate;
    private String apiKey;
    private static String WEATHER_API =
            "http://samples.openweathermap.org/data/2.5/weather?q={location}&appid=";

    public OpenWeatherMapClient(@Value("${open.weather.map.api-key}") String apiKey,
                                RestTemplateBuilder restTemplateBuilder) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<OpenWeatherMapResponse> fetchWeatherData(String location) {
        return this.restTemplate.getForEntity(WEATHER_API + apiKey, OpenWeatherMapResponse.class, location);
    }
}

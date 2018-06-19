package io.mikerinen.weatheraggregator.webservice.yahoo;

import io.mikerinen.weatheraggregator.webservice.yahoo.model.YahooWeatherResponse;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Setter
@Component("yahooWeatherClient")
public class YahooWeatherClient {

    private final RestTemplate restTemplate;
    private static String WEATHER_API_ENDPOINT =
            "https://query.yahooapis.com/v1/public/yql?q=" +
                    "select * from weather.forecast where woeid in " +
                    "(select woeid from geo.places(1) " +
                    "where text=\"{location}\") " +
                    "and u='c'&format=json&env=store";

    public YahooWeatherClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    ResponseEntity<YahooWeatherResponse> fetchWeatherData(String location) {
        return this.restTemplate.getForEntity(
                WEATHER_API_ENDPOINT, YahooWeatherResponse.class, location);
    }
}

package io.mikerinen.weatheraggregator;

import io.mikerinen.weatheraggregator.webservice.openweathermap.OpenWeatherMapProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties(OpenWeatherMapProperties.class)
@PropertySource("classpath:application.properties")
public class WeatherAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAggregatorApplication.class, args);
    }
}
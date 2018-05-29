package io.mikerinen.weatheraggregator.controller;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.service.WeatherService;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Setter
@RestController("weatherController")
public class WeatherController {

    @Resource(name = "weatherService")
    WeatherService weatherService;

    @GetMapping("/weather/{location}")
    @ResponseBody
    public WeatherData getWeatherDataForLocation(@PathVariable String location) {
        return weatherService.getWeatherDataForLocation(location);
    }
}
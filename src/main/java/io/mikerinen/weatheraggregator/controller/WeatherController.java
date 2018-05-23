package io.mikerinen.weatheraggregator.controller;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.repository.WeatherDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("weatherController")
public class WeatherController {

    @Resource(name = "weatherDataRepository")
    private WeatherDataRepository weatherDataRepository;

    @GetMapping("/weather/{location}")
    @ResponseBody
    public WeatherData getWeatherDataForLocation(@PathVariable String location) {
        return weatherDataRepository.findByLocationName(location);
    }
}
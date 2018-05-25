package io.mikerinen.weatheraggregator.webservice.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Main {

    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double temperatureMax;
    private Double temperatureMin;

    @JsonCreator
    public Main(@JsonProperty("temp") Double temperature,
                @JsonProperty("pressure") Double pressure,
                @JsonProperty("humidity") Double humidity,
                @JsonProperty("temp_max") Double temperatureMax,
                @JsonProperty("temp_min") Double temperatureMin) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
    }
}

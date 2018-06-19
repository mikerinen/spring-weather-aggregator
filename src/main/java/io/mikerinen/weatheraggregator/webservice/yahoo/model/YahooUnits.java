package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooUnits {

    private String distance;
    private String pressure;
    private String speed;
    private String temperature;

    @JsonCreator
    public YahooUnits(@JsonProperty("distance") String distance,
                      @JsonProperty("pressure") String pressure,
                      @JsonProperty("speed") String speed,
                      @JsonProperty("temperature") String temperature) {
        this.distance = distance;
        this.pressure = pressure;
        this.speed = speed;
        this.temperature = temperature;
    }
}

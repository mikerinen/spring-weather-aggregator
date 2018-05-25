package io.mikerinen.weatheraggregator.webservice.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Coordinates {

    private Double longitude;
    private Double latitude;

    @JsonCreator
    public Coordinates(@JsonProperty("lon") Double longitude,
                       @JsonProperty("lat") Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}

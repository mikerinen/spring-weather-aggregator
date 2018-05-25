package io.mikerinen.weatheraggregator.webservice.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Clouds {

    private Long all;

    @JsonCreator
    public Clouds(@JsonProperty("all") Long all) {
        this.all = all;
    }
}

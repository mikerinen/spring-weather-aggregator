package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooAstronomy {

    private String sunrise;
    private String sunset;

    @JsonCreator
    public YahooAstronomy(@JsonProperty("sunrise") String sunrise,
                          @JsonProperty("sunset") String sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
}

package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooWind {

    private Double chill;
    private Double direction;
    private Double speed;

    @JsonCreator
    public YahooWind(@JsonProperty("chill") Double chill,
                     @JsonProperty("direction") Double direction,
                     @JsonProperty("speed") Double speed) {
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
    }
}

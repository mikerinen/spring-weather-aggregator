package io.mikerinen.weatheraggregator.webservice.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Wind {

    private Double speed;
    private Double degrees;

    @JsonCreator
    public Wind(@JsonProperty("speed") Double speed,
                @JsonProperty("degrees") Double degrees) {
        this.speed = speed;
        this.degrees = degrees;
    }
}

package io.mikerinen.weatheraggregator.webservice.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sys {

    private Long type;
    private Long id;
    private Double message;
    private String countryCode;
    private Long sunriseTimestamp;
    private Long sunsetTimestamp;

    @JsonCreator
    public Sys(@JsonProperty("type") Long type,
               @JsonProperty("id") Long id,
               @JsonProperty("message") Double message,
               @JsonProperty("country") String countryCode,
               @JsonProperty("sunrise") Long sunriseTimestamp,
               @JsonProperty("sunset") Long sunsetTimestamp) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.countryCode = countryCode;
        this.sunriseTimestamp = sunriseTimestamp;
        this.sunsetTimestamp = sunsetTimestamp;
    }
}

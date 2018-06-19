package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooLocation {

    private String city;
    private String country;
    private String region;

    @JsonCreator
    public YahooLocation(@JsonProperty("city") String city,
                         @JsonProperty("country") String country,
                         @JsonProperty("region") String region) {
        this.city = city;
        this.country = country;
        this.region = region;
    }
}

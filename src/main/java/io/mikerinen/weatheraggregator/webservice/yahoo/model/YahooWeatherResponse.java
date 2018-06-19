package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooWeatherResponse {

    private YahooQuery query;

    @JsonCreator
    public YahooWeatherResponse(@JsonProperty("query") YahooQuery query) {
        this.query = query;
    }
}

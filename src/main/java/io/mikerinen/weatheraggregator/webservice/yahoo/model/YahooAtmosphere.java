package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooAtmosphere {

    private Double humidity;
    private Double pressure;
    private Double rising;
    private Double visibility;

    @JsonCreator
    public YahooAtmosphere(@JsonProperty("humidity") Double humidity,
                           @JsonProperty("pressure") Double pressure,
                           @JsonProperty("rising") Double rising,
                           @JsonProperty("visibility") Double visibility) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.rising = rising;
        this.visibility = visibility;
    }
}

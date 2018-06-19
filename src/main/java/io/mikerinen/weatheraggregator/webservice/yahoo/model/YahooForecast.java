package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooForecast {

    private String code;
    private String date;
    private String day;
    private Double high;
    private Double low;
    private String text;

    @JsonCreator
    public YahooForecast(@JsonProperty("code") String code,
                         @JsonProperty("date") String date,
                         @JsonProperty("day") String day,
                         @JsonProperty("high") Double high,
                         @JsonProperty("low") Double low,
                         @JsonProperty("text") String text) {
        this.code = code;
        this.date = date;
        this.day = day;
        this.high = high;
        this.low = low;
        this.text = text;
    }
}

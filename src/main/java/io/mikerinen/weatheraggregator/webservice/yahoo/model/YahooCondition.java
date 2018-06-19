package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooCondition {

    private String code;
    private String date;
    private Double temp;
    private String text;

    @JsonCreator
    public YahooCondition(@JsonProperty("code") String code,
                          @JsonProperty("date") String date,
                          @JsonProperty("temp") Double temp,
                          @JsonProperty("text") String text) {
        this.code = code;
        this.date = date;
        this.temp = temp;
        this.text = text;
    }
}

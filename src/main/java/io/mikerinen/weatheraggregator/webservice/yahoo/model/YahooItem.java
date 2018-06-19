package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class YahooItem {

    private YahooCondition condition;
    private List<YahooForecast> forecasts;
    private String title;
    private String latitude;
    private String longitude;
    private String link;
    private String pubDate;

    @JsonCreator
    public YahooItem(@JsonProperty("condition") YahooCondition condition,
                     @JsonProperty("forecast") List<YahooForecast> forecasts,
                     @JsonProperty("title") String title,
                     @JsonProperty("latitude") String latitude,
                     @JsonProperty("longitude") String longitude,
                     @JsonProperty("link") String link,
                     @JsonProperty("pubDate") String pubDate) {
        this.condition = condition;
        this.forecasts = forecasts;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.link = link;
        this.pubDate = pubDate;
    }
}

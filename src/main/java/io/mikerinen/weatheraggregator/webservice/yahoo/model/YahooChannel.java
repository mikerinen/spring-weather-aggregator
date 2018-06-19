package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooChannel {

    private YahooUnits units;
    private String title;
    private String link;
    private String description;
    private String language;
    private String lastBuildDate;
    private String timeToLive;
    private YahooLocation location;
    private YahooWind wind;
    private YahooAtmosphere atmosphere;
    private YahooAstronomy astronomy;
    private YahooImage image;
    private YahooItem item;

    @JsonCreator
    public YahooChannel(@JsonProperty("units") YahooUnits units,
                        @JsonProperty("title") String title,
                        @JsonProperty("link") String link,
                        @JsonProperty("description") String description,
                        @JsonProperty("language") String language,
                        @JsonProperty("lastBuildDate") String lastBuildDate,
                        @JsonProperty("ttl") String timeToLive,
                        @JsonProperty("location") YahooLocation location,
                        @JsonProperty("wind") YahooWind wind,
                        @JsonProperty("atmosphere") YahooAtmosphere atmosphere,
                        @JsonProperty("astronomy") YahooAstronomy astronomy,
                        @JsonProperty("image") YahooImage image,
                        @JsonProperty("item") YahooItem item) {
        this.units = units;
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.lastBuildDate = lastBuildDate;
        this.timeToLive = timeToLive;
        this.location = location;
        this.wind = wind;
        this.atmosphere = atmosphere;
        this.astronomy = astronomy;
        this.image = image;
        this.item = item;
    }
}

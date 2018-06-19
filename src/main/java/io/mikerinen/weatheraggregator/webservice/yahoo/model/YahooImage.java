package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooImage {

    private String title;
    private Integer width;
    private Integer height;
    private String link;
    private String url;

    @JsonCreator
    public YahooImage(@JsonProperty("title") String title,
                      @JsonProperty("width") Integer width,
                      @JsonProperty("height") Integer height,
                      @JsonProperty("link") String link,
                      @JsonProperty("url") String url) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.link = link;
        this.url = url;
    }
}

package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class YahooQuery {

    private Integer count;
    private LocalDateTime created;
    private String lang;
    private YahooResults results;

    @JsonCreator
    public YahooQuery(@JsonProperty("count") Integer count,
                      @JsonProperty("created") LocalDateTime created,
                      @JsonProperty("lang") String lang,
                      @JsonProperty("results") YahooResults results) {
        this.count = count;
        this.created = created;
        this.lang = lang;
        this.results = results;
    }
}

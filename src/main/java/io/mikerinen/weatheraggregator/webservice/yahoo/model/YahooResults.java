package io.mikerinen.weatheraggregator.webservice.yahoo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YahooResults {

    private YahooChannel channel;

    @JsonCreator
    public YahooResults(@JsonProperty("channel") YahooChannel channel) {
        this.channel = channel;
    }
}

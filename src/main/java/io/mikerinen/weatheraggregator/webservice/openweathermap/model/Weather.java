package io.mikerinen.weatheraggregator.webservice.openweathermap.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Weather {

    private Long id;
    private String main;
    private String description;
    private String icon;

    @JsonCreator
    public Weather(@JsonProperty("id") Long id,
                   @JsonProperty("main") String main,
                   @JsonProperty("description") String description,
                   @JsonProperty("icon") String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }
}

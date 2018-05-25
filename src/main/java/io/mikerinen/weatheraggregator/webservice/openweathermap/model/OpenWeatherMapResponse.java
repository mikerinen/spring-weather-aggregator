package io.mikerinen.weatheraggregator.webservice.openweathermap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherMapResponse {

    private Coordinates coordinates;
    private List<Weather> weather;
    private String base;
    private Main mainInformation;
    private Long visibility;
    private Wind wind;
    private Clouds clouds;
    private Long dt;
    private Sys system;
    private Long id;
    private String locationName;
    private Long cod;

    @JsonCreator
    public OpenWeatherMapResponse(@JsonProperty("coord") Coordinates coordinates,
                                  @JsonProperty("weather") List<Weather> weather,
                                  @JsonProperty("base") String base,
                                  @JsonProperty("main") Main mainInformation,
                                  @JsonProperty("visibility") Long visibility,
                                  @JsonProperty("wind") Wind wind,
                                  @JsonProperty("clouds") Clouds clouds,
                                  @JsonProperty("dt") Long dt,
                                  @JsonProperty("sys") Sys system,
                                  @JsonProperty("id") Long id,
                                  @JsonProperty("name") String locationName,
                                  @JsonProperty("cod") Long cod) {
        this.coordinates = coordinates;
        this.weather = weather;
        this.base = base;
        this.mainInformation = mainInformation;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.system = system;
        this.id = id;
        this.locationName = locationName;
        this.cod = cod;
    }
}

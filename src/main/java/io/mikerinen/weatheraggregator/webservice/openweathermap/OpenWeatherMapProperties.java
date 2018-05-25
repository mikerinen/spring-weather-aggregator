package io.mikerinen.weatheraggregator.webservice.openweathermap;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ConfigurationProperties("open.weather.map")
@Validated
public class OpenWeatherMapProperties {

    /**
     * Open Weather Map API Key. Example: b6907d289e10d714a6e88b30761fae22
     */
    @Pattern(regexp = "([a-f]|[0-9]){32}")
    private String apiKey;

}
package io.mikerinen.weatheraggregator.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Entity
public class WeatherData implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String locationName;
    private Double temperature;
    private Double temperatureMax;
    private Double temperatureMin;
    private Double airPressure;
    private Double humidityPercentage;
    private Double windSpeedMetersPerSecond;
    private Double windDirectionDegrees;
    private String description;
    private String longDescription;
    private String icon;
    private LocalDateTime updated;
}
package io.mikerinen.weatheraggregator.webservice.openweathermap;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.webservice.openweathermap.model.*;
import lombok.Setter;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

@Setter
@RunWith(JMockit.class)
public class OpenWeatherMapServiceTest {

    public static final String TEST_LOCATION = "Helsinki";
    public static final double AIR_PRESSURE = 1000.0;
    public static final String MAIN_DESCRIPTION = "Rain";
    public static final String LONG_DESCRIPTION = "Light rain";
    public static final String ICON = "rain-clouds";
    public static final double TEMPERATURE = 10.0;
    public static final double TEMPERATURE_MAX = 12.0;
    public static final double TEMPERATURE_MIN = 9.0;
    public static final double HUMIDITY = 90.0;
    public static final double WIND_SPEED = 3.0;
    public static final double WIND_DIRECTION = 90.0;

    @Mocked
    private OpenWeatherMapClient openWeatherMapClientStub;

    @Test
    public void testGetWeatherDataForLocation() {
        // setup
        new Expectations() {{
            openWeatherMapClientStub.fetchWeatherData(TEST_LOCATION);
            result = new ResponseEntity<>(getOpenWeatherMapResponse(), HttpStatus.OK);
        }};

        OpenWeatherMapService openWeatherMapService = new OpenWeatherMapService();
        openWeatherMapService.setOpenWeatherMapClient(openWeatherMapClientStub);

        // execute
        WeatherData weatherData = openWeatherMapService.getWeatherDataForLocation(TEST_LOCATION);

        // verify
        assertThat(weatherData.getUpdated().isBefore(LocalDateTime.now()), is(true));
        assertThat(weatherData.getLocationName(), is(TEST_LOCATION));
        assertThat(weatherData.getAirPressure(), is(AIR_PRESSURE));
        assertThat(weatherData.getDescription(), is(MAIN_DESCRIPTION));
        assertThat(weatherData.getLongDescription(), is(LONG_DESCRIPTION));
        assertThat(weatherData.getIcon(), is(ICON));
        assertThat(weatherData.getTemperature(), is(TEMPERATURE));
        assertThat(weatherData.getTemperatureMax(), is(TEMPERATURE_MAX));
        assertThat(weatherData.getTemperatureMin(), is(TEMPERATURE_MIN));
        assertThat(weatherData.getHumidityPercentage(), is(HUMIDITY));
        assertThat(weatherData.getWindSpeedMetersPerSecond(), is(WIND_SPEED));
        assertThat(weatherData.getWindDirectionDegrees(), is(WIND_DIRECTION));
    }

    @Test
    public void testGetWeatherDataForLocationNotFound() {
        // setup
        new Expectations() {{
            openWeatherMapClientStub.fetchWeatherData(TEST_LOCATION);
            result = new ResponseEntity<>(getOpenWeatherMapResponse(), HttpStatus.NOT_FOUND);
        }};

        OpenWeatherMapService openWeatherMapService = new OpenWeatherMapService();
        openWeatherMapService.setOpenWeatherMapClient(openWeatherMapClientStub);

        // execute
        WeatherData weatherData = openWeatherMapService.getWeatherDataForLocation(TEST_LOCATION);

        // verify
        assertThat(weatherData.getUpdated(), is(nullValue()));
        assertThat(weatherData.getLocationName(), is(nullValue()));
        assertThat(weatherData.getAirPressure(), is(nullValue()));
        assertThat(weatherData.getDescription(), is(nullValue()));
        assertThat(weatherData.getLongDescription(), is(nullValue()));
        assertThat(weatherData.getIcon(), is(nullValue()));
        assertThat(weatherData.getTemperature(), is(nullValue()));
        assertThat(weatherData.getTemperatureMax(), is(nullValue()));
        assertThat(weatherData.getTemperatureMin(), is(nullValue()));
        assertThat(weatherData.getHumidityPercentage(), is(nullValue()));
        assertThat(weatherData.getWindSpeedMetersPerSecond(), is(nullValue()));
        assertThat(weatherData.getWindDirectionDegrees(), is(nullValue()));
    }

    private OpenWeatherMapResponse getOpenWeatherMapResponse() {
        return new OpenWeatherMapResponse(
                new Coordinates(1.0, 2.0),
                new ArrayList<Weather>() {{
                    add(new Weather(1L, MAIN_DESCRIPTION, LONG_DESCRIPTION, ICON));
                }},
                "stations",
                new Main(TEMPERATURE, AIR_PRESSURE, HUMIDITY, TEMPERATURE_MAX, TEMPERATURE_MIN),
                10L,
                new Wind(WIND_SPEED, WIND_DIRECTION),
                new Clouds(1L),
                10L, new Sys(1L, 1L, 1.0, "FIN", 1111L, 1112L),
                1L, "Helsinki", 1L);
    }
}
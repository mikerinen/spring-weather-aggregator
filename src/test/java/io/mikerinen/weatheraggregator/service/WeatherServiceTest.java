package io.mikerinen.weatheraggregator.service;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.repository.WeatherDataRepository;
import io.mikerinen.weatheraggregator.webservice.openweathermap.OpenWeatherMapService;
import io.mikerinen.weatheraggregator.webservice.yahoo.YahooWeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class WeatherServiceTest {

    private static final String TEST_LOCATION = "Helsinki";

    private Clock fixedClock = Clock.fixed(Instant.parse("2000-01-01T00:00:00.00Z"), ZoneId.systemDefault());

    @Test
    public void testGetWeatherDataForLocationNotFoundInDB() {
        // setup
        WeatherDataRepository weatherDataRepositoryStub = mock(WeatherDataRepository.class);
        when(weatherDataRepositoryStub.findByLocationName(TEST_LOCATION)).thenReturn(null);

        OpenWeatherMapService owmServiceStub = setupOpenWeatherMapServiceStub(LocalDateTime.now(fixedClock));
        YahooWeatherService yahooWeatherServiceStub = setupYahooWeatherServiceStub(LocalDateTime.now(fixedClock));

        WeatherService weatherService = new WeatherService();
        weatherService.setOpenWeatherMapService(owmServiceStub);
        weatherService.setWeatherDataRepository(weatherDataRepositoryStub);
        weatherService.setYahooWeatherService(yahooWeatherServiceStub);

        // execute
        WeatherData weatherData = weatherService.getWeatherDataForLocation(TEST_LOCATION);

        // verify
        assertThat(weatherData, is(not(nullValue())));
        assertThat(weatherData.getId(), is(nullValue()));
        assertThat(weatherData.getUpdated(), is(LocalDateTime.now(fixedClock)));
    }

    @Test
    public void testGetWeatherDataForLocationOldDataInDB() {
        // setup
        WeatherDataRepository weatherDataRepositoryStub = setupWeatherRepoStub(LocalDateTime.MIN);
        OpenWeatherMapService owmServiceStub = setupOpenWeatherMapServiceStub(LocalDateTime.MAX);
        YahooWeatherService yahooWeatherServiceStub = setupYahooWeatherServiceStub(LocalDateTime.MAX);

        WeatherService weatherService = new WeatherService();
        weatherService.setYahooWeatherService(yahooWeatherServiceStub);
        weatherService.setOpenWeatherMapService(owmServiceStub);
        weatherService.setWeatherDataRepository(weatherDataRepositoryStub);

        // execute
        WeatherData weatherData = weatherService.getWeatherDataForLocation(TEST_LOCATION);

        // verify
        assertThat(weatherData, is(not(nullValue())));
        assertThat(weatherData.getId(), is(nullValue()));
        assertThat(weatherData.getUpdated(), is(LocalDateTime.MAX));
    }

    @Test
    public void testGetWeatherDataForLocationCurrentDataInDB() {
        // setup
        WeatherDataRepository weatherDataRepositoryStub = setupWeatherRepoStub(LocalDateTime.MAX);
        OpenWeatherMapService owmServiceStub = setupOpenWeatherMapServiceStub(LocalDateTime.MIN);
        YahooWeatherService yahooWeatherServiceStub = setupYahooWeatherServiceStub(LocalDateTime.MAX);

        WeatherService weatherService = new WeatherService();
        weatherService.setYahooWeatherService(yahooWeatherServiceStub);
        weatherService.setOpenWeatherMapService(owmServiceStub);
        weatherService.setWeatherDataRepository(weatherDataRepositoryStub);

        // execute
        WeatherData weatherData = weatherService.getWeatherDataForLocation(TEST_LOCATION);

        // verify
        assertThat(weatherData, is(not(nullValue())));
        assertThat(weatherData.getId(), is(1L));
        assertThat(weatherData.getUpdated(), is(LocalDateTime.MAX));
    }

    private OpenWeatherMapService setupOpenWeatherMapServiceStub(LocalDateTime time) {
        OpenWeatherMapService owmServiceStub = mock(OpenWeatherMapService.class);

        WeatherData returnedWeatherData = new WeatherData();
        returnedWeatherData.setUpdated(time);
        returnedWeatherData.setId(null);
        returnedWeatherData.setTemperature(1.0);

        when(owmServiceStub.getWeatherDataForLocation(TEST_LOCATION)).thenReturn(returnedWeatherData);
        return owmServiceStub;
    }

    private YahooWeatherService setupYahooWeatherServiceStub(LocalDateTime time) {
        YahooWeatherService yahooWeatherServiceStub = mock(YahooWeatherService.class);

        WeatherData returnedWeatherData = new WeatherData();
        returnedWeatherData.setUpdated(time);
        returnedWeatherData.setId(null);
        returnedWeatherData.setTemperature(2.0);

        when(yahooWeatherServiceStub.getWeatherDataForLocation(TEST_LOCATION)).thenReturn(returnedWeatherData);
        return yahooWeatherServiceStub;
    }


    private WeatherDataRepository setupWeatherRepoStub(LocalDateTime updatedTime) {
        WeatherDataRepository weatherDataRepositoryStub = mock(WeatherDataRepository.class);

        WeatherData weatherDataInDB = new WeatherData();
        weatherDataInDB.setId(1L);
        weatherDataInDB.setUpdated(updatedTime);

        when(weatherDataRepositoryStub.findByLocationName(TEST_LOCATION)).thenReturn(weatherDataInDB);
        return weatherDataRepositoryStub;
    }
}
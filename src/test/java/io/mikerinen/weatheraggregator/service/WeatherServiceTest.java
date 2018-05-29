package io.mikerinen.weatheraggregator.service;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import io.mikerinen.weatheraggregator.repository.WeatherDataRepository;
import io.mikerinen.weatheraggregator.webservice.openweathermap.OpenWeatherMapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class WeatherServiceTest {

    private static final String TEST_LOCATION = "Helsinki";

    @Test
    public void testGetWeatherDataForLocationNotFoundInDB() {
        // setup
        WeatherDataRepository weatherDataRepositoryStub = mock(WeatherDataRepository.class);
        when(weatherDataRepositoryStub.findByLocationName(TEST_LOCATION)).thenReturn(null);

        OpenWeatherMapService owmServiceStub = mock(OpenWeatherMapService.class);
        WeatherData returnedWeatherData = new WeatherData();
        returnedWeatherData.setId(100L);
        returnedWeatherData.setUpdated(LocalDateTime.now());
        when(owmServiceStub.getWeatherDataForLocation(TEST_LOCATION)).thenReturn(returnedWeatherData);

        WeatherService weatherService = new WeatherService();
        weatherService.setOpenWeatherMapService(owmServiceStub);
        weatherService.setWeatherDataRepository(weatherDataRepositoryStub);

        // execute
        WeatherData weatherData = weatherService.getWeatherDataForLocation(TEST_LOCATION);

        // verify
        assertThat(weatherData, is(not(nullValue())));
        assertThat(weatherData.getId(), is(100L));
        assertThat(weatherData.getUpdated(), is(not(nullValue())));
    }

    @Test
    public void testGetWeatherDataForLocationOldDataInDB() {
        // setup
        WeatherDataRepository weatherDataRepositoryStub = setupWeatherRepoStub(LocalDateTime.MIN);
        OpenWeatherMapService owmServiceStub = setupOpenWeatherMapServiceStub(LocalDateTime.MAX);

        WeatherService weatherService = new WeatherService();
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

        WeatherService weatherService = new WeatherService();
        weatherService.setOpenWeatherMapService(owmServiceStub);
        weatherService.setWeatherDataRepository(weatherDataRepositoryStub);

        // execute
        WeatherData weatherData = weatherService.getWeatherDataForLocation(TEST_LOCATION);

        // verify
        assertThat(weatherData, is(not(nullValue())));
        assertThat(weatherData.getId(), is(1L));
        assertThat(weatherData.getUpdated(), is(LocalDateTime.MAX));
    }

    private OpenWeatherMapService setupOpenWeatherMapServiceStub(LocalDateTime min) {
        OpenWeatherMapService owmServiceStub = mock(OpenWeatherMapService.class);

        WeatherData returnedWeatherData = new WeatherData();
        returnedWeatherData.setUpdated(min);
        returnedWeatherData.setId(null);

        when(owmServiceStub.getWeatherDataForLocation(TEST_LOCATION)).thenReturn(returnedWeatherData);
        return owmServiceStub;
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
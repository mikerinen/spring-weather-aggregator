package io.mikerinen.weatheraggregator.webservice.openweathermap;

import io.mikerinen.weatheraggregator.webservice.openweathermap.model.OpenWeatherMapResponse;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import javax.annotation.Resource;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Setter
@RunWith(SpringRunner.class)
@RestClientTest(OpenWeatherMapClient.class)
public class OpenWeatherMapClientTest {

    @Resource
    OpenWeatherMapClient openWeatherMapClient;
    @Resource
    private MockRestServiceServer server;

    // Sample response from
    // http://samples.openweathermap.org/data/2.5/weather?q=London&appid=a
    private String response = "{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"" +
            "weather\":[{\"id\":300,\"main\":\"Drizzle\",\"description\":\"" +
            "light intensity drizzle\",\"icon\":\"09d\"}],\"base\":\"stations\"," +
            "\"main\":{\"temp\":280.32,\"pressure\":1012,\"humidity\":81,\"temp_min\":279.15," +
            "\"temp_max\":281.15},\"visibility\":10000,\"wind\":{\"speed\":4.1,\"deg\":80}," +
            "\"clouds\":{\"all\":90},\"dt\":1485789600,\"sys\":{\"type\":1,\"id\":5091," +
            "\"message\":0.0103,\"country\":\"GB\",\"sunrise\":1485762037,\"sunset\":1485794875}," +
            "\"id\":2643743,\"name\":\"London\",\"cod\":200}";

    @Test
    public void testFetchWeatherData() {
        // setup
        this.server.expect(
                requestTo("http://samples.openweathermap.org/data/2.5/weather?q=London&appid=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        // execute
        ResponseEntity<OpenWeatherMapResponse> response =
                openWeatherMapClient.fetchWeatherData("London");

        // verify
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        OpenWeatherMapResponse body = response.getBody();
        assertThat(body.getMainInformation().getTemperature(), is(280.32));
        assertThat(body.getMainInformation().getHumidity(), is(81.0));
        assertThat(body.getSystem().getCountryCode(), is("GB"));
    }

    @Test
    public void testFetchWeatherDataNotContent() {
        // setup
        this.server.expect(
                requestTo("http://samples.openweathermap.org/data/2.5/weather?q=NotFound&appid=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
                .andRespond(withNoContent());

        // execute
        ResponseEntity<OpenWeatherMapResponse> response =
                openWeatherMapClient.fetchWeatherData("NotFound");

        // verify
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
    }
}
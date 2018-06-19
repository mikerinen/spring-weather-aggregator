package io.mikerinen.weatheraggregator.webservice.yahoo;

import io.mikerinen.weatheraggregator.webservice.yahoo.model.YahooWeatherResponse;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import javax.annotation.Resource;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Setter
@RunWith(SpringRunner.class)
@RestClientTest(YahooWeatherClient.class)
public class YahooWeatherClientTest {

    @Resource
    YahooWeatherClient yahooWeatherClient;

    @Resource
    private MockRestServiceServer server;

    // Sample response from
    // https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D"nome")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys
    private String response = "{\"query\":{\"count\":1,\"created\":\"2018-06-10T19:12:09Z\",\"lang\":\"en-GB\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Nome, AK, US\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"description\":\"Yahoo! Weather for Nome, AK, US\",\"language\":\"en-us\",\"lastBuildDate\":\"Sun, 10 Jun 2018 11:12 AM AKDT\",\"ttl\":\"60\",\"location\":{\"city\":\"Nome\",\"country\":\"United States\",\"region\":\" AK\"},\"wind\":{\"chill\":\"50\",\"direction\":\"338\",\"speed\":\"7\"},\"atmosphere\":{\"humidity\":\"67\",\"pressure\":\"1016.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"1:34 am\",\"sunset\":\"4:29 am\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Nome, AK, US at 10:00 AM AKDT\",\"lat\":\"64.499474\",\"long\":\"-165.405792\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"pubDate\":\"Sun, 10 Jun 2018 10:00 AM AKDT\",\"condition\":{\"code\":\"27\",\"date\":\"Sun, 10 Jun 2018 10:00 AM AKDT\",\"temp\":\"51\",\"text\":\"Mostly Cloudy\"},\"forecast\":[{\"code\":\"28\",\"date\":\"10 Jun 2018\",\"day\":\"Sun\",\"high\":\"56\",\"low\":\"46\",\"text\":\"Mostly Cloudy\"},{\"code\":\"32\",\"date\":\"11 Jun 2018\",\"day\":\"Mon\",\"high\":\"54\",\"low\":\"38\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"12 Jun 2018\",\"day\":\"Tue\",\"high\":\"58\",\"low\":\"41\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"13 Jun 2018\",\"day\":\"Wed\",\"high\":\"57\",\"low\":\"44\",\"text\":\"Mostly Sunny\"},{\"code\":\"30\",\"date\":\"14 Jun 2018\",\"day\":\"Thu\",\"high\":\"55\",\"low\":\"45\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"15 Jun 2018\",\"day\":\"Fri\",\"high\":\"45\",\"low\":\"38\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"16 Jun 2018\",\"day\":\"Sat\",\"high\":\"43\",\"low\":\"37\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"17 Jun 2018\",\"day\":\"Sun\",\"high\":\"43\",\"low\":\"35\",\"text\":\"Partly Cloudy\"},{\"code\":\"32\",\"date\":\"18 Jun 2018\",\"day\":\"Mon\",\"high\":\"46\",\"low\":\"36\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"19 Jun 2018\",\"day\":\"Tue\",\"high\":\"48\",\"low\":\"39\",\"text\":\"Partly Cloudy\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/27.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Mostly Cloudy\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Sun - Mostly Cloudy. High: 56Low: 46\\n<BR /> Mon - Sunny. High: 54Low: 38\\n<BR /> Tue - Partly Cloudy. High: 58Low: 41\\n<BR /> Wed - Mostly Sunny. High: 57Low: 44\\n<BR /> Thu - Partly Cloudy. High: 55Low: 45\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}";

    @Test
    public void testFetchWeatherData() {
        // setup
        this.server.expect(
                requestTo("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%22)%20and%20u%3D'c'&format=json&env=store"))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        // execute
        ResponseEntity<YahooWeatherResponse> response =
                yahooWeatherClient.fetchWeatherData("nome");

        // verify
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        Optional<YahooWeatherResponse> optionalBody = Optional.ofNullable(response.getBody());
        if (optionalBody.isPresent()) {
            YahooWeatherResponse body = optionalBody.get();
            assertThat(body.getQuery().getResults().getChannel().getItem().getCondition().getTemp(), is(51.00));
            assertThat(body.getQuery().getResults().getChannel().getItem().getTitle(), is("Conditions for Nome, AK, US at 10:00 AM AKDT"));
            assertThat(body.getQuery().getResults().getChannel().getLanguage(), is("en-us"));
        } else
            Assert.fail();
    }

    @Test
    public void testFetchWeatherDataNotContent() {
        // setup
        this.server.expect(
                requestTo("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22NotFound%22)%20and%20u%3D'c'&format=json&env=store"))
                .andRespond(withNoContent());

        // execute
        ResponseEntity<YahooWeatherResponse> response =
                yahooWeatherClient.fetchWeatherData("NotFound");

        // verify
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
    }
}
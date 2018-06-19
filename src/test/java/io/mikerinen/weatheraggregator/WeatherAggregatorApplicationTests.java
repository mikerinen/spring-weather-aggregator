package io.mikerinen.weatheraggregator;

import io.mikerinen.weatheraggregator.controller.WeatherController;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@Setter
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherAggregatorApplicationTests {


    @Resource
    private WeatherController controller;

    @Test
    public void contextLoads() {
        assertThat(controller, is(notNullValue()));
    }

}
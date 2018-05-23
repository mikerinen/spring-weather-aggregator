package io.mikerinen.weatheraggregator.repository;

import io.mikerinen.weatheraggregator.entity.WeatherData;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("weatherDataRepository")
public interface WeatherDataRepository extends PagingAndSortingRepository<WeatherData, Long> {

    WeatherData findByLocationName(String locationName);
}
CREATE TABLE WEATHER_DATA (
id                              IDENTITY NOT NULL PRIMARY KEY,
location_name                   VARCHAR(50) NOT NULL,
icon                            VARCHAR(50) NOT NULL,
description                     VARCHAR(50) NOT NULL,
long_description                VARCHAR(255) NOT NULL,
air_pressure                    DOUBLE(17),
humidity_percentage             DOUBLE(17),
temperature_max                 DOUBLE(17),
temperature_min                 DOUBLE(17),
temperature                     DOUBLE(17),
wind_speed_meters_per_second    DOUBLE(17),
wind_direction_degrees          DOUBLE(17),
updated                         TIMESTAMP
);

CREATE INDEX idx_location_name on WEATHER_DATA (location_name);
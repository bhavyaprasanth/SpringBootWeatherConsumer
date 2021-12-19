package com.weatherconsumer.SpringBootWeatherConsumer.adapter;

import org.springframework.http.ResponseEntity;

import com.weatherconsumer.SpringBootWeatherConsumer.responseDO.WeatherResponse;

/**
 * @author Bhavya Mohan
 *
 */
public interface WeatherConsumerAdapter {
	
	public WeatherResponse getWeatherByCityName(String cityName, String countryCode);
	
	public WeatherResponse getWeatherByCoordinates(double longitute, double latitute);

}

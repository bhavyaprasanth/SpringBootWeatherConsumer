package com.weatherconsumer.SpringBootWeatherConsumer.service;

import org.springframework.http.ResponseEntity;

import com.weatherconsumer.SpringBootWeatherConsumer.Entity.User;
import com.weatherconsumer.SpringBootWeatherConsumer.responseDO.WeatherResponse;

/**
 * @author Bhavya Mohan
 *
 */
public interface WeatherConsumerService {

	public User registerUser(User user);
	
	public WeatherResponse getWeatherByCityName(String cityName, String countryCode);
	
	public WeatherResponse getWeatherByCoordinates(double longitute, double latitute);
}

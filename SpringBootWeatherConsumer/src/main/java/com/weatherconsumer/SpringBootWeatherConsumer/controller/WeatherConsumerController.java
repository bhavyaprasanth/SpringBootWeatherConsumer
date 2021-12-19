package com.weatherconsumer.SpringBootWeatherConsumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weatherconsumer.SpringBootWeatherConsumer.Entity.User;
import com.weatherconsumer.SpringBootWeatherConsumer.responseDO.WeatherResponse;
import com.weatherconsumer.SpringBootWeatherConsumer.service.WeatherConsumerService;

/**
 * @author Bhavya Mohan
 *
 */
@RestController
@RequestMapping("/api")
public class WeatherConsumerController {

	Logger logger = LoggerFactory.getLogger(WeatherConsumerController.class);
	
    @Autowired
	private WeatherConsumerService weatherConsumerService;
	/**
	 * @param user
	 * @return ResponseEntity
	 */
	@RequestMapping(value= {"/v1/register"}, method= {RequestMethod.POST}) 
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User savedUser = weatherConsumerService.registerUser(user);
		if(savedUser!=null) {
			logger.info("User saved successfully");
		}else {
			logger.error("Error in saving user info");
		}
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}
	
	/**
	 * @return ResponseEntity
	 */
	@RequestMapping(value= {"/v1/getByCityName/{cityName}/{countryCode}"}, method= {RequestMethod.GET}) 
	public ResponseEntity<WeatherResponse> getWeatherByCityName(@PathVariable(name="cityName") String cityName, @PathVariable(name="countryCode") String countryCode) {
		WeatherResponse weatherResponse = weatherConsumerService.getWeatherByCityName(cityName, countryCode);
		if(weatherResponse!=null) {
			logger.info("Got back the weather details after passing city name");
		}else {
			logger.error("Error in getting weather details");
		}
		return new ResponseEntity<WeatherResponse>(weatherResponse, HttpStatus.OK);
	}
	/**
	 * @return ResponseEntity
	 */
	@RequestMapping(value= {"/v1/getByCoordinates/{longitute}/{latitute}"}, method= {RequestMethod.GET}) 
	public ResponseEntity<WeatherResponse> getWeatherByCoordinates(@PathVariable(name="longitute") Double longitute, @PathVariable(name="latitute") Double latitute) {
		WeatherResponse weatherResponse = weatherConsumerService.getWeatherByCoordinates(longitute, latitute);
		if(weatherResponse!=null) {
			logger.info("Got back the weather details after passing coordinates");
		}else {
			logger.error("Error in getting weather details");
		}
		return new ResponseEntity<WeatherResponse>(weatherResponse, HttpStatus.OK);
	}
	}


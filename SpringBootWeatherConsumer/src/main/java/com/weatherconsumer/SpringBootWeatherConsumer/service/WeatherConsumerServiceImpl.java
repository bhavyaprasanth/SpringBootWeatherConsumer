package com.weatherconsumer.SpringBootWeatherConsumer.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.weatherconsumer.SpringBootWeatherConsumer.Entity.User;
import com.weatherconsumer.SpringBootWeatherConsumer.adapter.WeatherConsumerAdapter;
import com.weatherconsumer.SpringBootWeatherConsumer.controller.WeatherConsumerController;
import com.weatherconsumer.SpringBootWeatherConsumer.exception.WeatherConsumerCustomException;
import com.weatherconsumer.SpringBootWeatherConsumer.repository.WeatherConsumerDao;
import com.weatherconsumer.SpringBootWeatherConsumer.responseDO.WeatherResponse;

/**
 * @author Bhavya Mohan
 *
 */
@Service
public class WeatherConsumerServiceImpl implements WeatherConsumerService {
	
	Logger logger = LoggerFactory.getLogger(WeatherConsumerServiceImpl.class);
	
	@Autowired
	private WeatherConsumerDao weatherConsumerDao;
	
	@Autowired
	private WeatherConsumerAdapter weatherConsumerAdapter;
	

	@Override
	public User registerUser(User user) {
		User savedUser = null;
		if(user.getName().isEmpty() || user.getName().length() == 0 ) {
			throw new WeatherConsumerCustomException("602","Please send proper name, It should not be blank",LocalDateTime.now());
		}
		try {
		savedUser = weatherConsumerDao.save(user);
		logger.info("User details saved succesfully", WeatherConsumerServiceImpl.class);
		return savedUser;
		}catch (IllegalArgumentException e) {
			logger.error("User details is null", WeatherConsumerServiceImpl.class);
			throw new WeatherConsumerCustomException("603","User is null" + e.getMessage(),LocalDateTime.now());
		}catch (Exception e) {
			logger.error("Exception while registering the user", WeatherConsumerServiceImpl.class);
			throw new WeatherConsumerCustomException("604","Something went wrong in Service layer while registering the user" + e.getMessage(),LocalDateTime.now());
		}
		
	}

	@Override
	public WeatherResponse getWeatherByCityName(String cityName, String countryCode) {
	 try {
		WeatherResponse weatherResponse = weatherConsumerAdapter.getWeatherByCityName(cityName,countryCode);
		return weatherResponse;
	 	}catch (IllegalArgumentException e) {
	 		logger.error("City details is null", WeatherConsumerServiceImpl.class);
			throw new WeatherConsumerCustomException("606","given city is null, please send some name to be searched" + e.getMessage(),LocalDateTime.now());
		}
		catch (java.util.NoSuchElementException e) {
			logger.error("City details is doesnt exist", WeatherConsumerServiceImpl.class);
			throw new WeatherConsumerCustomException("607","given city details doesnot exist in DB" + e.getMessage(),LocalDateTime.now());
		}catch (Exception e) {
			logger.error("Exception while retreiving weather details", WeatherConsumerServiceImpl.class);
			throw new WeatherConsumerCustomException("609","Something went wrong in Service layer while getting weather" + e.getMessage(),LocalDateTime.now());
		}
	}

	@Override
	public WeatherResponse getWeatherByCoordinates(double longitute, double latitute) {
		try {
		WeatherResponse weatherResponse = weatherConsumerAdapter.getWeatherByCoordinates(longitute, latitute);
		return weatherResponse;
		}catch (IllegalArgumentException e) {
	 		logger.error("coordinate details is null", WeatherConsumerServiceImpl.class);
			throw new WeatherConsumerCustomException("606","given coordinate is null, please send some name to be searched" + e.getMessage(),LocalDateTime.now());
		}
		catch (java.util.NoSuchElementException e) {
			logger.error("coordinate details is doesnt exist", WeatherConsumerServiceImpl.class);
			throw new WeatherConsumerCustomException("607","given coordinate details doesnot exist in DB" + e.getMessage(),LocalDateTime.now());
		}catch (Exception e) {
			logger.error("Exception while retreiving weather details", WeatherConsumerServiceImpl.class);
			throw new WeatherConsumerCustomException("609","Something went wrong in Service layer while getting weather" + e.getMessage(),LocalDateTime.now());
		}
	}

}

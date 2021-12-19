package com.weatherconsumer.SpringBootWeatherConsumer.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.weatherconsumer.SpringBootWeatherConsumer.exception.WeatherConsumerCustomException;

/**
 * @author Bhavya Mohan
 *
 */
@ControllerAdvice
public class WeatherConsumerControllerAdvice {
	
	@ExceptionHandler(WeatherConsumerCustomException.class)
	public ResponseEntity<String> handleWeatherCustomException(WeatherConsumerCustomException weatherConsumerCustomException){
		return new ResponseEntity<String>("There is an issue with getting weather details with the input provided, Please provide valid details",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException weatherConsumerCustomException){
		return new ResponseEntity<String>("There is an issue with getting weather details with the input provided, Response received is null",HttpStatus.BAD_REQUEST);
	}

}

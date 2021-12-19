package com.weatherconsumer.SpringBootWeatherConsumer.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.weatherconsumer.SpringBootWeatherConsumer.Entity.User;
import com.weatherconsumer.SpringBootWeatherConsumer.repository.WeatherConsumerDao;
import com.weatherconsumer.SpringBootWeatherConsumer.responseDO.WeatherResponse;
import com.weatherconsumer.SpringBootWeatherConsumer.service.WeatherConsumerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherConsumerControllerTest {
	
	@Autowired
	WeatherConsumerController weatherConsumerController;

	@MockBean
	WeatherConsumerService weatherService;
	
	@Test
	public void saveUserTest() {
		User user = new User(999, "Ishu", "Pra@gmail.com", "India");
		when(weatherService.registerUser(user)).thenReturn(user);
		ResponseEntity<User> userResponseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		assertEquals(userResponseEntity, weatherConsumerController.registerUser(user));
	}
	
	@Test
	public void whenGetByCityNameInvokedItShouldReturnWeatherResponse() {
		WeatherResponse mockResponse = mock(WeatherResponse.class);
		Double id = new Double(292223);

		when(mockResponse.getId()).thenReturn(id);
		when(mockResponse.getName()).thenReturn("Dubai");
		when(weatherService.getWeatherByCityName("Dubai", "ARE")).thenReturn(mockResponse);
		ResponseEntity<WeatherResponse> weatherResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
		assertEquals(weatherResponseEntity, weatherConsumerController.getWeatherByCityName("Dubai","ARE"));
	}
	
	@Test
	public void whenGetByCoordinatesInvokedItShouldReturnWeatherResponse() {
		WeatherResponse mockResponse = mock(WeatherResponse.class);
		Double id = new Double(292223);

		when(mockResponse.getId()).thenReturn(id);
		when(mockResponse.getName()).thenReturn("Dubai");
		when(weatherService.getWeatherByCoordinates(55.3047, 25.2582)).thenReturn(mockResponse);
		ResponseEntity<WeatherResponse> weatherResponseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
		assertEquals(weatherResponseEntity, weatherConsumerController.getWeatherByCoordinates(55.3047, 25.2582));
	}
}

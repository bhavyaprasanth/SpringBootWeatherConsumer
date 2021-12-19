package com.weatherconsumer.SpringBootWeatherConsumer.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.weatherconsumer.SpringBootWeatherConsumer.Entity.User;
import com.weatherconsumer.SpringBootWeatherConsumer.adapter.WeatherConsumerAdapter;
import com.weatherconsumer.SpringBootWeatherConsumer.controller.WeatherConsumerController;
import com.weatherconsumer.SpringBootWeatherConsumer.repository.WeatherConsumerDao;
import com.weatherconsumer.SpringBootWeatherConsumer.responseDO.WeatherResponse;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherConsumerServiceTest {

	@Autowired
	WeatherConsumerService weatherService;

	@MockBean
	WeatherConsumerDao weatherDao;
	
	@MockBean
	WeatherConsumerAdapter weatherConsumerAdapter;
	
	@Test
	public void saveUserTest() {
		User user = new User(999, "Ishu", "Pra@gmail.com", "India");
		when(weatherDao.save(user)).thenReturn(user);
		assertEquals(user, weatherService.registerUser(user));
	}
	
	@Test
	public void whenGetByCityNameInvokedItShouldReturnWeatherResponseToService() {
		WeatherResponse mockResponse = mock(WeatherResponse.class);
		Double id = new Double(292223);

		when(mockResponse.getId()).thenReturn(id);
		when(mockResponse.getName()).thenReturn("Dubai");
		when(weatherService.getWeatherByCityName("Dubai", "ARE")).thenReturn(mockResponse);
		assertEquals(mockResponse, weatherService.getWeatherByCityName("Dubai","ARE"));
	}
	
	@Test
	public void whenGetByCoordinatesInvokedItShouldReturnWeatherResponseToService() {
		WeatherResponse mockResponse = mock(WeatherResponse.class);
		Double id = new Double(292223);

		when(mockResponse.getId()).thenReturn(id);
		when(mockResponse.getName()).thenReturn("Dubai");
		when(weatherService.getWeatherByCityName("Dubai", "ARE")).thenReturn(mockResponse);
		assertEquals(mockResponse, weatherService.getWeatherByCityName("Dubai","ARE"));
	}

}

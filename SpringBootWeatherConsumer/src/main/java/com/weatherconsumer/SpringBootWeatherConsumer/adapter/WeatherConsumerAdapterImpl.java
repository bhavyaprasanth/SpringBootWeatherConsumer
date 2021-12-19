package com.weatherconsumer.SpringBootWeatherConsumer.adapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.weatherconsumer.SpringBootWeatherConsumer.exception.WeatherConsumerCustomException;
import com.weatherconsumer.SpringBootWeatherConsumer.responseDO.WeatherResponse;
import com.weatherconsumer.SpringBootWeatherConsumer.service.WeatherConsumerServiceImpl;


/**
 * @author Bhavya Mohan
 *
 */
@Component
public class WeatherConsumerAdapterImpl implements WeatherConsumerAdapter{
	
	Logger logger = LoggerFactory.getLogger(WeatherConsumerAdapterImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String uri = "http://api.openweathermap.org/data/2.5/weather";
	private final String apiKey = "cd54b7fbdaf08c6e9677d93bfb7a35b2";

	@Override
	public  WeatherResponse getWeatherByCityName(String cityName, String countryCode) {
		ResponseEntity<WeatherResponse> strResponse = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");
			
			List<String> cityList = new ArrayList<>();
			cityList.add(cityName);
			cityList.add(countryCode);
			 Collection<String> values = cityList;
			 MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
			 queryParams.add("q", String.join(",", values));
			 queryParams.add("appid", apiKey);

			 UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
			         .queryParams(queryParams)
			         .build();

			    HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

			    strResponse = restTemplate.exchange(builder.toUriString(),HttpMethod.GET, requestEntity,
			    		WeatherResponse.class);
			    
			    
			    if(strResponse!=null) {
			    	logger.info("Successfully received the weather details");
			    }
			    else {
			    	logger.error("Response received by passing city name is null");
			    	throw new NullPointerException();
			    }
			    
		}catch(NullPointerException e) {
			throw new WeatherConsumerCustomException("603","Response received by passing city name is null" + e.getMessage(),LocalDateTime.now());
		} catch (RestClientException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return strResponse.getBody();
	}

	@Override
	public WeatherResponse getWeatherByCoordinates(double longitute, double latitute){
		ResponseEntity<WeatherResponse> strResponse = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");

			UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
			                        .queryParam("lat",latitute)
			                        .queryParam("lon", longitute)
			                        .queryParam("appid",apiKey)
			                        .build();

			    HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

			    strResponse = restTemplate.exchange(builder.toUriString(),HttpMethod.GET, requestEntity,
			    		WeatherResponse.class);
			    if(strResponse!=null) {
			    	logger.info("Successfully received the weather details");
			    }
			    else {
			    	logger.error("Response received by passing coordinates is null");
			    	throw new NullPointerException();
			    }
			    
		}catch(NullPointerException e) {
			throw new WeatherConsumerCustomException("603","Response received  is null" + e.getMessage(),LocalDateTime.now());
		} catch (RestClientException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return strResponse.getBody();
	}
	
	

}

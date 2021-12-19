package com.weatherconsumer.SpringBootWeatherConsumer.responseDO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sys {

	private int type;
	private int id;
	private String country;
	private double sunrise;
	private double sunset;
	
}

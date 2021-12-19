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
public class Coord {
 
	private double lon;
	private double lat;
	
	
}

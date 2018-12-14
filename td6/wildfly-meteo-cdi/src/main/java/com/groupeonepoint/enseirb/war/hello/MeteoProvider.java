package com.groupeonepoint.enseirb.war.hello;

import java.util.Random;

@ModelRandom
public class MeteoProvider implements IMeteoProvider{

	public int getTemperature(String city) {
		Random rand = new Random();

		//40 is the maximum and -10 is the minimum 
		return rand.nextInt(50) - 10;
	}
	
}

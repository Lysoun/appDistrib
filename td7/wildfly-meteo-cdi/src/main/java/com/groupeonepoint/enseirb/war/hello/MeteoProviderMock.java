package com.groupeonepoint.enseirb.war.hello;

@ModelMock
public class MeteoProviderMock implements IMeteoProvider {
	private static final int temp = 18;

	public int getTemperature(String city) {
		return temp;
	}

}

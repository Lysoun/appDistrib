package com.groupeonepoint.enseirb.war.hello;

public class User {
	private final String name;
	private int count;
	
	public User(String name) {
		this.name = name;
		count = 0;
	}
	
	public String getName() {
		return new String(this.name);
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void increaseCount() {
		count++;
	}
}

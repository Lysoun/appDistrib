package com.groupeonepoint.enseirb.war.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Users {
	private static HashMap<String, User> users = new HashMap<String, User>();
	private static Users instance = new Users();
		
	private Users() {
		users.put("Limace", new User("Limace"));
		users.put("Couverdure", new User("Couverdure"));
	}
	
	public static Users get() {
		return instance;
	}
	
	public void addUser(String user) {
		if(!users.containsKey(user))
			users.put(new String(user), new User(user));		
	}
	
	public Integer getCount(String user) {
		return Integer.valueOf(users.get(user).getCount());
	}
	
	public void increaseCount(String user) {
		users.get(user).increaseCount();
	}
	
	public List<User> getUserList(){
		return new ArrayList<User>(users.values());
	}
	
	public boolean isRegistered(String user) {
		return users.containsKey(user);
	}
}

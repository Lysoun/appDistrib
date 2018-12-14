package com.groupeonepoint.enseirb.war.hello;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class AuthenticationController implements Serializable{	
	private static final long serialVersionUID = 1L;
	private final String login;

	AuthenticationController(String login) {
		this.login = login;
	}

	public String getLogin() {
		return new String(login);
	}
	
	public int getCount() {
		return Users.get().getCount(login);
	}
}

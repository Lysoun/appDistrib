package com.groupeonepoint.enseirb.war.hello;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<User> userList = Users.get().getUserList();
		
		response.setContentType("text/html");
		request.setAttribute("userList", userList);
		
		String nextJSP = "/WEB-INF/Login.jsp";
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String login = request.getParameter("name");

		List<User> userList = Users.get().getUserList();

		response.setContentType("text/html");
		request.setAttribute("userList", userList);

		String nextServlet = "/wildfly-meteo-cdi/login";
		
		if (login != null) {
			request.setAttribute("name", login);
			if (Users.get().isRegistered(login)) {
				Users.get().increaseCount(login);
				nextServlet = "/wildfly-meteo-cdi/meteo";
			}
			else {
				request.setAttribute("userNotFound", true);
			}
		}
				
		response.sendRedirect(nextServlet);
	}
}

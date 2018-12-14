package com.groupeonepoint.enseirb.war.hello;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Déclaration de la servlet auprès du conteneur de servlet
@WebServlet(name = "AddUserServlet", urlPatterns = "/newuser")
public class ServletAddUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Méthode appelée par le conteneur lorsqu'une requète Http GET est reçue
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("name");

		resp.setContentType("text/html");
		String nextJSP = "/WEB-INF/AddUser.jsp";
		
		if (login != null) {
			if (!login.equals("")) {				
				Users users = Users.get();
				users.addUser(login);
				List<User> userList = users.getUserList();
				
				req.setAttribute("userList", userList);
				nextJSP = "/WEB-INF/Login.jsp";
			}
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}

}

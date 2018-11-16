package com.groupeonepoint.enseirb.war.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Déclaration de la servlet auprès du conteneur de servlet
@WebServlet(name="HelloServlet", urlPatterns="/hello")
public class ServletHelloWorld extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// Méthode appelée par le conteneur lorsqu'une requète Http GET est reçue
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// On récupère un paramètre depuis la requète
		String name = req.getParameter("name");
		
		// On génère la réponse
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<h1>" + "Hello " + name + "! Je suis une servlet" + "</h1>");		
		
	}

}

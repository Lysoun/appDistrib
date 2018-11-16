package com.groupeonepoint.enseirb.war.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Déclaration de la servlet auprès du conteneur de servlet
@WebServlet(name = "MeteoServlet", urlPatterns = "/meteo")
public class ServletMeteo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Méthode appelée par le conteneur lorsqu'une requète Http GET est reçue
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// On récupère un paramètre depuis la requète (peut être null)
		String name = req.getParameter("city");
		
		// On génère la réponse si city = null -> formulaire
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<form>"
					+ "Ville :<br/>"
					+ "<input type='text' name='city'><br/>"
					+ "<input type='submit' value='Valider' action='meteo' method='post'>"
					+ "</form>");
		// on recharge la page

		// On génère la réponse si city != null -> affichage de la réponse
		if(name!=null) {
			resp.setContentType("text/html");
			out = resp.getWriter();
			
			Random rand = new Random();
			int temp = rand.nextInt(50) - 10;
			//50 is the maximum and the 1 is our minimum 
			
			out.println("<h1>" + "Il fait " + temp + " degrés à " + name + ".</h1>");
		}
	}
}

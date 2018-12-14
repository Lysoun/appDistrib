package com.groupeonepoint.enseirb.war.hello;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Déclaration de la servlet auprès du conteneur de servlet
@WebServlet(name = "MeteoServlet", urlPatterns = "/meteo")
public class ServletMeteo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//MeteoProvider meteoProvider = new MeteoProvider();
	@Inject @ModelRandom private IMeteoProvider meteoProvider;
	//@Inject @ModelMock private IMeteoProvider meteoProvider;

	// Méthode appelée par le conteneur lorsqu'une requète Http GET est reçue
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {		
		
		// On récupère un paramètre depuis la requète (peut être null)
		String city = req.getParameter("city");
		
		// On génère la réponse si city = null -> formulaire
		resp.setContentType("text/html");
		
		// On génère la réponse si city != null -> affichage de la réponse
		if(city!=null) {			
			int temp = meteoProvider.getTemperature(city);
			
			req.setAttribute("city", city);
			req.setAttribute("temp", temp);
		}
		
		String nextJSP = "/WEB-INF/ShowMeteo.jsp";
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req,resp);

	}
}

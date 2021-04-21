import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Demo03Servlet
 */
@WebServlet("/registrierenservlet")
public class registrierenservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet zur Entgegennahme von Formularinhalten und Generierung eines Beans zur Weitergabe
		// der Formulardaten an eine JSP
		request.setCharacterEncoding("UTF-8");	// In diesem Format erwartet das Servlet jetzt die Formulardaten
												// Alternative: GlassFish dazu bewegen, die Formulardaten gleich
												// als UTF-8 zu interpretieren. Dazu muss GlassFish auf UTF-8 umge-
												// stellt werden. Eine neue Zeile in die Datei glassfish-web.xml
												// erg√§nzen (zu finden im WEB-INF-Ordner des Projektes):
												// <parameter-encoding default-charset="UTF-8" />
		registrierenBean form = new registrierenBean();
		form.setTitel(request.getParameter("titel"));
		form.setNname(request.getParameter("nname"));
		form.setVname(request.getParameter("vname"));
		form.setEmail(request.getParameter("email"));
		form.setPlz(request.getParameter("plz"));
		form.setStraﬂe(request.getParameter("straﬂe"));
		form.setHausnummer (request.getParameter("hausnummer"));
		form.setAnmerkung(request.getParameter("anmerkung"));
		
		// Scope "Request"
		// request.setAttribute("form", form);
		
		// Scope "Session"
		final HttpSession session = request.getSession();
		session.setAttribute("form", form);

		// Scope "Application"
		// final ServletContext application = request.getServletContext();
		// application.setAttribute("form", form);
		
		// Weiterleiten an JSP entweder √ºber Redirect (dem PRG-Pattern folgend Post-Redirect-Get) oder per Forward
		// response.sendRedirect("03_formausgabe.jsp");
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("03_formausgabe.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
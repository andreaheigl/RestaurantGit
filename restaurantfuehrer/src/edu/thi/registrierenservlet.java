package edu.thi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resources;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//neuerCode
import edu.thi.registrierenBean;

//import edu.thi.demo.servlets.Resource;

@WebServlet("/registrierenservlet")
public class registrierenservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//neuerCode
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
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
		form.setOrt(request.getParameter("ort"));
		form.setStraﬂe(request.getParameter("straﬂe"));
		form.setHausnummer (request.getParameter("hausnummer"));
		form.setAnmerkung(request.getParameter("anmerkung"));
		
		// DB-Zugriff
		persist(form);
		
		// Scope "Request"
		//request.setAttribute("form", form);
		
		// Scope "Session"
		final HttpSession session = request.getSession();
		session.setAttribute("form", form);

		// Scope "Application"
		// final ServletContext application = request.getServletContext();
		// application.setAttribute("form", form);
		
		// Weiterleiten an JSP entweder √ºber Redirect (dem PRG-Pattern folgend Post-Redirect-Get) oder per Forward
		// response.sendRedirect("03_formausgabe.jsp");
	
		// Weiterleiten an JSP
			final RequestDispatcher dispatcher = request.getRequestDispatcher("formausgabe.jsp");
			dispatcher.forward(request, response);
	}
	
	//neuerCode
	private void persist(registrierenBean form) throws ServletException {
		// DB-Zugriff
		String[] generatedKeys = new String[] {"id"};	// Name der Spalte(n), die automatisch generiert wird(werden)
		
		try (Connection con = ds.getConnection();
			 /*final Statement stmt = con.createStatement()*/
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO kunden (titel,nname,vname,email,plz,ort,straﬂe,hausnummer,anmerkung) VALUES (?,?,?,?,?,?,?,?)", 
					generatedKeys)){

			// Zugriff √ºber Klasse java.sql.Statement
/*			stmt.executeUpdate("INSERT INTO employees (first,last,age) VALUES ('" +
								form.getFirstname() + "','" +
								form.getLastname() + "','" +
								form.getAge() + "')"
					);
*/		
			// Zugriff √ºber Klasse java.sql.PreparedStatement
			pstmt.setString(1, form.getNname());
			pstmt.setString(2, form.getVname());
			pstmt.setString(3, form.getEmail());
			pstmt.setString(4, form.getPlz());
			pstmt.setString(5, form.getOrt());
			pstmt.setString(6, form.getStraﬂe());
			pstmt.setString(7, form.getHausnummer());
			pstmt.setString(8, form.getAnmerkung());
			pstmt.executeUpdate();
			
			// Generierte(n) Schl√ºssel auslesen (funktioniert nur mit PreparedStatement)
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				while (rs.next()) {
					form.setId(rs.getLong(1));
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
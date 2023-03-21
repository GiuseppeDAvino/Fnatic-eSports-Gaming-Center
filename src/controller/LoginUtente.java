package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PrenotazioneBean;
import model.PrenotazioneDAO;
import model.ShoppingCart;
import model.TesseraBean;
import model.TesseraDAO;
import model.UtenteBean;
import model.UtenteDAO;

/**
 * Servlet implementation class LoginUtente
 */
@WebServlet("/login")
public class LoginUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtenteDAO utenteDAO = new UtenteDAO();
	private TesseraDAO tesseraDAO = new TesseraDAO();
	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean utente = new UtenteBean();
		
		String e = request.getParameter("email");
		String psw = request.getParameter("password");
		
		try {
			utente = utenteDAO.loginUtente(e, psw);
			if(utente == null) {
				RequestDispatcher redirectPage = request.getRequestDispatcher("error.jsp");
				request.setAttribute("errore", "Username o password errati!");
				redirectPage.include(request, response);
			}
			else {
				//Se l'autenticazione va a buon fine
				//Recupero la sessione
				RequestDispatcher redirectPage = request.getRequestDispatcher("pageuser.jsp");
				TesseraBean tessera = new TesseraBean();
				tessera = tesseraDAO.doRetrieveByKey(e);
				
				HttpSession session = request.getSession();
				session.setAttribute("utente", utente);
				session.setAttribute("tessera", tessera);
				session.setAttribute("prenotazioni", prenotazioneDAO.doRetrieveByKey(e));
				session.setMaxInactiveInterval(5*60);
				redirectPage.include(request, response);
			}
			
		} catch (SQLException r) {
			r.printStackTrace();
		}
	}

}

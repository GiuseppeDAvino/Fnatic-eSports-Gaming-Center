package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TesseraBean;
import model.TesseraDAO;
import model.UtenteBean;
import model.UtenteDAO;

/**
 * Servlet implementation class RegistrazioneUtente
 */
@WebServlet("/registrazione")
public class RegistrazioneUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtenteDAO utenteDAO = new UtenteDAO();
	private TesseraDAO tesseraDAO = new TesseraDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String tipo_tessera = request.getParameter("tipo-tessera");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UtenteBean utente = new UtenteBean();
		TesseraBean tessera = new TesseraBean();
		int verifica_errore;
		
		utente.setEmail(email);
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setUsername(username);
		utente.setPassword(password);
		tessera.setTipo(tipo_tessera);
		tessera.setEmail_utente(email);
		
		try {
			verifica_errore = utenteDAO.registraUtente(utente);
				if(verifica_errore == 1) {
					RequestDispatcher redirectPage = request.getRequestDispatcher("error.jsp");
					request.setAttribute("errore", "Email inserita già esistente!");
					redirectPage.include(request, response);
				}
				else if(verifica_errore == 2) {
					RequestDispatcher redirectPage = request.getRequestDispatcher("error.jsp");
					request.setAttribute("errore", "Username inserito già esistente");
					redirectPage.include(request, response);
				}
				else {
				//Se l'autenticazione va a buon fine
				//Recupero la sessione
				RequestDispatcher redirectPage = request.getRequestDispatcher("pageuser.jsp");
				tesseraDAO.doSave(tessera);
				TesseraBean bean = new TesseraBean();
				HttpSession session = request.getSession();
				bean = tesseraDAO.doRetrieveByKey(email);
				session.setAttribute("utente", utente);
				session.setAttribute("tessera", bean);
				session.setMaxInactiveInterval(5*60);
				redirectPage.include(request, response);
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	
	}

}

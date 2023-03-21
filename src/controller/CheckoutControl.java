package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PostazioneBean;
import model.PrenotazioneBean;
import model.PrenotazioneDAO;
import model.TesseraDAO;
import model.UtenteBean;


/**
 * Servlet implementation class CheckoutControl
 */
@WebServlet("/CheckoutControl")
public class CheckoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
	private TesseraDAO tesseraDAO = new TesseraDAO();
    public CheckoutControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errore = "non-errore";
		
		PrenotazioneBean prenotazione = new PrenotazioneBean();
		HttpSession session = request.getSession(false);
		int totale = (Integer) session.getAttribute("totale");
		System.out.println(totale);
		@SuppressWarnings("rawtypes")
		Vector carrello = (Vector) session.getAttribute("checkout-carrello");
		UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		PostazioneBean postazione = new PostazioneBean();
		String data = null;
		System.out.println("totale: " + totale);
		try {
			if(tesseraDAO.paga(utente.getEmail(), totale)) {
				for(int i=0; i<carrello.size(); i++) {
					data = (String) request.getParameter(Integer.toString(i));
					postazione = (PostazioneBean) carrello.elementAt(i);
					prenotazione.setData_prenotazione(data);
					prenotazione.setEmail_utente(utente.getEmail());
					prenotazione.setNumero_postazione(postazione.getN_posto());
				
				try {
					prenotazioneDAO.doSave(prenotazione);

					RequestDispatcher redirectPage = request.getRequestDispatcher("thankyou.html");
					redirectPage.include(request, response);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			}
			else {
				errore = "errore";
				session.setAttribute("errore", errore);
				RequestDispatcher redirectPage = request.getRequestDispatcher("checkout.jsp");
				redirectPage.include(request, response);
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
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

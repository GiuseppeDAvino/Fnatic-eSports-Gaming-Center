package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PrenotazioneDAO;

/**
 * Servlet implementation class RestituisciUltimaPrenotazione
 */
@WebServlet("/RestituisciUltimaPrenotazione")
public class RestituisciUltimaPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestituisciUltimaPrenotazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println("ciao " + data);
		try {
			request.setAttribute("prenotazioni-per-data", prenotazioneDAO.doRetrieveByDate(data));
			RequestDispatcher redirectPage = request.getRequestDispatcher("pageuser.jsp");
			redirectPage.include(request, response);
		} catch (SQLException e) {
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

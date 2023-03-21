package model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DettagliPostazione
 */
@WebServlet("/DettagliPostazione")
public class DettagliPostazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PostazioneDAO postazioneDAO = new PostazioneDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliPostazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostazioneBean postazione = new PostazioneBean();
		HttpSession session = request.getSession();
		String tipo = request.getParameter("tipo");
		int n_posto = Integer.parseInt(request.getParameter("n_posto"));
		try {
			if(tipo.equals("pc")) {
				postazione = postazioneDAO.doRetrieveByKey(n_posto);
	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pc.jsp");
				session.setAttribute("postazione", postazione);
				dispatcher.include(request, response);
				response.sendRedirect(request.getContextPath() + "/pc.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

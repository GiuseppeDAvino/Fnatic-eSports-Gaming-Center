package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteBean;
import model.UtenteDAO;

/**
 * Servlet implementation class UtenteControl
 */
@WebServlet("/UtenteControl")
public class UtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtenteDAO utenteDAO = new UtenteDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		try {
			if(action != null) {
				if(action.equalsIgnoreCase("delete")) {
					String email = request.getParameter("email");
					utenteDAO.doDelete(email);
				}
				else if(action.equalsIgnoreCase("read")) {
					String email = request.getParameter("email");
					request.removeAttribute("utente");
					request.setAttribute("utente", utenteDAO.doRetrieveByKey(email));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			request.removeAttribute("utenti");
			request.setAttribute("utenti", utenteDAO.doRetrieveAll());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione-utenti.jsp");
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

package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TesseraDAO;

/**
 * Servlet implementation class TesseraControl
 */
@WebServlet("/TesseraControl")
public class TesseraControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private TesseraDAO tesseraDAO = new TesseraDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesseraControl() {
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
					int codice = Integer.parseInt(request.getParameter("codice"));
					tesseraDAO.doDelete(codice);
				}
				else if(action.equalsIgnoreCase("read")) {
					String email_utente = request.getParameter("email_utente");
					request.removeAttribute("tessera");
					request.setAttribute("tessera", tesseraDAO.doRetrieveByKey(email_utente));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			request.removeAttribute("tessere");
			request.setAttribute("tessere", tesseraDAO.doRetrieveAll());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione-tessere.jsp");
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

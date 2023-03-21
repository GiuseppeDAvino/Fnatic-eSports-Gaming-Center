package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostazioneBean;
import model.SalaBean;
import model.SalaDAO;

/**
 * Servlet implementation class SalaControl
 */
@WebServlet("/SalaControl")
public class SalaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalaDAO salaDAO = new SalaDAO();
	
    public SalaControl() {
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
				if(action.equalsIgnoreCase("insert")) {
					int capacita = Integer.parseInt(request.getParameter("capacita"));
					
					SalaBean sala = new SalaBean();
					sala.setCapacita(capacita);

					
					salaDAO.doSave(sala);
				}
				else if(action.equalsIgnoreCase("delete")) {
					int codice = Integer.parseInt(request.getParameter("codice"));
					salaDAO.doDelete(codice);
				}
				else if(action.equalsIgnoreCase("read")) {
					int codice = Integer.parseInt(request.getParameter("codice"));
					request.removeAttribute("sala");
					request.setAttribute("sala", salaDAO.doRetrieveByKey(codice));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			request.removeAttribute("sale");
			request.setAttribute("sale", salaDAO.doRetrieveAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione-sale.jsp");
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

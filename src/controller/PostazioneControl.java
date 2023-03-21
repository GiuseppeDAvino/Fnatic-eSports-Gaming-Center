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
import model.PostazioneDAO;

/**
 * Servlet implementation class PostazioneControl
 */
@WebServlet("/PostazioneControl")
public class PostazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PostazioneDAO postazioneDAO = new PostazioneDAO();
	
    public PostazioneControl() {
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
					String tipo = request.getParameter("tipo");
					String nome_postazione = request.getParameter("nome_postazione");
					String descrizione = request.getParameter("descrizione");
					int costo = Integer.parseInt(request.getParameter("costo"));
					int codice_sala = Integer.parseInt(request.getParameter("codice_sala"));
					
					PostazioneBean postazione = new PostazioneBean();
					postazione.setTipo(tipo);
					postazione.setNome_postazione(nome_postazione);
					postazione.setCosto(costo);
					postazione.setDescrizione(descrizione);
					postazione.setCodice_sala(codice_sala);
					
					postazioneDAO.doSave(postazione);
				}
				else if(action.equalsIgnoreCase("delete")) {
					int n_posto = Integer.parseInt(request.getParameter("n_posto"));
					postazioneDAO.doDelete(n_posto);
				}
				else if(action.equalsIgnoreCase("read")) {
					int n_posto = Integer.parseInt(request.getParameter("n_posto"));
					request.removeAttribute("postazione");
					request.setAttribute("postazione", postazioneDAO.doRetrieveByKey(n_posto));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			request.removeAttribute("postazioni");
			request.setAttribute("postazioni", postazioneDAO.doRetrieveAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/gestione-postazioni.jsp");
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

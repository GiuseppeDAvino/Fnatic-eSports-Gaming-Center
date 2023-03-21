package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostazioneDAO;

/**
 * Servlet implementation class GetPictureServletPostazione
 */
@WebServlet("/getPicturePostazione")
public class GetPictureServletPostazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPictureServletPostazione() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		byte[] bt = null;
		String n_posto = (String) request.getParameter("n_posto");
		if (n_posto != null) 
		{
			try {
				bt = PostazioneDAO.load(n_posto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ServletOutputStream out = response.getOutputStream();
			if (bt != null) 
			{
				out.write(bt);
				response.setContentType("image/jpeg");
			}
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

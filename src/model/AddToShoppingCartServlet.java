package model;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/AddToShoppingCartServlet")

public class AddToShoppingCartServlet extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException
    {

// Prendere gli item dalla request
        int n_posto = Integer.parseInt(request.getParameter("numero_posto"));
        String tipo = request.getParameter("tipo");
        int costo = Integer.parseInt(request.getParameter("costo"));
        int codice_sala = Integer.parseInt(request.getParameter("codice_sala"));

// Creare il bean e aggiungere al carrello
        PostazioneBean postazione = new PostazioneBean();
        postazione.setN_posto(n_posto);
        postazione.setTipo(tipo);
        postazione.setCosto(costo);
        postazione.setCodice_sala(codice_sala);

        HttpSession session = request.getSession();

// Prendere il carrello
        ShoppingCart carrello = (ShoppingCart) session.
            getAttribute("ShoppingCart");

// Se non esiste nessun carrello, creare uno nuovo
        if (carrello == null)
        {
            carrello = new ShoppingCart();

            session.setAttribute("ShoppingCart", carrello);
        }

        carrello.addItem(postazione);

        response.sendRedirect(response.encodeRedirectURL("/Fnatic-eSports-Gaming-Center/carrello.jsp"));
    }
}
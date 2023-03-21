<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="model.*,java.util.*,java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
    <link rel="stylesheet" href="icone/style.css">

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">


    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/style.css">
    
        <script
    src="https://code.jquery.com/jquery-3.3.1.js">
</script>
<script> 
$(function(){
  $("#header").load("header.html"); 
  $("#footer").load("footer.html"); 
});
</script>
</head>
<body>
	<div class="site-wrap">
		<div id="header"></div>
		</div>
<%
// Get the current shopping cart from the user's session.
    ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
	int totale = 0;

// If the user doesn't have a shopping cart yet, create one.
    if (cart == null)
    {
        cart = new ShoppingCart();
        session.setAttribute("ShoppingCart", cart);
    }

// Get the items from the cart.
    Vector items = cart.getItems();

// If there are no items, tell the user that the cart is empty.
    if (items.size() == 0)
    {
        out.println("<h3>Your shopping cart is empty.</h3>");
    }
    else
    {
%>
	<div class="site-section">
      <div class="container">
        <div class="row mb-5">
          <form class="col-md-12" method="post">
            <div class="site-blocks-table">
              <table class="table table-bordered">
                <thead>
                  <tr>
                    <th class="product-thumbnail">Immagine</th>
                    <th class="product-name">Postazione</th>
                    <th class="product-price">Prezzo</th>
                    <th class="product-remove">Opzioni</th>
                  </tr>
                </thead>
                <tbody>
       <%

        int numItems = items.size();

// Get a formatter to write out currency values.
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        for (int i=0; i < numItems; i++)
        {
           	PostazioneBean item = (PostazioneBean) items.elementAt(i);
           	totale = totale + item.getCosto();
           	
           	
		%>
		<tbody>
			<tr>
                    <td class="product-thumbnail">
                      <img src="./getPicturePostazione?n_posto= <%=item.getN_posto() %>" alt="Image" class="img-fluid">
                    </td>
                    <td class="product-name">
                      <h2 class="h5 text-black"><%=item.getTipo() %></h2>
                    </td>
                    <td><%=item.getCosto() %> euro</td>
                    
                    <% String url = "/Fnatic-eSports-Gaming-Center/RemoveItemServlet?item=" + i; %>
                    <td><a href=<%= url %> class="btn btn-primary btn-sm">X</a></td>
                    <%session.setAttribute("checkout-carrello", items); %>
                  </tr>
		</tbody>
		<%		}
    		}
        
        %>
                </table>
                </div>
                </form>
                </div>
                        <div class="row">
          <div class="col-md-6">
              <div class="col-md-6">
                <a href="ShowProductCatalog.jsp"><button class="btn btn-primary btn-sm btn-block">Continue Shopping</button></a>
              </div>
            </div>
          </div>
          <div class="col-md-6 pl-5">
            <div class="row justify-content-end">
              <div class="col-md-7">
                <div class="row">
                  <div class="col-md-12 text-right border-bottom mb-5">
                    <h3 class="text-black h4 text-uppercase">Carrello totale</h3>
                  </div>
                </div>
                <div class="row mb-5">
                  <div class="col-md-6">
                    <span class="text-black">Totale</span>
                  </div>
                  <div class="col-md-6 text-right">
                    <strong class="text-black"><%= totale %></strong>
                    <% session.setAttribute("totale",totale); %>
                  </div>
                </div>
					
					
                <div class="row">
                  <div class="col-md-12">
                    <button class="btn btn-primary btn-lg py-3 btn-block" onclick="window.location='GoToCheckout'">Proceed To Checkout</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
       </div>
       
       	<!-- Footer -->
	<div id="footer"></div>
      
		

</body>
</html>
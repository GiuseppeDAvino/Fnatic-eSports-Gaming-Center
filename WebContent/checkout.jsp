<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="model.*,java.util.*,java.text.*"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="utf-8">
<title>Fnatic e-Sports Center</title>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-grid.min.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-grid.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="icone/style.css">
	<link rel="stylesheet" href="css/gallery-grid.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.8.1/baguetteBox.min.css">
        
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
<%
// Get the current shopping cart from the user's session.
    Vector items = (Vector) session.getAttribute("checkout-carrello");
	int totale = 0;

%>
	<div class="site-wrap">
		<div id="header"></div>

    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><a href="index.html">Home</a> <span class="mx-2 mb-0">/</span> <a href="cart.html">Cart</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Checkout</strong></div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <div class="container">

        <div class="row">
        <div class="row mb-5">
          <form action="/Fnatic-eSports-Gaming-Center/CheckoutControl" class="col-md-12" method="get">
            <div class="site-blocks-table">
              <table class="table table-bordered">
                <thead>
                  <tr>
                    <th class="product-thumbnail">Data</th>
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
                      <input type="date" id="<%= i %>" name="<%= i %>" min="" max="2020-12-31">
                      <script>
    	 var d1 = new Date();
   		var i = "<%= i %>";
        var y1= d1.getFullYear();
        var m1 = d1.getMonth()+1;
            if(m1<10){ m1="0"+m1};
        var dt1 = d1.getDate();
            if(dt1<10){dt1 = "0"+dt1};
        var d2 = y1+"-"+m1+"-"+dt1;
    document.getElementById(i).value=d2;


    var future = new Date();
future.setDate(future.getDate());
 var futureYear= future.getFullYear();
    var futureMonth = future.getMonth()+1;
        if(futureMonth<10){futureMonth="0"+futureMonth};
    var futureDay = future.getDate();
        if(futureDay<10){futureDay = "0"+futureDay};
    var futureDate = futureYear+"-"+futureMonth+"-"+futureDay;
    document.getElementById(i).setAttribute("min", futureDate);
</script>
                    </td>
                    <td class="product-name">
                      <h2 class="h5 text-black"><%=item.getTipo() %></h2>
                    </td>
                    <td><%=item.getCosto() %> euro</td>
                    
                    <% String url = "/Fnatic-eSports-Gaming-Center/RemoveItemServlet?item=" + i; %>
                    <td><a href=<%= url %> class="btn btn-primary btn-sm">X</a></td>
                  </tr>
		</tbody>
		<%		}

        
        %>
                </table>
                </div>
                
                  <div class="form-group">
                    <input type="submit" class="btn btn-primary btn-lg py-3 btn-block" value="Place Order">
                  </div>
                </form>
               	
               	<% 	
               		String errore =  "errore";
               		System.out.println(session.getAttribute("errore"));
               		if(errore.equals(session.getAttribute("errore"))){
               	%>
               			<script>alert("Il saldo della tua tessera è inferiore all'importo della prenotazione, si prega di ricaricare la carta!");</script>
               	<%
               		}
               	%>


                </div>
              </div>
            </div>

          </div>
        </div>
        <!-- </form> -->


    
	<div id="footer"></div>

  <script src="js/jquery-3.3.1.min.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>

  <script src="js/main.js"></script>
  



    
  </body>
</html>
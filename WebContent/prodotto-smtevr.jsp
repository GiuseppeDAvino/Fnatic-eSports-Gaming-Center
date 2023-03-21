<%@ page language="java" import="model.*,java.net.*,java.text.*, java.util.*" %>
<html>
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
<body bgcolor="#ffffff">
<div id="header"></div>
<%

PostazioneDAO model = new PostazioneDAO();


// Initialize the array of available products.
Collection<PostazioneBean> prodotti = model.doRetrieveByType("SIMULATORI E VR");


%>

<a href="/Fnatic-eSports-Gaming-Center/DisplayShoppingCart.jsp">View Shopping Cart</a>
<p>
<h1>Available Products</h1>
<table border="1">
<tr><th>Description</th><th>Quantity</th><th>Price</th></tr>
<%


   
    	Iterator<?> it = prodotti.iterator();
		while (it.hasNext()) {
			PostazioneBean item = (PostazioneBean) it.next();

// Create the URL for adding the item to the shopping cart.
        String addItemURL =
            "/Fnatic-eSports-Gaming-Center/AddToShoppingCartServlet?"+
            "numero_posto="+URLEncoder.encode("" + item.getN_posto(),java.nio.charset.StandardCharsets.UTF_8.toString())+
            "&tipo="+ URLEncoder.encode(item.getTipo(),java.nio.charset.StandardCharsets.UTF_8.toString())+
            "&costo="+ URLEncoder.encode(""+item.getCosto(),java.nio.charset.StandardCharsets.UTF_8.toString())+
            "&codice_sala="+ URLEncoder.encode(""+item.getCodice_sala(),java.nio.charset.StandardCharsets.UTF_8.toString());
%>
<tr><td><%=item.getN_posto()%></td><td><%=item.getTipo()%>
    </td><td><%=item.getCosto()%></td>
<td><a href="<%=addItemURL%>">Add to Shopping Cart</a></td></tr>
<%
		}
    
%>
</table>
</body>
<div id="footer"></div>
</html>
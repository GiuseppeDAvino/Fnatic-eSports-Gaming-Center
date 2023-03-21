<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-grid.min.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-grid.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="icone/style.css">
	<link rel="stylesheet" href="css/gallery-grid.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-blue-grey.css">
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	    <script
    src="https://code.jquery.com/jquery-3.3.1.js">
</script>
<script> 
$(function(){
  $("#header").load("header.html"); 
  $("#footer").load("footer.html"); 
});
</script>
<style>
html, body, h1, h2, h3, h4, h5 {font-family: "Open Sans", sans-serif}
</style>
<body class="w3-theme-l5">
	<div id="header"></div>
	<% 
		UtenteBean utente = null;
		String username = null;
		TesseraBean tessera =null;
		if(session.getAttribute("utente") == null){
			 %>
			 <jsp:forward page="/PaginaUtente"></jsp:forward>
	<%
		}
		utente = (UtenteBean) session.getAttribute("utente");
		username = utente.getUsername();
		tessera = (TesseraBean) session.getAttribute("tessera");
		Collection<?> prenotazioni = (Collection<?>) session.getAttribute("prenotazioni");
		
	%>
<!-- Page Container -->
<div class="w3-container w3-content" style="max-width:1400px;margin-top:80px">    
  <!-- The Grid -->
  <div class="w3-row">
    <!-- Left Column -->
    <div class="w3-col m3">
      <!-- Profile -->
      <div class="w3-card w3-round w3-white">
        <div class="w3-container">
         <h4 class="w3-center">Bentornato, <%=username %></h4>
         <p class="w3-center"><img src="./getPictureUtente?username=<%= username %>" class="w3-circle" style="height:106px;width:106px" alt="Avatar" onerror="this.onerror=null;this.src='<%=request.getContextPath()+"/immagini/nophoto.png"%>'"></p>
         <div>
         	<h6>Modifica l'immagine profilo</h6>
         	<form action="UploadPhoto" enctype="multipart/form-data" method="post">
    			<input name="username" type="hidden" value="<%=username %>"><br>
    			<input name="photo" class="file" type="file" value="" maxlength="255">
    			<input type="submit" value="Add">
     		</form>
         
         </div>
         <hr>
         <p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i> Saldo tessera: <%= tessera.getSaldo() %></p>
         <p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i> <%=utente.getNome() %> <%=utente.getCognome() %></p>
         <p><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i> Tipo cliente: <%= tessera.getTipo() %></p>
        </div>
      </div>
      <br>
      
      <!-- Accordion -->
      <div class="w3-card w3-round">
        <div class="w3-white">
          <button onclick="myFunction('Demo1')" class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-circle-o-notch fa-fw w3-margin-right"></i> My Groups</button>
          <div id="Demo1" class="w3-hide w3-container">
            <p>Some text..</p>
          </div>
          

          <button onclick="myFunction('Demo2')" class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> My Events</button>

          <div id="Demo2" class="w3-hide w3-container">

<%
			if (prenotazioni != null && prenotazioni.size() != 0) {
				Iterator<?> it = prenotazioni.iterator();
				while (it.hasNext()) {
					PrenotazioneBean bean = (PrenotazioneBean) it.next();
		%>
			<p>Dettagli prenotazioni:</p>
            <p>Data: <%=bean.getData_prenotazione() %>, numero postazione: <%=bean.getNumero_postazione() %></p>
            <%
				}
			} else {
		%>
            <p>Nessuna prenotazione eseguita</p>
 		<%
			}
		%>
          </div>
          
                  	<%
				String url = request.getContextPath() + "/logout";
			%>
          <a href="<%= url%>"><button class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> Logout</button></a>
        </div>      
      </div>


    
    <!-- End Left Column -->
    </div>
    
    <!-- Middle Column -->
    <div class="w3-col m7">
      <div class="w3-container w3-card w3-white w3-round w3-margin"><br>
        <h4>Dettagli account:</h4><br>
        <hr class="w3-clear">
        <p>Nome: <%=utente.getNome() %></p>
        <p>Cognome: <%=utente.getCognome() %></p>
        <p>Username: <%=utente.getUsername() %></p>
        <p>E-mail: <%=utente.getEmail() %></p>
		<p>Password: <%=utente.getPassword() %></p>
      </div>
      
      <div class="w3-container w3-card w3-white w3-round w3-margin"><br>
        <h4>Dettagli tessera:</h4><br>
        <hr class="w3-clear">
		<p>Codice tessera: <%=tessera.getCodice() %></p>
        <p>Saldo: <%=tessera.getSaldo() %></p>
        <p>Tipo: <%=tessera.getTipo() %></p>
      </div>  

      <div class="w3-container w3-card w3-white w3-round w3-margin"><br>
        <h4>Cronologia prenotazioni:</h4><br>
        <hr class="w3-clear">
        <%
			if (prenotazioni != null && prenotazioni.size() != 0) {
				Iterator<?> it = prenotazioni.iterator();
				while (it.hasNext()) {
					PrenotazioneBean bean = (PrenotazioneBean) it.next();
		%>
            <p>Data: <%=bean.getData_prenotazione() %>, numero postazione: <%=bean.getNumero_postazione() %></p>
            <%
				}
			} else {
		%>
            <p>Nessuna prenotazione eseguita</p>
 		<%
			}
		%> 
      </div> 
      
    <!-- End Middle Column -->
    </div>
    
    <!-- Right Column -->
    <div class="w3-col m2">
      <div class="w3-card w3-round w3-white w3-center">
        <div class="w3-container">
          <p>Qui andranno le prenotazioni da usufruire:</p>
          <form action="/Fnatic-eSports-Gaming-Center/RestituisciUltimaPrenotazione" method="get">
          <input type="hidden" value="" name="data" id="data">
          <p><button class="w3-button w3-block w3-theme-l4">Info</button></p>
          </form>
          
          <script>
                      var d = new Date();
                      document.getElementById("data").value=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
         </script>
          <%
          Collection<?> pData = (Collection<?>) request.getAttribute("prenotazioni-per-data");
			if (pData != null && pData.size() != 0) {
				Iterator<?> it = pData.iterator();
				while (it.hasNext()) {
					PrenotazioneBean bean = (PrenotazioneBean) it.next();
		%>
			<p><strong>Data: <%=bean.getData_prenotazione() %></strong></p>
            <p>Numero postazione: <%=bean.getNumero_postazione() %></p>
            <%
				}
			} else {
		%>
            <p>Nessuna prenotazione eseguita</p>
 		<%
			}
		%>

        </div>
      </div>
      
    <!-- End Right Column -->
    </div>
    
  <!-- End Grid -->
  </div>
  
<!-- End Page Container -->
</div>
                    

<div id="footer"></div>
 
<script>
// Accordion
function myFunction(id) {
  var x = document.getElementById(id);
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
    x.previousElementSibling.className += " w3-theme-d1";
  } else { 
    x.className = x.className.replace("w3-show", "");
    x.previousElementSibling.className = 
    x.previousElementSibling.className.replace(" w3-theme-d1", "");
  }
}



// Used to toggle the menu on smaller screens when clicking on the menu button
function openNav() {
  var x = document.getElementById("navDemo");
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
  } else { 
    x.className = x.className.replace(" w3-show", "");
  }
}
</script>

</body>
</html> 
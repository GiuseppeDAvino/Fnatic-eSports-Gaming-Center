<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.*"%>

<%
	Collection<?> utenti = (Collection<?>) request.getAttribute("utenti");
	if(utenti == null) {
		response.sendRedirect("/Fnatic-eSports-Gaming-Center/UtenteControl");	
		return;
	}
	
	UtenteBean utente = (UtenteBean) request.getAttribute("utente");

%>

<!DOCTYPE html>
<html>

<head>
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Storage DS/BF</title>
</head>

<body>
	<h2>Products</h2>
	<a href="/Fnatic-eSports-Gaming-Center/UtenteControl">List</a>
	<table border="1">
		<tr>
			<th>Code <a href="product?sort=code">Sort</a></th>
			<th>Name <a href="product?sort=name">Sort</a></th>
			<th>Description <a href="product?sort=description">Sort</a></th>
			<th>Action</th>
		</tr>
		<%
			if (utenti != null && utenti.size() != 0) {
				Iterator<?> it = utenti.iterator();
				while (it.hasNext()) {
					UtenteBean bean = (UtenteBean) it.next();
		%>
		<tr>
			<td><img src="./getPictureUtente?username=<%= bean.getUsername() %>"  style="width:100px"></td>
			<td><%=bean.getEmail()%></td>
			<td><%=bean.getNome()%></td>
			<td><%=bean.getCognome()%></td>
			<td><%=bean.getUsername()%></td>

			<td><a href="/Fnatic-eSports-Gaming-Center/UtenteControl?action=delete&email=<%=bean.getEmail()%>">Delete</a><br>
				<a href="/Fnatic-eSports-Gaming-Center/UtenteControl?action=read&email=<%=bean.getEmail()%>">Details</a><br>
				</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<h2>Details</h2>
	<%
		if (utente != null) {
	%>
	<table border="1">
		<tr>
			<th>Email</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Username</th>
		</tr>
		<tr>
			<td><img src="./getPictureUtente?username=<%= utente.getUsername() %>" style="width:100px"></td>
			<td><%=utente.getEmail()%></td>
			<td><%=utente.getNome()%></td>
			<td><%=utente.getCognome()%></td>
			<td><%=utente.getUsername()%></td>

		</tr>
	</table>
	<%
		}
	%>

	
</body>
</html>
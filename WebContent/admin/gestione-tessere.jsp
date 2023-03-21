<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.*"%>

<%
	Collection<?> tessere = (Collection<?>) request.getAttribute("tessere");
	if(tessere == null) {
		response.sendRedirect("/Fnatic-eSports-Gaming-Center/TesseraControl");	
		return;
	}
	
	TesseraBean tessera = (TesseraBean) request.getAttribute("tessera");

%>

<!DOCTYPE html>
<html>

<head>
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Storage DS/BF</title>
</head>

<body>
	<h2>Products</h2>
	<a href="/Fnatic-eSports-Gaming-Center/TesseraControl">List</a>
	<table border="1">
		<tr>
			<th>Code <a href="product?sort=code">Sort</a></th>
			<th>Name <a href="product?sort=name">Sort</a></th>
			<th>Description <a href="product?sort=description">Sort</a></th>
			<th>Action</th>
		</tr>
		<%
			if (tessere != null && tessere.size() != 0) {
				Iterator<?> it = tessere.iterator();
				while (it.hasNext()) {
					TesseraBean bean = (TesseraBean) it.next();
		%>
		<tr>
			<td><%=bean.getCodice()%></td>
			<td><%=bean.getSaldo()%></td>
			<td><%=bean.getTipo()%></td>
			<td><%=bean.getEmail_utente()%></td>

			<td><a href="/Fnatic-eSports-Gaming-Center/TesseraControl?action=delete&email_utente=<%=bean.getEmail_utente()%>">Delete</a><br>
				<a href="/Fnatic-eSports-Gaming-Center/TesseraControl?action=read&email_utente=<%=bean.getEmail_utente()%>">Details</a><br>
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
		if (tessera != null) {
	%>
	<table border="1">
		<tr>
			<th>Email</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Username</th>
		</tr>
		<tr>
			<td><%=tessera.getCodice()%></td>
			<td><%=tessera.getSaldo()%></td>
			<td><%=tessera.getTipo()%></td>
			<td><%=tessera.getEmail_utente()%></td>

		</tr>
	</table>
	<%
		}
	%>

	
</body>
</html>
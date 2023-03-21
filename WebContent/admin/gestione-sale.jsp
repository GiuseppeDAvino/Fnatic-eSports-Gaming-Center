<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.*"%>

<%
	Collection<?> sale = (Collection<?>) request.getAttribute("sale");
	if(sale == null) {
		response.sendRedirect("/Fnatic-eSports-Gaming-Center/SalaControl");	
		return;
	}
	
	SalaBean sala = (SalaBean) request.getAttribute("sala");

%>

<!DOCTYPE html>
<html>

<head>
	<link href="/Fnatic-eSports-Gaming-Center/css/ProductStyle.css" rel="stylesheet" type="text/css">
	<link href="/Fnatic-eSports-Gaming-Center/css/style.css" rel="stylesheet" type="text/css">
	<title>Storage DS/BF</title>
</head>

<body>
	<div class="site-wrap">
        <header class="site-logo" role="banner">
                <div class="container">
                    <div class="row">
                        <div class="align-items-center text-center horizontal">
                            <div class="site-logo">
                                <a href="index.jsp"><img src="/Fnatic-eSports-Gaming-Center/immagini/fnatic.png" alt="logo" width="200"></a>
                            </div>
                        </div>
                    </div>
            </div>
        </header>
    </div>
	<h2>Lista delle sale</h2>

	<table border="1">
		<tr>
			<th>Codice sala</th>
			<th>Capacità</th>
			<th>Azioni</th>
		</tr>
		<%
			if (sale != null && sale.size() != 0) {
				Iterator<?> it = sale.iterator();
				while (it.hasNext()) {
					SalaBean bean = (SalaBean) it.next();
		%>
		<tr>
			<td><%=bean.getCodice_sala()%></td>
			<td><%=bean.getCapacita()%></td>
			<td><a href="/Fnatic-eSports-Gaming-Center/SalaControl?action=delete&codice=<%=bean.getCodice_sala()%>">Elimina</a><br>
				<a href="/Fnatic-eSports-Gaming-Center/SalaControl?action=read&codice=<%=bean.getCodice_sala()%>">Dettagli</a><br>
				</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessuna sala disponibile</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<%
		if (sala != null) {
	%>
	<h2>Dettagli</h2>
	<table border="1">
		<tr>
			<th>Codice sala</th>
			<th>Capacità</th>
		</tr>
		<tr>
			<td><%=sala.getCodice_sala()%></td>
			<td><%=sala.getCapacita()%></td>
		</tr>
	</table>
	<%
		}
	%>
	<h2>Insert</h2>
	<form action="/Fnatic-eSports-Gaming-Center/SalaControl" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="capacita">Capacità sala:</label><br> 
		<input name="capacita" type="number" min="1" value="1" required><br>

		<input type="submit" value="Add"><input type="reset" value="Reset">
	</form>

	
</body>
</html>
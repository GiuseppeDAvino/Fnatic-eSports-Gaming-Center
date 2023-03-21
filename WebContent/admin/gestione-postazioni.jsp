<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.*"%>

<%
	Collection<?> postazioni = (Collection<?>) request.getAttribute("postazioni");
	if(postazioni == null) {
		response.sendRedirect("/Fnatic-eSports-Gaming-Center/PostazioneControl");	
		return;
	}
	
	PostazioneBean postazione = (PostazioneBean) request.getAttribute("postazione");

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

	<h2>Lista delle postazioni</h2>
	<table border="1">
		<tr>
			<th>Immagine</th>
			<th>Codice postazione</th>
			<th>Tipo</th>
			<th>Nome</th>
			<th>Descrizione</th>
			<th>Costo</th>
			<th>Codice sala</th>
			<th>Action</th>
		</tr>
		<%
			if (postazioni != null && postazioni.size() != 0) {
				Iterator<?> it = postazioni.iterator();
				while (it.hasNext()) {
					PostazioneBean bean = (PostazioneBean) it.next();
		%>
		<tr>
			<td><img src="./getPicturePostazione?n_posto=<%= bean.getN_posto() %>"  style="width:100px"></td>
			<td><%=bean.getN_posto()%></td>
			<td><%=bean.getTipo()%></td>
			<td><%=bean.getNome_postazione()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><%=bean.getCosto()%></td>
			<td><%=bean.getCodice_sala()%></td>
			<td><a href="/Fnatic-eSports-Gaming-Center/PostazioneControl?action=delete&n_posto=<%=bean.getN_posto()%>">Delete</a><br>
				<a href="/Fnatic-eSports-Gaming-Center/PostazioneControl?action=read&n_posto=<%=bean.getN_posto()%>">Details</a><br>
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
		if (postazione != null) {
	%>
	<table border="1">
		<tr>
			<th>Numero posto</th>
			<th>Tipo</th>
			<th>Nome postazione</th>
			<th>Descrizione</th>
			<th>Costo</th>
			<th>Codice sala</th>
		</tr>
		<tr>
			<td><img src="./getPicturePostazione?n_posto=<%= postazione.getN_posto() %>" style="width:100px"></td>
			<td><%=postazione.getN_posto()%></td>
			<td><%=postazione.getTipo()%></td>
			<td><%=postazione.getNome_postazione()%></td>
			<td><%=postazione.getDescrizione()%></td>
			<td><%=postazione.getCosto()%></td>
			<td><%=postazione.getCodice_sala()%></td>
		</tr>
	</table>
	<%
		}
	%>
	<h2>Insert</h2>
	<form action="/Fnatic-eSports-Gaming-Center/PostazioneControl" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="tipo">Tipo:</label><br> 
		<input name="tipo" type="text" maxlength="20" required placeholder="Inserisci tipo postazione"><br> 
		
		<label for="nome_postazione">Nome postazione:</label><br> 
		<input name="nome_postazione" type="text" maxlength="20"  required placeholder="Inserisci nome postazione"><br>
		
		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="100" rows="3" required placeholder="inserisci descrizione postazione"></textarea><br>
		
		<label for="costo">Costo:</label><br> 
		<input name="costo" type="number" min="0" value="0" required><br>

		<label for="codice_sala">Codice sala:</label><br> 
		<input name="codice_sala" type="number" min="1" value="1" required><br>

		<input type="submit" value="Add"><input type="reset" value="Reset">
	</form>

 	<form action="UploadPhotoPostazione" enctype="multipart/form-data" method="post">
    	<label for="n_posto">Code:</label><br> 
    	<input name="n_posto" type="text" maxlength="20" required placeholder="enter code"><br>
    	<label for="photo">Photo:</label><br>
        <input name="photo" class="file" type="file" value="" maxlength="255">
        <input type="submit" value="Add">
    </form>
	
</body>
</html>
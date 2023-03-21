<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<% String errore = (String) request.getAttribute("errore");
		%>
	<h3>Si è verificato il seguente errore: <%=errore %></h3>
	<a href="login.jsp">Ritenta</a>
</body>
</html>
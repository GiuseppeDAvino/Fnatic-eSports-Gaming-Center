<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<title>Login Page</title>
</head>
	<link rel="stylesheet" href="/Fnatic-eSports-Gaming-Center/bootstrap/css/bootstrap-grid.min.css">
	<link rel="stylesheet" href="/Fnatic-eSports-Gaming-Center/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/Fnatic-eSports-Gaming-Center/css/login.css">
	<link rel="stylesheet" href="/Fnatic-eSports-Gaming-Center/css/style.css">
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
    
    <div id="logreg-forms">
        <form class="form-signin" action='<%= response.encodeURL("j_security_check") %>' method="post">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Accedi alla pagina riservata</h1>

            <input type="text" name="j_username" class="form-control" placeholder="utente" required>
            <input type="password" name="j_password" class="form-control" placeholder="password" required>
            
            <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Accedi</button>
        </form>
    </div>
</html>
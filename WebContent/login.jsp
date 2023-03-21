<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-grid.min.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="css/style.css">
	<title>Login</title>
</head>
<body>
	<div class="site-wrap">
        <header class="site-logo" role="banner">
                <div class="container">
                    <div class="row">
                        <div class="align-items-center text-center horizontal">
                            <div class="site-logo">
                                <a href="index.jsp"><img src="immagini/fnatic.png" alt="logo" width="200"></a>
                            </div>
                        </div>
                    </div>
            </div>
        </header>
    </div>
    
    <div id="logreg-forms">
        <form class="form-signin" action="<%= request.getContextPath() %>/login" method="post">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Accedi</h1>

            <input type="email" name="email" class="form-control" placeholder="Email" required="" autofocus="">
            <input type="password" name="password" class="form-control" placeholder="Password" required="">
            
            <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Accedi</button>
            <a href="#" id="forgot_pswd">Password dimenticata?</a>
            <hr>
            <!-- <p>Don't have an account!</p>  -->
            <button class="btn btn-primary btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> Registrati</button>
            </form>

            <form action="/reset/password/" class="form-reset">
                <input type="email" id="resetEmail" class="form-control" placeholder="Email" required="" autofocus="">
                <button class="btn btn-primary btn-block" type="submit">Cambia Password</button>
                <a href="#" id="cancel_reset"><i class="fas fa-angle-left"></i> Indietro</a>
            </form>
            
            <form action="<%= request.getContextPath() %>/registrazione" method="post" class="form-signup">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Registrati</h1>

				<input type="text" name="nome" class="form-control" placeholder="Nome" required>
				<input type="text" name="cognome" class="form-control" placeholder="Cognome" required>
                <input type="text" name="username" class="form-control" placeholder="Username" required>
                <input type="email" name="email" class="form-control" placeholder="Email" required>
                <select name="tipo-tessera" class="form-control">
                	<option>Seleziona un tipo di tessera</option>
                	<option value="STANDARD">STANDARD</option>
                	<option value="PREMIUM">PREMIUM</option>
                </select>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <input type="password" name="confermapassword" class="form-control" placeholder="Ripeti Password" required>

                <button  onClick = "return validate(this)" class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Registrati</button>
                <a href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Indietro</a>
            </form>
            <br>
            
    </div>
    <p style="text-align:center">
        <a href="http://bit.ly/2RjWFMfunction toggleResetPswd(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle() // display:block or none
    $('#logreg-forms .form-reset').toggle() // display:block or none
}

function toggleSignUp(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle(); // display:block or none
    $('#logreg-forms .form-signup').toggle(); // display:block or none
}

$(()=>{
    // Login Register Form
    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
    $('#logreg-forms #btn-signup').click(toggleSignUp);
    $('#logreg-forms #cancel_signup').click(toggleSignUp);
})g" target="_blank" style="color:black"></a>
    </p>
    <script src="bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="js/login.js"></script>
	

</body>
</html>
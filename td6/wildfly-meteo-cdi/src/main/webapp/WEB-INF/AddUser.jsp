<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvel utilisateur</title>
</head>

<body>
	<h2>Nouvel utilisateur</h2>

	<form action="/wildfly-meteo-cdi/newuser" method='get'>
		Nom d'utilisateur :<br /> <input type='text' name='name'><br />
		<input type='submit' value='Créer' />
	</form>
</body>
</html>
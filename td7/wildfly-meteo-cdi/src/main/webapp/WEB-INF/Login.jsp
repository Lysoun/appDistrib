<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>

<body>
	<c:if test="${ not empty userList }">
		<h2>Liste des utilisateurs :</h2>
		<table>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>:</td>
					<td>${user.count}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<br />

	<form action="/wildfly-meteo-cdi/login" method='post'> <%-- On veut modifier l'état du serveur --> post --%>
		Nom d'utilisateur :<br /> <input type='text' name='name'><br />
		<input type='submit' value='Se connecter' />
	</form>

	<c:if test="${ not empty userNotFound }">
		Cet utilisateur n'est pas encore enregistré. Vous pouvez créer un nouvel utilisateur ou vous connecter avec un des logins existants.
	</c:if>

	<form action="/wildfly-meteo-cdi/newuser" method='get'>  <%-- On veut modifier l'état du serveur --> post --%>
		<input type='submit' value='Nouvel utilisateur' />
	</form>

</body>
</html>
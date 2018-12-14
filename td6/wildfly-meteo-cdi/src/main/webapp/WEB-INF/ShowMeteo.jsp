<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Météo</title>
</head>

<body>
	<c:if test="${ not empty name}">
		<p>
			Bienvenue <b>${ name }</b> !
		</p>
	</c:if>

	<form action="/wildfly-meteo-cdi/meteo" method='get'>
		Ville :<br /> <input type='text' name='city'><br /> <input type='submit' value='Valider' />
	</form>

	<c:choose>
		<c:when test="${ not empty city and not empty temp }">
			<p>
				Il fait ${ temp } degrés à <b>${ city }</b>.
			</p>
		</c:when>
		<c:otherwise>
			<p>Veuillez saisir un nom de ville.</p>
		</c:otherwise>
	</c:choose>
	
	<form action="/wildfly-meteo-cdi/login" method='get'>
		<input type='submit' value='Déconnexion' />
	</form>
</body>
</html>
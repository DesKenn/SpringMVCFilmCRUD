<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to the MVC Film Site</title>
</head>
<body>
	<h1>Welcome to the Film Site</h1>

	<form action="index.do" method="GET">
		Enter a film ID: <input type="text" name="film id" /> <input
			type="submit" value="Submit" />
	</form>

	
</body>
</html>
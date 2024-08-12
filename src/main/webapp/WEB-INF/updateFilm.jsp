<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="jakarta.tags.core" prefix="c"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update a Film</title>
</head>
<body>
	<form:form action="updateFilm.do" method="POST" modelAttribute="film">
		<form:label path="id">Film Id: ${film.id }</form:label>
		<form:input path="id" type="hidden" />
		<form:errors path="id" />
		<br />
		<form:label path="title">Title:</form:label>
		<form:input path="title" />
		<form:errors path="title" />
		<br />
		<br />
		<form:label path="description">Description:</form:label>
		<form:input path="description" />
		<form:errors path="description" />
		<br />
		<form:label path="releaseYear">Release Year:</form:label>
		<form:input path="releaseYear" />
		<form:errors path="releaseYear" />
		<br />

		<form:label path="rentalDuration">Rental Duration:</form:label>
		<form:input path="rentalDuration" />
		<form:errors path="rentalDuration" />
		<br />
		<form:label path="rentalRate">Rental Rate:</form:label>
		<form:input path="rentalRate" />
		<form:errors path="rentalRate" />
		<br />
		<form:label path="length">Film Duration:</form:label>
		<form:input path="length" />
		<form:errors path="length" />
		<br />
		<form:label path="replacementCost">Replacement Cost:</form:label>
		<form:input path="replacementCost" />
		<form:errors path="replacementCost" />
		<br />
		<input type="submit" value="Save Changes">
	</form:form>
	<form action="index.do" method="GET">
		<input type="submit" value="Home">
	</form>

</body>
</html>
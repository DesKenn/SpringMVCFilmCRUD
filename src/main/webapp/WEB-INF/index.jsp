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

	<h1>Add A New Film</h1>
	<form action="index.do" method="POST" modelAttribute="film">

		<form:label path="title">Title: </form:label>
		<br>
		<form:input class="input" path="title" placeholder="Enter Title" />
		<form:errors path="title" />

		<br>
		<form:label path="description">Description:</form:label>
		<br>
		<form:input class="input" path="description"
			placeholder="Enter Description" />
		<br>
		<form:label path="releaseYear">Release Year:</form:label>
		<br>
		<form:input class="input" path="releaseYear" min="0" max="2040"
			type="number" placeholder="Year" />
		<form:errors path="releaseYear" />
		<br>
		<form:label path="rentalDuration">Rental Duration:</form:label>
		<br>
		<form:input class="input" path="rentalDuration" min="0" type="number"
			placeholder="Enter Days" />
		<form:errors path="rentalDuration" />
		<br>
		<form:label path="length">Length:</form:label>
		<br>
		<form:input class="input" path="length" min="0" type="number"
			placeholder="Enter Length In Minutes" />
		<form:errors path="length" />
		<br>
		<form:label path="replacementCost">Replacement Cost:</form:label>
		<br>
		<form:input class="input" path="replacementCost" type="number" min="0"
			placeholder="Enter Cost" />
		<br>
		<form:label path="rating">Rating:</form:label>
		<br>
		<form:input class="input" path="rating" required="required"
			placeholder="Enter Rating" />
		<form:errors path="rating" />
		<br>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Added</title>
</head>
<body>
<form:form action="createFilm.do" method="POST" modelAttribute="film">
			<form:label path="title">Title:</form:label>
			<form:input path="title" />
			<form:errors path="title" />
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
			<form:label path="length">Length:</form:label>
			<form:input path="length" />
			<form:errors path="length" />
			<br />
			<form:label path="replacementCost">Replacement Cost:</form:label>
			<form:input path="replacementCost" />
			<form:errors path="replacementCost" />
			<br />

			<label for="title">Film Rating</label>
			<br>
			<input type="radio" name="rating" value="G"> G 
			<input type="radio" name="rating" value="PG"> PG <br />
			<input type="radio" name="rating" value="PG13"> PG-13 
			<input type="radio" name="rating" value="R"> R 
			<input type="radio" name="rating" value="NC17"> NC-17 <br />
			<label for="title">Special Features</label>
			<br>
			<input type="checkbox" name="specitalFeatures" value="Deleted Scenes">Deleted Scenes<br />
			<input type="checkbox" name="specitalFeatures" value="Behind the Scenes">Behind the Scenes<br />

				<input type="submit" value="Add Film">
		</form:form>
				<form action="index.do" method="GET">
			<input type="submit" value="Main page">
		</form>

</body>
</html>
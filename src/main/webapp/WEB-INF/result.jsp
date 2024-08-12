<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Search Result</title>
</head>
<body>

	<c:forEach var="film" items="${searchResults }">
		<br>

		<h3>${film.title}</h3>

		<p>
			Film Id: ${film.id }<br> Released on: ${film.releaseYear} <br>
			Film Rating: ${film.rating} <br>

			<c:if test="${! empty film.language}">Language: ${film.language} <br>
			</c:if>


			Film Length: ${film.length}<br>
		</p>

		<p>Description: ${film.description}</p>
						Rental Duration (days): ${film.rentalDuration} | Rental Rate: $${film.rentalRate} | 
						Replacement Cost: $${film.replacementCost}<br>
						Special Features: ${film.specitalFeatures}
		
					<p>
			Cast:<br>
			<c:forEach var="actor" items="${film.cast }"> * ${actor } <br>
			</c:forEach>
			<br>
			<c:forEach var="filmCategories" items="${film.categories }">  ${filmCategories.categoryName} <br>
			</c:forEach>
		</p>

		<form action="index.do" method="GET">
			<input type="submit" value="Home">
		</form>
	</c:forEach>
</body>
</html>
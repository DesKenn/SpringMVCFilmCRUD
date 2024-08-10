<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
				<c:forEach var="film" items="${searchResults }">

<h3>${film.title}</h3>

				<p>
					Id: ${film.id }<br>
					 Released on: ${film.releaseYear} <br>
					Rated: ${film.rating} <br>
					 Language: ${film.language} <br>
					Duration (min): ${film.length}<br>
				</p>

				<p>Description: ${film.description}</p>
					Rental Duration (days): ${film.rentalDuration} | Rental Rate: $${film.rentalRate} | 
					Replacement Cost: $${film.replacementCost}<br>
					Special Features: ${film.specitalFeatures}
					</c:forEach>


				<c:forEach var="actor" items="${film.cast }"> * ${actor } <br>
				</c:forEach>
				<br>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix= "t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<c:choose>  
    <c:when test="${user != null}">  
       <t:nav>
		</t:nav> 
    </c:when>  
    
    <c:otherwise>  
        <t:nav2>
		</t:nav2>  
    </c:otherwise>  
</c:choose>
<t:search>
</t:search>

<c:choose>
<c:when test="${user != null}">
			    <h4>Welcome ${user.firstName} ${user.lastName}</h4>
			    </c:when>
			    <c:otherwise>
			    <h4>Welcome to <strong> MyRecipe</strong></h4>
			    <p>To like, save and add your favorite recipe please logIn</p>
			    </c:otherwise>
			    </c:choose>
<c:if test="${user != null}">
	<c:choose>

	  
		 <c:when test="${recipe.userSaves.contains(user)}">
		 	<a href="/recipe/${recipe.id}/unbookmark">Remove From Your Saved Recipe List</a>
		 </c:when>
		 <c:otherwise>
		 	<a href="/recipe/${recipe.id}/bookmark">Add To Your Saved Recipe List </a>
		 </c:otherwise>
	
	 
	</c:choose> 
 </c:if>
<p>${recipe.id}</p>
<p>${recipe.title}</p>
<p>${recipe.prepTime}</p>
<%-- <p><img src = "${recipe.image_url}" height = "400" width = "300"/></p> --%>
<c:forEach items="${recipe.ingrediants}" var="i">
    <c:out value="${i.ingredient.name}"/>
    <c:out value="${i.quantity}"/>
    
    
</c:forEach>

<p>${recipe.steps}</p>
<c:if test="${user != null}">
	<c:choose>

	  
		 <c:when test="${recipe.userLiked.contains(user)}">
		 	<a href="/recipe/${recipe.id}/dislike">DisLike</a>
		 </c:when>
		 <c:otherwise>
		 	<a href="/recipe/${recipe.id}/like">Like</a>
		 </c:otherwise>
	
	 
	</c:choose> 
 </c:if>
 <c:choose>  
    <c:when test="${recipe.user.id == user.id}">  
    <form action = "/recipe/${recipe.id}/delete" method = "POST">
       <button class="btn btn-danger">Delete</button>
</form>
     <a href="/recipe/${recipe.id}/edit">Edit</a>  

    </c:when>  
    <c:otherwise>  
      
    </c:otherwise>  
</c:choose> 


</body>
</html>
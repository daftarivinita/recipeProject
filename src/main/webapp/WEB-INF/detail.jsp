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
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<c:choose>  
    <c:when test="${user != null}">  
       <t:nav></t:nav> 
		<t:search></t:search>
    </c:when>  
    <c:otherwise>  
        <t:nav2></t:nav2>
		<t:search></t:search>
		<t:header></t:header>
    </c:otherwise>  
</c:choose>
<div class = "container">
<c:choose>
	<c:when test="${user != null}">
	    <h1 class="mt-5 mb-2 ">Welcome ${user.firstName} ${user.lastName}</h1>
	    <hr>
	</c:when>
	<c:otherwise>
	    <h1 class="mt-5">Welcome to <strong> MyRecipe</strong></h1>
	    <p>To like, save and add your favorite recipe please <a href="/user">LOGIN</a></p>
	    <hr>
	    <h2>${recipe.title}</h2>
	</c:otherwise>
</c:choose>

<c:if test="${user != null}">
<div class="row">
	<h2>${recipe.title}</h2>
	
	<c:choose>
		 <c:when test="${recipe.userSaves.contains(user)}">
		 	<a href="/recipe/${recipe.id}/unbookmark" class="btn btn-danger text-right">Unsave</a>
		 </c:when>
		 <c:otherwise>
		 	<a href="/recipe/${recipe.id}/bookmark" class="btn btn-success">Save</a>
		 </c:otherwise>
	</c:choose> 
	</div>
 </c:if>

<p>Prep Time: <span>${recipe.prepTime}</span></p>
	  
	
	 
<c:if test="${recipe.pictures.size() != 0}">

<img class="img-fluid rounded mx-auto d-block" src= "${recipe.pictures[0].image_url}" height = "400" width = "500"/>
</c:if>
<ul>
<c:forEach items="${recipe.ingrediants}" var="i">
  <li><c:out value="${i.ingredient.name}"/>, <c:out value="${i.quantity}"/></li>
</c:forEach>
</ul>
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
<hr>
<c:if test="${user != null}">
	<h3>All Recipe Submitted By You</h3>
	<ul>
	<c:forEach items="${user.receipeCreated}" var="r">
  	<li><a href= "/recipe/${r.id}"><c:out value="${r.title}"/></a></li>
</c:forEach>
</ul>
	
 </c:if>


</div>
</body>
</html>
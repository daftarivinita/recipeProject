<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix= "t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body class="background">
<div>
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
<p class = "text-center display-4"><strong>${errorFromApi}</strong></p>
<p class = "text-center display-4"><strong>${searchError}</strong></p>
<h1 class= "my-4">Welcome ${user.firstName} ${user.lastName}</h1>
<hr>
<h4 class= "my-4">Recent Recipes</h4>
<div class = "row">
<c:forEach items="${newRecipes}" var="r">
	<div class = "col">
  
     <img src = "${r.pictures[0].image_url}" height = "300" width = "250"/>
     <p> <a href= "/recipe/${r.id}"><c:out value="${r.title}"/></a></p> 
     </div>   
</c:forEach>
</div>

<hr>
<h4 class= "my-4">All Time Favorites</h4>
<div class = "row">
<c:forEach items="${topLiked}" var="r">
 <div class = "col">
   <img src = "${r.pictures[0].image_url}" height = "300" width = "250"/>  
   <p> <a href= "/recipe/${r.id}"><c:out value="${r.title}"/></a></p> 
   </div>    
</c:forEach>
</div>
<hr>

<h4 class= "my-3">All Recipe</h4>
<ul>
<c:forEach items="${allRecipe}" var="r">
        <li><a href= "/recipe/${r.id}"><c:out value="${r.title}"/></a></li> 
       
</c:forEach>
</ul>
</div>

</div>
</body>
</html>
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
<title>All Your Saved Recipe</title>
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
<h1 class= "my-5">Welcome ${user.firstName} ${user.lastName}</h1>

<c:choose>  
    <c:when test="${allRecipe.size() == 0}">  
       <h4>No Recipe Bookmarked</h4>
       <p>Go to <a href = "/">Home</a> to add some Recipe to your Favorite</p>
    </c:when>  
    <c:otherwise>  
    <div class= "row">
        <c:forEach items="${allRecipe}" var="r">
  <div class = "col">
  
     <img src = "${r.pictures[0].image_url}" height = "300" width = "250"/>
     
     <p class= "link-success"><a href= "/recipe/${r.id}"><c:out value="${r.title}"/> </a></p> 
     
   <a href="/recipe/${r.id}/unbookmark" class="btn btn-success">Remove</a>
  
   </div> 
   
</c:forEach>
</div>
    </c:otherwise>  
</c:choose>
<!-- <ul>
  <li><a href="https://www.facebook.com/sharer.php?..." target="blank" rel="noopener noreferrer"><img src="facebook-icon.png" alt="Share Page on Facebook" /></a></li>
  <li><a href="https://twitter.com/intent/tweet?..." target="blank" rel="noopener noreferrer"><img src="twitter-icon.png" alt="Share Page on Twitter" /></a></li>
  <li><a href="https://www.linkedin.com/shareArticle?..." target="blank" rel="noopener noreferrer"><img src="linkedin-icon.png" alt="Share Page on LinkedIn" /></a></li>
</ul> -->


</div>
</body>
</html>
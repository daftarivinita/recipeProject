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
<div class="container">
<c:choose>
	<c:when test="${user != null}">
	    <h1 class= "my-3">Welcome ${user.firstName} ${user.lastName}</h1>
	</c:when>
	<c:otherwise>
	    <h1 class= "my-3">Welcome to <strong> MyRecipe</strong></h1>
	    <h4>To like, save and add your favorite recipe please logIn</h4>
	</c:otherwise>
</c:choose>
<hr>
<div class= "row">
        <c:forEach items="${allRecipe}" var="r">
  <div class = "col-12 my-2">
  
     <img src = "${r.pictures[0].image_url}" height = "300" width = "250"/>
     
     <p class= "link-success"><a href= "/recipe/${r.id}"><c:out value="${r.title}"/> </a></p> 
     
   <hr>
   </div> 
   
</c:forEach>
</div>
</div>
</body>
</html>
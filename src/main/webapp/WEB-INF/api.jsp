<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix= "t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
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
<h1>Results from Youtube</h1>
<div class="row">
<c:forEach items="${meals}" var="meal">
<div class="col p-2">
<img src='${meal.getString("strMealThumb")}' height = "300" width = "250"/>
<p class="text-center"><a href='/recipe/${meal.getString("idMeal")}/api' target="blank">Click Here</a></p>
<p class="text-center"> ${meal.getString("strMeal")}</p>
</div>
 </c:forEach>
</div>
</div>
</body>
</html>
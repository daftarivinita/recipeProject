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

<h1>Welcome ${user.firstName} ${user.lastName}</h1>
<h4>Recent Recipe</h4>
<hr>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
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
<title>Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<t:nav2></t:nav2>  
    
<t:search></t:search>

<div class = "container">
 <div class="row align-items-start">
<div class="col">
<p>${errors}</p>
<h2>Register</h2>
	<hr>
		
			<form:form action="/user/loginToRegister" method="post" modelAttribute="user">
				<div class = "form-group row">
				    <p>
				        <form:label path="firstName">First Name</form:label>
				        <form:errors path="firstName"/>
				        <form:input path="firstName" class="form-control"/>
				    </p>
				 </div>
				 <div class = "form-group row">
				    <p>
				        <form:label path="lastName">Last Name</form:label>
				        <form:errors path="lastName"/>
				        <form:input path="lastName"  class="form-control"/>
				    </p>
				 </div>
				 <div class = "form-group row">
				    <p>
				        <form:label path="email">Email</form:label>
				        <form:errors path="email"/>
				        <form:input type = "email" path="email"  class="form-control"/>
				    </p>
				 </div>
				 <div class = "form-group row">
				    <p>
				        <form:label path="password">Password</form:label>
				        <form:errors path="password"/>
				        <form:input type = "password" path="password"  class="form-control"/>
				    </p>
				 </div>
				 <div class = "form-group row">
				    <p>
				        <form:label path="confirmPassword">Confirm Password</form:label>
				        <form:errors path="confirmPassword"/>
				        <form:input type = "password" path="confirmPassword"  class="form-control"/>
				    </p>
				 </div>
			    <input type="submit" value="Submit"/>
			</form:form>  
	 
				 
		</div>		     
		<div class="col">
		<h2>Who wants to Login</h2>
		<hr>
		<p>${loginError}</p>
		<form action = "/user/login" method = "POST">
		<div class = "form-group row">
		<label>Email Address</label>
		<input type= "email" name= "lemail">
		</div>
		<div class = "form-group row">
		<label>password</label>
		<input type= "password" name= "lpassword">	
		</div>	
	 <input type="submit" value="Log In"/>
		</form>
		</div>  
	
	</div>
 </div>
</body>
</html>
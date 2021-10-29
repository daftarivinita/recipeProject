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
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<t:nav2></t:nav2>  
    
<t:search></t:search>

<div class = "container">
<p>${loginError}</p>
<p>${errors}</p>
 <div class="row align-items-start">
<div class="col">
<h2>Register</h2>
<hr>


		
<form:form class= "border border-success pt-3 px-3 pr-5" action="/user/loginToRegister" method="post" modelAttribute="user">
	
	   <div class="form-group row">
	        <form:label class="col-sm-12 col-lg-2 col-form-label" path="firstName">FirstName</form:label>
	        <form:errors path="firstName"/>
	        <form:input path="firstName" class="col-sm-12 form-control col-lg-10"/>
				   </div>
<div class="form-group row">
				
				   
      <form:label class="col-sm-12 col-lg-2 col-form-label" path="lastName">LastName</form:label>
   <form:errors path="lastName"/>
   <form:input path="lastName"  class="col-sm-12 form-control col-lg-10"/>

	 </div>
	 <div class = "form-group row">
	   
	        <form:label class="col-sm-12 col-lg-2 col-form-label" path="email">Email</form:label>
	        <form:errors path="email"/>
	        <form:input type = "email" path="email"  class="col-sm-12 form-control col-lg-10"/>
	   
	 </div>
	 <div class = "form-group row">
	   
	        <form:label class="col-sm-12 col-lg-2 col-form-label" path="password">Password</form:label>
	        <form:errors path="password"/>
	        <form:input type = "password" path="password"  class="col-sm-12 form-control col-lg-10"/>
	   
	 </div>
	 <div class = "form-group row">
	   
	        <form:label class="col-sm-12 col-lg-2 col-form-label" path="confirmPassword">Confirm Password</form:label>
	        <form:errors path="confirmPassword"/>
	        <form:input type = "password" path="confirmPassword"  class="col-sm-12 form-control col-lg-10"/>
	    
	 </div>
    <input type="submit" value="Submit"/>
</form:form>  
	 
				 
		</div>		     
		<div class="col">
		<h2>Login</h2>
		<hr>
		
		<form class= "border border-success pt-3 px-3 pr-5"action = "/user/login" method = "POST">
		<div class = "form-group row">
		<label class="col-sm-12 col-lg-2 col-form-label pr-4">Email</label>
		<input class="col-sm-12 form-control col-lg-10" type= "email" name= "lemail">
		</div>
		<div class = "form-group row">
		<label class="col-sm-12 col-lg-2 col-form-label pr-4">password</label>
		<input class="col-sm-12 form-control col-lg-10" type= "password" name= "lpassword">	
		</div>	
	 <input type="submit" value="Log In"/>
		</form>
		</div>  
	
	</div>
 </div>
</body>
</html>
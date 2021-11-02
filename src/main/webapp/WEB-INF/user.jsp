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
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

<t:nav2></t:nav2>  
    
<t:search></t:search>
<t:header></t:header>

<div class = "container">
<p>${loginError}</p>
<p>${errors}</p>
 <div class="row">

	<div class= "col p-5">	
		
		<form  class="border border-success px-3 pt-2" action = "/user/login" method = "POST">
		<h2>Login</h2>
		<hr>
		<div class = "form-group row">
		<label class=" col-sm-2 col-form-label">Email</label>
		 <div class="col-sm-10">
		<input class="form-control" type= "email" name= "lemail">
		</div>
		</div>
		<div class = "form-group row">
		<label class=" col-sm-2 col-form-label">Password</label>
		 <div class="col-sm-10">
		<input class="form-control" type= "password" name= "lpassword">	
		 </div>
		</div>	
	 <div class= "d-flex flex-row-reverse">
	<p><button class="btn btn-success ">LogIn</button></p>
</div>
		</form>


</div>

	<div class= "col p-5">	
<form:form  class="border border-success px-3 pt-2" action="/user/loginToRegister" method="post" modelAttribute="user">
	<h2>Register</h2>
	<hr>
	   <div class="form-group row ">
	        <form:label class="col-sm-2 col-form-label pr-2" path="firstName">FirstName</form:label>
	        <form:errors path="firstName"/>
	         <div class="col-sm-10">
	        <form:input path="firstName" class="form-control"/>
	        </div>
				   </div>
<div class="form-group row">
				
				   
      <form:label class=" col-sm-2 col-form-label" path="lastName">LastName</form:label>
   <form:errors path="lastName"/>
    <div class="col-sm-10">
   <form:input path="lastName"  class="form-control"/>
   </div>

	 </div>
	 <div class = "form-group row">
	   
	        <form:label class="col-sm-2 col-form-label" path="email">Email</form:label>
	        <form:errors path="email"/>
	         <div class="col-sm-10">
	        <form:input type = "email" path="email"  class="form-control"/>
	        </div>
	   
	 </div>
	 <div class = "form-group row">
	   
	        <form:label class="col-sm-2 col-form-label" path="password">Password</form:label>
	        <form:errors path="password"/>
	         <div class="col-sm-10">
	        <form:input type = "password" path="password"  class="form-control"/>
	        </div>
	   
	 </div>
	 <div class = "form-group row">
	   
	        <form:label class="col-sm-2 col-form-label" path="confirmPassword">Confirm-Password</form:label>
	        <form:errors path="confirmPassword"/>
	         <div class="col-sm-10">
	        <form:input type = "password" path="confirmPassword"  class="form-control"/>
	        </div>
	    
	 </div>
   <div class= "d-flex flex-row-reverse">
	<p><button class="btn btn-success ">Register</button></p>
</div>
</form:form>  
	 
				 
		</div>		     
		
		
	</div>
</div>	
	
 
</body>
</html>
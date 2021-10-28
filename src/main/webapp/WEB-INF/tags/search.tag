<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark mt-5 border border-success border-5">
  <div class="container-fluid ">
    
    <div class="collapse navbar-collapse ml-auto d-flex flex-row-reverse " id="navbarCollapse">
    	
      	<form action= "/logout" method="POST" class="lead form-inline px-2 pt-2 pb-1">
	        <input class="form-control mr-sm-2" type="search" placeholder="Search By Ingredient" aria-label="Search">
	        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Go</button>
       </form>
       <form action= "/logout" method="POST" class="lead form-inline pt-2 pb-1">
	        <input class="form-control mr-sm-2" type="search" placeholder="Search By Recipe" aria-label="Search">
	        <button class="btn btn-outline-success my-2 my-sm-0 " type="submit">Go</button>
       </form>
      	</div>
      	
    
 
    </div>
<jsp:doBody/>
</nav>

</body>
</html>
      	
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

<div class = "container">
<h1>Thank you For submitting a WonderFul Recipe.</h1>


<form  class= "border border-success pt-3 px-3 pr-5"  action="/recipe/${recipe.id}/picture" method="POST" enctype="multipart/form-data">
	<h4>Please Upload A Picture</h4>
	<hr>
	<div class = "form-group">
	
		<input type = "file" name = "pic" class ="formFileLg"  id = "inlineFormInput">
	
	<input type="submit" class="btn btn-success  m-1"  value="Upload Pic"/>
	</div>
</form> 
	
	
<div class= "border border-success pt-3 px-3 pr-5">		
<form:form method="POST" action="/recipe/${recipe.id}/edit" modelAttribute="recipe">
<div class="form-group row">
	<form:label class="col-sm-12 col-lg-2 col-form-label" path="title">Title</form:label>
	<form:errors path="title"/>
	<form:input type="text" class="col-sm-12 form-control col-lg-10" path="title"/>
</div>
<div class="form-group row">
	<form:label class="col-sm-12 col-lg-2 col-form-label" path="prepTime">Preparation Time</form:label>
	<form:errors path="prepTime"/>
	<form:input type="text" class="col-sm-12 form-control col-lg-10" path="prepTime"/>
</div>


<div class= "table-responsive m-3">	
<table id="ingredientTable" class = "table table-bordered">
	

	<tbody>
	<c:forEach items="${recipe.ingrediants}" var="ingredientQuantity" varStatus="loop">
		<tr>
			<td>
				<form:label path="ingrediants[${loop.index}].ingredient.name">Ingredient</form:label>
				<form:errors path="ingrediants[${loop.index}]}.ingredient.name"/>
				<form:input class="form-control"  rows = "10" cols = "30" path="ingrediants[${loop.index}].ingredient.name"/>
			</td>
			<td>
				<form:label path="ingrediants[${loop.index}].quantity">quantity</form:label>
				<form:errors path="ingrediants[${loop.index}].quantity"/>
				<form:input class="form-control"  rows = "10" cols = "30" path="ingrediants[${loop.index}].quantity"/>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	
</table>
</div>
<div class= "d-flex flex-row-reverse">
<input type="button" class="mb-3 py-1 px-2 btn btn-success" id="addIngredientRowBtn" name="addIngredientRowBtn" value="Add Ingredient" onclick="myFunction()"/>
</div>
<div class="form-group row">
	<form:label class="col-sm-12 col-lg-2 col-form-label"  path="steps">Steps:</form:label>
	<form:errors path="steps"/>
	<form:textarea type="text" class="col-sm-12 form-control col-lg-10" path="steps"/>
</div>


	<p><button class="btn btn-success">Update Recipe</button></p>
</form:form>
<c:choose>  
    <c:when test="${recipe.user.id == user.id}">  
    <form action = "/recipe/${recipe.id}/delete" method = "POST">
    
       <button class="btn btn-danger mb-3">Delete</button>
       
</form>
       

    </c:when>  
    <c:otherwise>  
      
    </c:otherwise>  
</c:choose> 



</div>
</div>


<script>
function myFunction() {
	     var row = document.createElement("tr");
	     var ingredientNameCol = document.createElement("td");
	     var ingredientQuantityCol = document.createElement("td");
	     
	     // arraylist is 0 base
	     var nextIngredientCount = document.getElementById('ingredientTable').rows.length;
	     
	     var ingredientNameForm = ' \
	    	 <label for="ingrediants' + nextIngredientCount + '.ingredient.name">Ingredient</label> \
	    	 <input id="ingrediants' + nextIngredientCount + '.ingredient.name" name="ingrediants[' + nextIngredientCount + '].ingredient.name" rows="10" class="form-control" cols="30" type="text" value=""/> \
	     '
	     
	     var ingredientQuantityForm = ' \
	    	 <label for="ingrediants' + nextIngredientCount + '.quantity">quantity</label> \
			<input id="ingrediants' + nextIngredientCount + '.quantity" name="ingrediants[' + nextIngredientCount + '].quantity" rows="10" class="form-control" cols="30" type="text" value=""/> \
	     '
	     
	    ingredientNameCol.innerHTML = ingredientNameForm;
		ingredientQuantityCol.innerHTML = ingredientQuantityForm;
		row.appendChild(ingredientNameCol);
		row.appendChild(ingredientQuantityCol);
		
	     
	    document.getElementById('ingredientTable').getElementsByTagName('tbody')[0].appendChild(row);
}
</script>




</body>
</html>
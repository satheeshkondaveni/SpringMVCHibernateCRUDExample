<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    info="Guru Directive JSP" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="description" content="ePos Home Page">
<meta name="keywords" content="HTML,CSS,XML,AngularJS">
<meta name="Author " content="Satheesh.Kondaveni">
<title>EPoS Home Page</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css?v=0.0.1" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

<div class="container-fluid">
 <div class="row">
    <div class="col-sm-12 col-md-12" >
<form:form method="post" id="studentForm" modelAttribute="student" action="/SpringMVCHibernateCRUDExample/student/wsAddStudent.do" class="form-horizontal">
    <div class="form-group">
       <label style="text-align:center" class="col-sm-12 col-md-12 control-label ">Add Student</label>
      
    </div>
    <div class="form-group">
    	<form:hidden path="roll_no" />
        <form:label  class="col-sm-10 col-md-4  control-label" path="studentName">Student Name:</form:label>     
      <div class="col-sm-10 col-md-5 ">
       <form:input path="studentName" name="studentName" class="form-control" id="studentName" size="50" maxlength="50"></form:input>
      </div>
    </div>
     <div class="form-group">
    	<form:label path="branch" class="col-sm-10 col-md-4  control-label" >Branch:</form:label>
       
      <div class="col-sm-10 col-md-5 ">
      <form:input path="branch" name="branch" id="branch" class="form-control" size="50" maxlength="50"></form:input>
      </div>
    </div>
    
    <div class="form-group">
    	
       <form:label  path="marks" class="col-sm-10 col-md-4  control-label " >Marks:</form:label>
      <div class="col-sm-10 col-md-5 ">
       <form:input path="marks" name="marks" id="marks" class="form-control" size="3" maxlength="3"></form:input>
      </div>
    </div>
     <div class="form-group">
      <div class="col-sm-12 col-md-12 ">
       <input type="submit" class="btn btn-primary btn_align" class="blue-button" />
      </div>
    </div>

<%-- <table>
		<tr>
			<th colspan="2">Add Student</th>
		</tr>
		<tr>
		
          <td>
          <form:hidden path="roll_no" />
          <form:label path="studentName">Student Name:</form:label></td>
          <td><form:input path="studentName" name="studentName" class="form-control" id="studentName" size="50" maxlength="50"></form:input></td>
        </tr>
		<tr>
			<td><form:label path="branch">Branch:</form:label></td>
          <td><form:input path="branch" name="branch" id="branch" class="form-control" size="50" maxlength="50"></form:input></td>
		</tr>
		<tr>
			<td><form:label path="marks">Marks:</form:label></td>
          <td><form:input path="marks" name="marks" id="marks" class="form-control" size="3" maxlength="3"></form:input></td>
		</tr>
		
		<tr>
			<td colspan="2" class="td_center"><input type="submit"
				class="blue-button" /></td>
		</tr>
	</table>  --%>
</form:form>
</div>
</div>
 <div class="row">
    <div class="col-sm-12 col-md-12"  align="center">
<h3 >Student List</h3>
<c:if test="${!empty listOfStudents}">
	<table class="tg">
	<tr>
		<th width="80">Roll Number</th>
		<th width="120">Student Name</th>
		<th width="120">Branch</th>
		<th width="60">Marks</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listOfStudents}" var="obj">
		<tr>
			<td>${obj.roll_no}</td>
			<td>${obj.studentName}</td>
			<td>${obj.branch}</td>
			<td>${obj.marks}</td>
			
			<td><a href="<c:url value='/student/${obj.roll_no}/wsUpdateStudent.do' />" >Edit</a></td>
			<td><a href="<c:url value='/student/${obj.roll_no}/wsDeleteStudent.do' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<br/>
<a href="<c:url value='/student/getAllStudents.do' />" class="btn btn-primary "  >Back</a>
</div>
</div>
</div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js' type="application/javascript" ></script>
  <script  src="${pageContext.servletContext.contextPath}/js/student_jquery.js?v=0.0.1" type="application/javascript"></script>
</body>
</html>

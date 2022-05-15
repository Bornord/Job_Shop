<%@page import="jobShop_WebProject.utils.JsonConverter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="jobShop_WebProject.*"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VIEW STUDENTS</title>
</head>
<body>
<form method = "get" action = "Server">
<%
	Collection<Student> l = (Collection<Student>) request.getAttribute("l");
	for(Student s: l) {
%>
<%= JsonConverter.toJson(s)%> <br/>




<%} %>
</form>
</body>
</html>
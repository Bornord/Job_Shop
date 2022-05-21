<%@page import="jobShop_WebProject.utils.JsonConverter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="jobShop_WebProject.*"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VIEW ADMINS</title>
</head>
<body>
<form method = "get" action = "Server">
<%
	Collection<Admin> r = (Collection<Admin>) request.getAttribute("admin");
	for(Admin s: r) {
%>
<%= JsonConverter.toJson(s)%> <br/>




<%} %>
</form>
</body>
</html>
<%@page import="jobShop_WebProject.utils.JsonConverter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="jobShop_WebProject.*"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VIEW RECRUITERS</title>
</head>
<body>
<form method = "get" action = "Server">
<%
	Collection<Recruiter> r = (Collection<Recruiter>) request.getAttribute("r");
	for(Recruiter s: r) {
%>
<%= JsonConverter.toJson(s)%> <br/>




<%} %>
</form>
</body>
</html>
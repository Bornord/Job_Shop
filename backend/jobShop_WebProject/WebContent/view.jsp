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
<%= ("\"s" + s.getId() + "\" = { \"name\" : \"" + s.getName() +"\", \"surname\" : \""+ s.getSurname()
+"\",\"login\" : \""+s.getLogin()+"\",\"password\" : \""+s.getPassword()+"\"}\n\n")%> <br/>




<%} %>
</form>
</body>
</html>
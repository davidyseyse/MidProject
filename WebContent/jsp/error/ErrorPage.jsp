<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
Error Message:<br/>
<%=exception%><br/>

Exception Stack Message:<br/>
<%
	exception.getMessage();
%><hr>
<%
	exception.printStackTrace(new PrintWriter(out));
%><hr>
<%
	exception.fillInStackTrace();
    
%>

</body>
</html>
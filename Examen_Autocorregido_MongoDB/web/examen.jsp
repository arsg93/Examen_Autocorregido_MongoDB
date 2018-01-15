<%-- 
    Document   : exam
    Created on : 14-ene-2018, 17:30:11
    Author     : Andreu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String dni = (String) request.getAttribute("DNI"); %>

<%
    if (dni == null) {
        response.sendRedirect("index.html");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>

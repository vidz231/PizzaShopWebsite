<%-- 
    Document   : error
    Created on : Mar 9, 2024, 12:23:23 AM
    Author     : TRUNG VI
--%>
<%@page  isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%= exception.getMessage()%>
    </body>
</html> 

<%-- 
    Document   : register
    Created on : Mar 7, 2024, 2:52:36 PM
    Author     : TRUNG VI
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register</h1>
        <form action="UserController" method="POST">
            Username:<input type="text" name="txtUserName"/></br>
            Password:<input type="password" name ="txtPassword"/></br>
            Confirm Password:<input type="password" name ="txtConfirmPassword"/></br>
            FullName:<input type="text" name="txtFullName"/></br>

            <% if (request.getAttribute("message") != null) {%>
            <p style="color: red"><%=request.getAttribute("message")%></p>
            <%}%>
            <input type="submit" value="register" name="action"/>
            <a href="UserController?action=signin">Sign in</a>
        </form>
    </body>
</html>

<%-- 
    Document   : login.jsp
    Created on : Mar 7, 2024, 1:49:27 PM
    Author     : TRUNG VI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="LoginServlet" method="POST">
            Username:<input type="text" name="txtUserName"/></br>
            Password:<input type="password" name ="txtPassword"/></br>
            <input type="submit" value="signin" name="action"/>
        </form>

    </body>
</html>

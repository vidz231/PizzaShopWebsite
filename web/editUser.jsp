<%-- 
    Document   : editUser
    Created on : Mar 8, 2024, 10:50:52 PM
    Author     : TRUNG VI
--%>

<%@page import="Model.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit User</h1>
        <% User user = (User) request.getAttribute("user");%>
        <form action="UserController" method="POST">
            <input type="hidden" name="userId" value="<%=user.getAccountID()%>">
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username" value="<%=user.getUsername()%>"><br>
            <label for="fullName">Full Name:</label><br>
            <input type="text" id="fullName" name="fullName" value="<%=user.getFullName()%>"><br>
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" value="<%=user.getPassword()%>"><br>
            <label for="type">Type:</label><br>
            <input type="number" id="type" name="type" value="<%=user.getType()%>"><br>
            <% if (request.getAttribute("message") != null) {%>
            <p style="color: green"><%=request.getAttribute("message")%></p>
            <%}%>
            <div>
                <a href="UserController?action=view">Back</a>
                <input type="submit" name="action" value="Update">

            </div>

        </form>

    </body>
</html>

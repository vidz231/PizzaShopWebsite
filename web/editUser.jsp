<%-- 
    Document   : editUser
    Created on : Mar 8, 2024, 10:50:52 PM
    Author     : TRUNG VI
--%>

<%@page import="Model.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="Header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
    </head>
    <body>
        <div class="container ">
            <h1 class="text-center my-4">Edit User</h1>
            <% User user = (User) request.getAttribute("user");%>
            <form action="UserController" method="POST" class="mx-auto border border-1 p-5 rounded" style="max-width: 500px;">
                <input type="hidden" name="userId" value="<%=user.getAccountID()%>">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" value="<%=user.getUsername()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label for="fullName">Full Name:</label>
                    <input type="text" id="fullName" name="fullName" value="<%=user.getFullName()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" value="<%=user.getPassword()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label for="type">Type:</label>
                    <select id="type" name="type" class="form-control">
                        <option value="0" <%=user.getType() == 0 ? "selected" : ""%>>admin</option>
                        <option value="1" <%=user.getType() == 1 ? "selected" : ""%>>user</option>
                    </select>
                </div>
                <div class="form-group mt-3">
                    <a href="UserController?action=view" class="btn btn-secondary">Back</a>
                    <input type="submit" name="action" value="Update" class="btn btn-primary">
                </div>
            </form>
        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>

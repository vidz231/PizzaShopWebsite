<%-- 
    Document   : editUser
    Created on : Mar 8, 2024, 10:50:52 PM
    Author     : TRUNG VI
--%>

<%@page import="Model.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center my-4">Edit User</h1>
            <% User user = (User) request.getAttribute("user");%>
            <form action="UserController" method="POST" class="mx-auto" style="max-width: 500px;">
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
                    <input type="text" id="password" name="password" value="<%=user.getPassword()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label for="type">Type:</label>
                    <select id="type" name="type" class="form-control" de>
                        <option value="0" <%=user.getType() == 0 ? "selected" : ""%>>admin</option>
                        <option value="1" <%=user.getType() == 1 ? "selected" : ""%>>user</option>
                    </select>
                </div>
                <div class="form-group">
                    <a href="UserController?action=view" class="btn btn-secondary">Back</a>
                    <input type="submit" name="action" value="Update" class="btn btn-primary">
                </div>
            </form>
        </div>
                
                <% if (request.getAttribute("message") != null) {%>
                        <div class="alert alert-success fixed-top text-center mt-3" id="alert-message" style="margin-left: auto; margin-right: auto; left: 0; right: 0; width: 200px;">
                            <%=request.getAttribute("message")%>
                        </div>
                        <%}%>
                        <script>
                            document.addEventListener('DOMContentLoaded', function () {
                                setTimeout(function () {
                                    var alertMessage = document.getElementById('alert-message');
                                    alertMessage.style.opacity = "0";
                                    setTimeout(function () {
                                        alertMessage.style.display = "none";
                                    }, 1000); // waits for the fade out animation to finish
                                }, 2000); // fades out after 2 seconds
                            });
                        </script>

    </body>
</html>

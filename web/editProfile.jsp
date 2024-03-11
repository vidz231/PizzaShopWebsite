<%-- 
    Document   : editProfile
    Created on : Mar 10, 2024, 1:48:41 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center my-4">Edit Profile</h1>
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
                    <input type="password" id="password" name="password" value="<%=user.getPassword()%>" class="form-control">
                </div>

                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="number" id="phone" name="phone" value="<c:out value="${sessionScope.customer.phone}"/>"  class="form-control">
                </div>
                <div class="form-group">
                    <label for="shipAddress">Shipping Address:</label>
                    <input type="text" id="shipAddress" name="shipAddress" value ="${sessionScope.customer.address}"class="form-control">
                </div>
                <div class="form-group">
                    <a href="ProductController?action=view" class="btn btn-secondary">Back</a>
                    <input type="submit" name="action" value="update" class="btn btn-primary">
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


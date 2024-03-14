<%-- 
    Document   : register
    Created on : Mar 7, 2024, 2:52:36 PM
    Author     : TRUNG VI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="ProductController?action=view">PizzaShop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!-- Include your navigation content here -->
            </div>
        </nav>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title text-center">Register</h5>
                            <form action="UserController" method="POST">
                                <div class="mb-3">
                                    <label for="txtUserName" class="form-label">Username:</label>
                                    <input type="text" class="form-control" id="txtUserName" value="${requestScope.user.username}" name="txtUserName">
                                    <c:if test="${requestScope.isError==true}">
                                        <p class="text-danger">${requestScope.userError.usernameError}</p>
                                    </c:if>
                                </div>
                                <div class="mb-3">
                                    <label for="txtPassword" class="form-label">Password:</label>
                                    <input type="password" class="form-control" id="txtPassword" value="${requestScope.user.password}" name="txtPassword">
                                    <c:if test="${requestScope.isError==true}">
                                        <p class="text-danger">${requestScope.userError.passwordError}</p>
                                    </c:if>
                                </div>
                                <div class="mb-3">
                                    <label for="txtConfirmPassword" class="form-label">Confirm Password:</label>
                                    <input type="password" class="form-control" id="txtConfirmPassword" name="txtConfirmPassword">
                                    <c:if test="${requestScope.isError==true}">
                                        <p class="text-danger">${requestScope.userError.matchedPasswordError}</p>
                                    </c:if>
                                </div>
                                <div class="mb-3">
                                    <label for="txtFullName" class="form-label">Full Name:</label>
                                    <input type="text" class="form-control" id="txtFullName" name="txtFullName" value="${requestScope.user.fullName}">
                                    <c:if test="${requestScope.isError==true}">
                                        <p class="text-danger">${requestScope.userError.fullNameError}</p>
                                    </c:if>
                                </div>
                                <a href="UserController?action=signin" class="d-block text-left">Already have an account? Sign in here</a>
                                <button type="submit" class="btn btn-primary d-flex justify-content-center mx-auto" name="action" value="register">Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>


    </body>
</html>

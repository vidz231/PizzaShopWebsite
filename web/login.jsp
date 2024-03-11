<%-- 
    Document   : login.jsp
    Created on : Mar 7, 2024, 1:49:27 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <style>
            /* Add your custom CSS styles here */
        </style>
    </head>
    <body>
        <!-- Header -->
        <header>
            <!-- Include your header content here -->
            <jsp:include page="Header.jsp"/>
        </header>

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">PizzaShop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!-- Include your navigation content here -->
            </div>
        </nav>

        <!-- Main Content -->
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title text-center">Login</h5>
                            <form id="loginForm" action="UserController" method="POST">
                                <div class="mb-3">
                                    <label for="txtUserName" class="form-label">Username:</label>
                                    <input type="text" class="form-control" id="txtUserName" name="txtUserName">
                                </div>
                                <div class="mb-3">
                                    <label for="txtPassword" class="form-label">Password:</label>
                                    <input type="password" class="form-control" id="txtPassword" name="txtPassword">
                                </div>
                                <a href="UserController?action=register" class="d-block text-center">Register here</a>
                                <p id="errorMessage" class="text-danger text-center"></p>
                                <button type="submit" class="btn btn-primary btn-block" name="action" value="signin">Sign in</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer>
            <!-- Include your footer content here -->
            <jsp:include page="footer.jsp"/>
        </footer>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Custom JavaScript -->
        <script>
            // Add your custom JavaScript code here
            document.getElementById("loginForm").addEventListener("submit", function (event) {
                var username = document.getElementById("txtUserName").value.trim();
                var password = document.getElementById("txtPassword").value.trim();
                if (username === "" || password === "") {
                    document.getElementById("errorMessage").innerText = "Please enter both username and password.";
                    event.preventDefault();
                }
            });
        </script>
    </body>
</html>

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
        <jsp:include page="Header.jsp"/>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title text-center">Register</h5>
                            <form action="UserController" method="POST">
                                <div class="mb-3">
                                    <label for="txtUserName" class="form-label">Username:</label>
                                    <input type="text" class="form-control" id="txtUserName" name="txtUserName">
                                </div>
                                <div class="mb-3">
                                    <label for="txtPassword" class="form-label">Password:</label>
                                    <input type="password" class="form-control" id="txtPassword" name="txtPassword">
                                </div>
                                <div class="mb-3">
                                    <label for="txtConfirmPassword" class="form-label">Confirm Password:</label>
                                    <input type="password" class="form-control" id="txtConfirmPassword" name="txtConfirmPassword">
                                </div>
                                <div class="mb-3">
                                    <label for="txtFullName" class="form-label">Full Name:</label>
                                    <input type="text" class="form-control" id="txtFullName" name="txtFullName">
                                </div>
                                <% if (request.getAttribute("message") != null) {%>
                                <p class="text-danger text-center"><%=request.getAttribute("message")%></p>
                                <%}%>
                                <a href="UserController?action=signin" class="d-block text-center">Sign in</a>
                                <button type="submit" class="btn btn-primary btn-block" name="action" value="register">Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>


    </body>
</html>

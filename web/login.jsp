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
        <jsp:include page="Header.jsp"/>
        <nav>
            
        </nav>
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title text-center">Login</h5>
                            <form action="UserController" method="POST">
                                <div class="mb-3">
                                    <label for="txtUserName" class="form-label">Username:</label>
                                    <input type="text" class="form-control" id="txtUserName" name="txtUserName">
                                </div>
                                <div class="mb-3">
                                    <label for="txtPassword" class="form-label">Password:</label>
                                    <input type="password" class="form-control" id="txtPassword" name="txtPassword">
                                </div>
                                <a href="UserController?action=register" class="d-block text-center">Register here</a>
                                <% if (request.getAttribute("message") != null) {%>
                                <p class="text-danger text-center"><%=request.getAttribute("message")%></p>
                                <%}%>
                                <button type="submit" class="btn btn-primary btn-block" name="action" value="signin">Sign in</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>

    </body>
</html>

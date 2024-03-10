<%-- 
    Document   : createUser
    Created on : Mar 10, 2024, 4:45:23 PM
    Author     : TRUNG VI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <h1 class="text-center">Create User</h1>
        <div class="container ">
            <div class="row d-flex justify-content-center">
                <div class="col-6">
                    <form action="UserController?action=create" method="post">
                        <div class="form-group">
                            <label for="userName">Username:</label>
                            <input type="text" class="form-control" id="userName" name="userName">
                        </div>
                        <div class="form-group">
                            <label for="fullName">Full Name:</label>
                            <input type="text" class="form-control" id="fullName" name="fullName">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <div class="form-group">
                            <label for="type">Type:</label>
                            <input type="number" class="form-control" id="type" name="type">
                        </div>
                        <div>
                            <a href="UserController?action=view" class="btn btn-secondary">Back</a>
                            <button type="submit" class="btn btn-primary">Create</button>

                        </div>

                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>

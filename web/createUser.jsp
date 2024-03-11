<%-- 
    Document   : createUser
    Created on : Mar 10, 2024, 4:45:23 PM
    Author     : TRUNG VI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create User</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Header -->
    <jsp:include page="Header.jsp"/>

    <!-- Main Content -->
    <h1 class="text-center">Create User</h1>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">
                <!-- Form -->
                <form action="UserController?action=create" method="post">
                    <div class="mb-3">
                        <label for="userName" class="form-label">Username:</label>
                        <input type="text" class="form-control" id="userName" name="userName">
                    </div>
                    <div class="mb-3">
                        <label for="fullName" class="form-label">Full Name:</label>
                        <input type="text" class="form-control" id="fullName" name="fullName">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password:</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="mb-3">
                        <label for="type" class="form-label">Type:</label>
                        <input type="number" class="form-control" id="type" name="type">
                    </div>
                    <div class="mb-3">
                        <a href="UserController?action=view" class="btn btn-secondary">Back</a>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>

    <!-- Include Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>


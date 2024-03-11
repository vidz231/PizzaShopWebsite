<%-- 
    Document   : updateBillingAddress
    Created on : Mar 11, 2024, 1:28:19 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-6">
                    <h1 class="text-center my-4">update your billing information</h1>
                    <% User user = (User) session.getAttribute("user");%>
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
                            <input type="number" id="phone" name="phone" value="<c:out value="${sessionScope.customer.phone}"/>"  class="form-control" required="true">
                        </div>
                        <div class="form-group">
                            <label for="shipAddress">Shipping Address:</label>
                            <input type="text" id="shipAddress" name="shipAddress" value ="${sessionScope.customer.address}"class="form-control" required="true">
                        </div>
                        <div class="form-group">
                            <a href="CartController?action=view" class="btn btn-secondary">Back</a>
                            <input type="submit" name="action" value="update" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>

        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>

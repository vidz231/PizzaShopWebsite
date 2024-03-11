<%-- 
    Document   : login.jsp
    Created on : Mar 7, 2024, 1:49:27 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">PizzaShop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <c:if test="${sessionScope.user != null}">

                    <div class="dropdown">
                        <button class="btn btn-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Category
                        </button>
                        <ul class="dropdown-menu">
                            <c:forEach var="category" items="${requestScope.categoryList}">
                                <li>
                                    <a class="dropdown-item" 
                                       href="ProductController?action=view&filterByCategory=${category.key}">
                                        ${category.value.categoryName}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">

                            <li class="nav-item">
                                <a class="nav-link" href="ProductController?action=view">Menu</a>
                            </li>
                            <c:if test="${sessionScope.user != null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="UserController?action=update&userId=<c:out value="${sessionScope.user.accountID}"/>">Profiles</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="OrderController?action=view&customerId=${sessionScope.customer.id}">Orders</a>
                                </li>
                            </c:if>

                            <li class="nav-item">
                                <c:if test="${sessionScope.user!=null}">
                                    <a class="nav-link btn btn-danger text-white" href="UserController?action=signout">Sign out</a>
                                </c:if>
                                <c:if test="${sessionScope.user==null}">
                                    <a class="nav-link btn btn-primary text-white" href="UserController?action=signin">Log in</a>
                                </c:if>
                            </li>
                        </ul>
                    </div>
                </div>
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

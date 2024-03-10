<%-- 
    Document   : landingPage
    Created on : Mar 9, 2024, 6:12:38 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>

        <jsp:include page="Header.jsp"/>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">PizzaShop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
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
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">

                        <li class="nav-item">
                            <a class="nav-link" href="ProductController?action=view">Menu</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="UserController?action=update&userId=<c:out value="${sessionScope.user.accountID}"/>">Profiles</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="UserController?action=signout">Signout</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="#">Orders</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <c:forEach var="product" items="${requestScope.productList}">
                    <div class="col-md-3">
                        <div class="card">
                            <img src="${product.productImage}" class="card-img-top" alt="${product.productName}">
                            <div class="card-body">
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="card-text">$ ${product.unitPrice}</p>
                                <form action="CartController" method="GET">
                                    <input type="hidden" id="itemId" name="itemId" value="${product.productID}">
                                    <input type="hidden" id="itemName" name="itemName" value="${product.productName}">
                                    <input type="hidden" id="quantity" name="quantity" value="1">
                                    <input type="hidden" id="unitPrice" name="unitPrice" value="${product.unitPrice}">
                                    <input type="hidden" id="filterByCategory" name="filterByCategory" value="${requestScope.filterByCategory}">
                                    <input type="hidden" id="action" name="action" value="add">
                                    <input class="btn btn-primary   " type="submit" value="Add to Cart">
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>

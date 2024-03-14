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
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.0-beta1/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.0-beta1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">PizzaShop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">

                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <div class="dropdown nav-link ">
                                <button class="dropdown-toggle border-0 bg-light" type="button" data-bs-toggle="dropdown" aria-expanded="false">
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
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ProductController?action=view">View all Menu</a>
                        </li>
                        <c:if test="${sessionScope.user != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="UserController?action=update&userId=<c:out value="${sessionScope.user.accountID}"/>">Profiles</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="OrderController?action=view&customerId=${sessionScope.customer.id != null?sessionScope.customer.id:0}">Orders</a>
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
                <div class="col-4 justify-content-center d-flex justify-content-center mt-3">
                    <form action="ProductController" method="GET">
                        <input type="hidden" name="action" value="view"/>
                        <input type="search" id ="searchParam"value="<c:out value="${requestScope.searchParam }"/>"  name="searchParam"/>
                        <input type="submit" class="btn btn-secondary"value="🔎"/>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row mt-3 mb-3">
                <div class="col-2 justify-content-center d-flex justify-content-center mt-3">
                    <a class="btn btn-primary" href="CartController?action=view">view cart</a>
                </div>

                <div class="col-4 justify-content-center d-flex justify-content-center mt-3">
                    <form action="ProductController" method="GET">
                        <input type="hidden" name="action" value="view"/>
                        <label for="minPrice"><b>Min Price:</b></label>
                        <input type="number" min="0" id="minPrice" value="<c:out value="${requestScope.searchMinPrice}"/>"  name="searchMinPrice"/>
                        <input type="submit" class="btn btn-secondary"value="search"/>
                    </form>
                </div>
            </div>
            <div class="row">
                <c:forEach var="product" items="${requestScope.productList}">
                    <div class="col-md-3">
                        <div class="card mb-3">
                            <img src="${product.productImage}" class="card-img-top" alt="${product.productName}">
                            <div class="card-body">
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="card-text">${requestScope.categoryList.get(product.categoryID).description}</p>
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

        <script>
            // TODO: Add your JavaScript code here
        </script>
    </body>
</html>

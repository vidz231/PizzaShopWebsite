<%-- 
    Document   : viewCart
    Created on : Mar 10, 2024, 7:04:27 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <div class="container">
            <div class="row text-center">
                <div class="col">
                    <h1 class="text-center">
                        Cart
                    </h1>
                </div>
            </div>

            <div class="row d-flex justify-content-center">
                <div class="col-6">
                    <c:choose>
                        <c:when test="${requestScope.cartList != null}">

                            <c:forEach var="item" items="${requestScope.cartList}">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-8">
                                                    <h5 class="card-title" id="itemName">${item.itemName}</h5>
                                                    <p class="card-text">Unit Price: <span id="unitPrice">${item.unitPrice}</span></p>
                                                </div>
                                                <div class="col-4">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-outline-secondary" type="button">-</button>
                                                        </div>
                                                        <input type="text" class="form-control" id="quantity" value="${item.quantity}" readonly>
                                                        <div class="input-group-append">
                                                            <button class="btn btn-outline-secondary" type="button">+</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h1>cart is empty</h1>
                        </c:otherwise>
                    </c:choose>

                </div>
                <div class="col-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Order Summary</h5>
                            <p class="card-text">
                                <strong>Total Items:</strong> <span id="totalItems">${requestScope.itemCount}</span>
                            </p>
                            <p class="card-text">
                                <strong>Subtotal:</strong> <span id="subtotal">${requestScope.subTotal}</span>
                            </p>
                            <div>
                                <a href="ProductController?action=view" class="btn btn-outline-secondary">Back</a>
                                <a href="CartController?action=save" class="btn btn-secondary">Save Cart</a>
                                <a href="OrderController?action=create" class="btn btn-primary">Place Order</a>
                            </div>
                        </div>
                    </div>
                </div>  
            </div>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>

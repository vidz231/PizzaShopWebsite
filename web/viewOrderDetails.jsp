<%-- 
    Document   : viewOrderDetails
    Created on : Mar 11, 2024, 3:28:54 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>
    <!-- Bootstrap CSS already included -->
</head>
<body>
    <jsp:include page="Header.jsp"/>

    <div class="container mt-5">
        <h1 class="text-center mb-4">Order Details</h1>
        <div class="row">
            <div class="col-lg-6">
                <table class="table table-hover">
                    <thead class="thead-light">
                        <tr>
                            <th>Product Image</th>
                            <th>Product Name</th>
                            <th>Unit Price</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.orderDetails}" var="order">
                            <c:set value="${requestScope.productMap.get(order.productID)}" var="product"/>
                            <tr>
                                <td><img src="${product.productImage}" alt="${product.productName}" width="50" height="50"/></td>
                                <td>${product.productName}</td>
                                <td>${order.unitPrice}</td>
                                <td>${order.quantity}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-6">
                <div class="card border-0 shadow">
                    <div class="card-body">
                        <h5 class="card-title">Order Summary</h5>
                        <p class="card-text">
                            <strong>Order Date: </strong>${requestScope.orderObject.orderDate} <br>
                            <strong>Expected Arrival Date: </strong>${requestScope.orderObject.requireDate} <br>
                            <strong>Arrival Date:</strong> ${requestScope.orderObject.shippedDate} <br>
                            <strong>Total Items:</strong>${requestScope.totalItem} <br>
                            <strong>Subtotal:</strong>${requestScope.subtotal} $<br>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="mt-4">
            <a href="OrderController?action=view&customerId=${sessionScope.customer.id}" class="btn btn-secondary">Back</a>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</body>
</html>

<%-- 
    Document   : userOrders
    Created on : Mar 10, 2024, 11:54:32 PM
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
        <div class="container">
            <h2 class="text-center">Order History</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Required Date</th>
                        <th>Shipped Date</th>
                        <th>Freight</th>
                        <th>Ship Address</th>
                        <th>Click me!</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${requestScope.orderList}">
                        <tr>
                            <td>${order.orderID}</td>
                            <td>${order.orderDate}</td>
                            <td>${order.requireDate}</td>
                            <td>${order.shippedDate}</td>
                            <td>${order.freight}</td>
                            <td>${order.shipAddress}</td>
                            <td>
                                <a href="OrderController?action=view-details&orderId=${order.orderID}">view details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="ProductController?action=view" class="btn btn-secondary">Back</a>
        </div>
        <jsp:include page="footer.jsp"/>

        <script>
            // TODO: Add your JavaScript code here
        </script>
    </body>
</html>


<%-- 
    Document   : editOrder
    Created on : Mar 9, 2024, 11:37:59 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Order</title>
        <jsp:include page="Header.jsp"/>
    </head>
    <c:set var="order" value="${requestScope.order}"/>
    <div class="container">
        <h1 class="text-center"> Edit Order</h1>
        <div class="row d-flex justify-content-center">
            <div class="col-8 w-full ">
                <form action="OrderController" method="POST" class="mx-auto">
                    <div class="form-group mb-0">
                        <label for="orderID">Order ID:</label><br>
                        <input type="number" id="orderID" name="orderID" value="${order.orderID}" class="form-control"><br>
                    </div>
                    <div class="form-group  mb-0">

                        <label for="customerID">Customer ID:</label><br>
                        <input type="number" id="customerID" name="customerID" value="${order.customerID}" class="form-control"><br>
                    </div>
                    <div class="form-group mb-0" >
                        <label for="orderDate">Order Date:</label><br>
                        <input type="date" id="orderDate" name="orderDate" value="${order.orderDate}" class="form-control"><br>
                    </div>
                    <div class="form-group  mb-0">
                        <label for="requireDate">Require Date:</label><br>
                        <input type="date" id="requireDate" name="requireDate" value="${order.requireDate}" class="form-control"><br>
                    </div>
                    <div class="form-group  mb-0">
                        <label for="shippedDate">Shipped Date:</label><br>
                        <input type="date" id="shippedDate" name="shippedDate" value="${order.shippedDate}" class="form-control"><br>
                    </div>
                    <div class="form-group  mb-0">
                        <label for="freight">Freight:</label><br>
                        <input type="text" id="freight" name="freight" value="${order.freight}" class="form-control"><br>
                    </div>
                    <div class="form-group  mb-0">
                        <label for="shipAddress">Shipping Address:</label><br>
                        <input type="text" id="shipAddress" name="shipAddress" value="${order.shipAddress}" class="form-control"><br>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <a href="OrderController?action=view" class="btn btn-secondary">Back</a>
                            <input type="submit" class="btn btn-primary"name="action" value="update">
                        </div>


                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>

</body>
</html>

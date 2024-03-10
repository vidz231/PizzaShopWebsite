<%-- 
    Document   : createOrder
    Created on : Mar 10, 2024, 4:31:47 PM
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
        <div class="container">
            <div class="row">
                <div class="col">
                    <form action="OrderController?action=create" method="POST">
                            <input type="hidden" class="form-control" id="orderId" name="orderId">
                        <div class="form-group">
                            <label for="customerID">Customer ID:</label>
                            <input type="number" class="form-control" id="customerID" name="customerID" min="0" step="1">
                        </div>
                        <div class="form-group">
                            <label for="orderDate">Order Date:</label>
                            <input type="date" class="form-control" id="orderDate" name="orderDate">
                        </div>
                        <div class="form-group">
                            <label for="requireDate">Require Date:</label>
                            <input type="date" class="form-control" id="requireDate" name="requireDate">
                        </div>
                        <div class="form-group">
                            <label for="shippedDate">Shipped Date:</label>
                            <input type="date" class="form-control" id="shippedDate" name="shippedDate">
                        </div>
                        <div class="form-group">
                            <label for="freight">Freight:</label>
                            <input type="text" class="form-control" id="freight" name="freight">
                        </div>
                        <div class="form-group">
                            <label for="shipAddress">Shipping Address:</label>
                            <input type="text" class="form-control" id="shipAddress" name="shipAddress">
                        </div>
                        <div>
                            <a href="OrderController?action=view" class="btn btn-secondary">Back </a>
                            <button type="submit" class="btn btn-primary">Submit</button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>

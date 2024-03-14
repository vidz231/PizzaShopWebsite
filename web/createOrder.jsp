<%-- 
    Document   : createOrder
    Created on : Mar 10, 2024, 4:31:47 PM
    Author     : TRUNG VI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Product</title>
        <style>
        </style>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="Header.jsp"/>

        <h1 class="text-center">Create Product</h1>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <form action="OrderController?action=create" method="POST">
                        <input type="hidden" class="form-control" id="orderId" name="orderId">
                        <div class="mb-3">
                            <label for="customerID" class="form-label">Customer ID:</label>
                            <input type="number" class="form-control" id="customerID" name="customerID" min="0" step="1" required>
                        </div>
                        <div class="mb-3">
                            <label for="orderDate" class="form-label">Order Date:</label>
                            <input type="date" class="form-control" id="orderDate" name="orderDate" required>
                        </div>
                        <div class="mb-3">
                            <label for="requireDate" class="form-label">Require Date:</label>
                            <input type="date" class="form-control" id="requireDate" name="requireDate" required>
                        </div>
                        <div class="mb-3">
                            <label for="shippedDate" class="form-label">Shipped Date:</label>
                            <input type="date" class="form-control" id="shippedDate" name="shippedDate">
                        </div>
                        <div class="mb-3">
                            <label for="freight" class="form-label">Freight:</label>
                            <input type="text" class="form-control" id="freight" name="freight">
                        </div>
                        <div class="mb-3">
                            <label for="shipAddress" class="form-label">Shipping Address:</label>
                            <input type="text" class="form-control" id="shipAddress" name="shipAddress">
                        </div>
                        <div class="mb-3">
                            <a href="OrderController?action=view" class="btn btn-secondary">Back</a>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>

    </body>
</html>


<%-- 
    Document   : saleStats
    Created on : Mar 12, 2024, 12:09:39 AM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sales Statistics</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Admin Panel</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="dropdown">
                <button class="btn btn-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Create
                </button>
                <ul class="dropdown-menu">
                    <li >
                        <a  class="dropdown-item" href="ProductController?action=create">Create Product</a>
                    </li>
                    <li >
                        <a class="dropdown-item"  href="UserController?action=create">Create User</a>
                    </li>
                    <li >
                        <a class="dropdown-item"  href="CategoryController?action=create">Create Category</a>
                    </li>
                </ul>
            </div>
            <div class="dropdown">
                <button class="btn btn-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    View
                </button>
                <ul class="dropdown-menu">
                    <li >
                        <a class="dropdown-item" href="UserController?action=view">View User</a>
                    </li>
                    <li >
                        <a class="dropdown-item" href="ProductController?action=view">View Product</a>
                    </li>
                    <li >
                        <a class="dropdown-item" href="OrderController?action=view">View Order</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="CategoryController?action=view">View Category</a>
                    </li>
                </ul>
            </div>
            <div class="dropdown">
                <button class="btn btn-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Function
                </button>
                <ul class="dropdown-menu">
                    <li >
                        <a class="dropdown-item" href="OrderController?action=view-sale-stats">Sale Stats</a>
                    </li>
            </div>
            <a class="btn btn-danger" href="UserController?action=signout">Sign Out</a>

        </nav>
        <div class="container mt-5">
            <h1 class="mb-4">Pizza Shop Sales Statistics</h1>
            <form action="OrderController?action=view-sale-stats" method="POST" id="filter-form">
                <!-- Date range picker -->
                <div class="mb-3">
                    <label for="start-date" class="form-label">Start Date</label>
                    <input type="date"   id="start-date" class="form-control" value="<c:out value="${requestScope.startDate}"/>" name="startDate" onchange="submitSearchFilter()">
                </div>
                <div class="mb-3">
                    <label for="end-date" class="form-label">End Date</label>
                    <input type="date" id="end-date" name="endDate" value="${requestScope.endDate}" class="form-control" onchange="submitSearchFilter()">
                </div>
            </form>


            <!-- Sales data table -->
            <table class="table table-striped mt-3">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Pizza Name</th>
                        <th>Quantity Sold</th>
                        <th>Unit Price</th>
                        <th>Total Sales</th>
                    </tr>
                </thead>
                <tbody id="sales-data">
                    <c:forEach var="sale" items="${requestScope.totalSaleList}">
                        <tr>
                            <td>${sale.orderDate}</td>
                            <td>${requestScope.productMap.get(sale.productID).productName}</td>
                            <td>${sale.totalQuantity}</td>
                            <td>${sale.unitPrice}</td>
                            <td>${sale.getToltalSale()}</td>

                        </tr>
                    </c:forEach>
                    <!-- Sales data will be inserted here -->
                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                        function submitSearchFilter() {
                            var x = document.getElementById("filter-form");
                            x.submit();
                        }
        </script>
    </body>
</html>

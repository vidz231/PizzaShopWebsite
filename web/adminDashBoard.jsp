<%-- 
    Document   : adminDashBoard
    Created on : Mar 7, 2024, 2:59:33 PM
    Author     : TRUNG VI
--%>

<%@page import="Model.DTO.Order"%>
<%@page import="Model.DTO.Category"%>
<%@page import="Model.DTO.Supplier"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Model.DTO.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@include file="Header.jsp" %>--%>
<jsp:include page="Header.jsp" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DASHBOARD</title>
    </head>
    <%
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath());
        }
    %>
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
        <div class="container ">
            <div class="row d-flex justify-content-center">
                <c:choose >

                    <c:when test="${requestScope.productList != null}">
                        <%-- Product List--%>
                        <% List<Product> listProduct = (List<Product>) request.getAttribute("productList");
                            HashMap<Integer, Supplier> supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
                            HashMap<Integer, Category> categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
                        %>
                        <div class="col-8 justify-content-center d-flex justify-content-center mt-3">
                            <form action="ProductController" method="GET">
                                <input type="hidden" name="action" value="view"/>
                                <input type="search" value="<c:out value="${requestScope.searchParam }"/>"  name="searchParam"/>
                                <input type="submit" class="btn btn-secondary"value="search"/>
                            </form>

                        </div>

                        <table class="table table-bordered mt-3 table-hover">
                            <thead>
                                <tr>
                                    <th>Product Name</th>
                                    <th>Category </th>
                                    <th>Product Image</th>
                                    <th>Quantity Per Unit</th>
                                    <th>Supplier</th>
                                    <th>Unit Price</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Product product : listProduct) {%>
                                <tr>
                                    <td><%=product.getProductName()%></td>
                                    <td><%=categoryList.get(product.getCategoryID()).getCategoryName()%></td>
                                    <td><%=product.getProductImage()%></td>
                                    <td><%=product.getQuantityPerUnit()%></td>
                                    <td><%=supplierList.get(product.getSupplierID()).getCompanyName()%></td>
                                    <td><%=product.getUnitPrice()%></td>
                                    <td>
                                        <!-- Add your action buttons or links here -->
                                        <a href="ProductController?action=update&productId=<%=product.getProductID()%>" class="btn btn-primary btn-sm">Edit</a>
                                        <!-- Delete link -->
                                        <a href="#" class="btn btn-danger btn-sm delete-link" data-bs-toggle="modal" data-bs-target="#deleteModal<%= product.getProductID()%>">Delete</a>

                                        <!-- Modal -->
                                        <div class="modal fade" id="deleteModal<%= product.getProductID()%>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="staticBackdropLabel">Confirmation</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Are you sure you want to delete this product  <%= product.getProductName()%>?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <form method="POST" action="ProductController?action=delete">
                                                            <input type="hidden" name="productId" value="<%= product.getProductID()%>">
                                                            <button type="submit" class="btn btn-danger">Yes</button>
                                                        </form>
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div> </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>

                    </c:when>
                    <c:when test="${requestScope.userList != null}">
                        <%-- User List--%>
                        <% List<User> userList = (List<User>) request.getAttribute("userList");
                            if (userList != null && !userList.isEmpty()) {
                        %>
                        <table class="table table-bordered mt-3 table-hover">
                            <tr>
                                <th>Account ID</th>
                                <th>Username</th>
                                <th>Full Name </th>
                                <th>Password</th>
                                <th>Type</th>
                                <th>Action</th>
                            </tr>
                            <% for (User user1 : userList) {%>
                            <tr>
                                <td><%=user1.getAccountID()%></td>
                                <td><%=user1.getUsername()%></td>
                                <td><%=user1.getFullName()%></td>
                                <td><%=user1.getPassword()%></td>
                                <td><%=user1.getType()%></td>
                                <td>
                                    <!-- Add your action buttons or links here -->
                                    <a class="btn btn-primary" href="UserController?action=update&userId=<%=user1.getAccountID()%>">Edit</a> |
                                    <!-- Delete link -->
                                    <a href="#" class="btn btn-danger btn-sm delete-link" data-bs-toggle="modal" data-bs-target="#deleteModal<%= user1.getAccountID()%>">Delete</a>

                                    <!-- Modal -->
                                    <div class="modal fade" id="deleteModal<%= user1.getAccountID()%>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="staticBackdropLabel">Confirmation</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure you want to delete this user with id = <%= user1.getAccountID()%>?
                                                </div>
                                                <div class="modal-footer">
                                                    <form method="POST" action="UserController?action=delete">
                                                        <input type="hidden" name="userId" value="<%= user1.getAccountID()%>">
                                                        <button type="submit" class="btn btn-danger">Yes</button>
                                                    </form>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <% }%>
                        </table>
                        <%}%>
                    </c:when>
                    <c:when test="${requestScope.orderList != null}">
                        <%-- Order List --%>
                        <% List<Order> orderList = (List<Order>) request.getAttribute("orderList");
                            if (orderList != null && !orderList.isEmpty()) {
                        %>
                        <table class="table table-bordered mt-3 table-hover">
                            <tr>
                                <th>Order ID</th>
                                <th>Customer ID</th>
                                <th>Order Date</th>
                                <th>Require Date</th>
                                <th>Shipped Date</th>
                                <th>Freight</th>
                                <th>Ship Address</th>
                                <th>Action</th>
                            </tr>
                            <% for (Order order : orderList) {%>
                            <tr>
                                <td><%= order.getOrderID()%></td>
                                <td><%= order.getCustomerID()%></td>
                                <td><%= order.getOrderDate()%></td>
                                <td><%= order.getRequireDate()%></td>
                                <td><%= order.getShippedDate()%></td>
                                <td><%= order.getFreight()%></td>
                                <td><%= order.getShipAddress()%></td>
                                <td>
                                    <!-- Add your action buttons or links here -->
                                    <a class="btn btn-primary" href="OrderController?action=update&orderId=<%= order.getOrderID()%>">Edit</a> |
                                    <!-- Button trigger modal -->
                                    <a href="#" class="btn btn-danger btn-sm delete-btn" data-bs-toggle="modal" data-bs-target="#deleteModal<%= order.getOrderID()%>">
                                        Delete
                                    </a>

                                    <!-- Modal -->
                                    <div class="modal fade" id="deleteModal<%= order.getOrderID()%>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="staticBackdropLabel">Confirmation</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure you want to delete id = <%= order.getOrderID()%>?
                                                </div>
                                                <div class="modal-footer">
                                                    <form method="POST" action="OrderController?action=delete">
                                                        <input type="hidden" name="orderId" value="<%= order.getOrderID()%>">
                                                        <button type="submit" class="btn btn-danger">Yes</button>
                                                    </form>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <% } %>
                        </table>
                        <% } %>

                    </c:when>
                    <c:otherwise>
                        <c:redirect url="/"/>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>



        <% if (request.getAttribute("message") != null) {%>
        <div class="alert alert-success fixed-top text-center mt-3" id="alert-message" style="margin-left: auto; margin-right: auto; left: 0; right: 0; width: 200px;">
            <%=request.getAttribute("message")%>
        </div>
        <%}%>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                setTimeout(function () {
                    var alertMessage = document.getElementById('alert-message');
                    alertMessage.style.opacity = "0";
                    setTimeout(function () {
                        alertMessage.style.display = "none";
                    }, 1000); // waits for the fade out animation to finish
                }, 2000); // fades out after 2 seconds
            });
        </script>

    </body>
    <%--<%@include file="footer.jsp" %>--%>
    <jsp:include page="footer.jsp" />

</html>

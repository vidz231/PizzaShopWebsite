<%-- 
    Document   : adminDashBoard
    Created on : Mar 7, 2024, 2:59:33 PM
    Author     : TRUNG VI
--%>

<%@page import="Model.DTO.Category"%>
<%@page import="Model.DTO.Supplier"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Model.DTO.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% User user = (User) session.getAttribute("user");
        if (user.getType() != 0) {
            response.sendRedirect(request.getContextPath());
        }
    %>
    <body>
        <h1>Hello Admin!</h1>
        <a href="CategoryManagement.jsp">Category manager</a></br>
        <a href="SupplierManagement.jsp">Supplier manager</a></br>
        <a href="ProductController?action=create">Create Product</a></br>
        <a href="UserController?action=view">View User</a></br>
        <a href="ProductController?action=view">View Product</a></br>

        <%-- Product List--%>

        <% List<Product> listProduct = (List<Product>) request.getAttribute("productList");
            HashMap<Integer, Supplier> supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
            HashMap<Integer, Category> categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
            if (listProduct != null && !listProduct.isEmpty()) {

        %>
        <table>
            <tr>
                <th>Product Name</th>
                <th>Category </th>
                <th>Product Image</th>
                <th>Quantity Per Unit</th>
                <th>Supplier</th>
                <th>Unit Price</th>
                <th>Action</th>
            </tr>
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
                    <a href="ProductController?action=update&productId=<%=product.getProductID()%>">Edit</a> |
                    <a href="ProductController?action=delete&productId=<%=product.getProductID()%>">Delete</a>
                </td>
            </tr>
            <% }%>
        </table>
        <%}%>

        <%-- User List--%>
        <% List<User> userList = (List<User>) request.getAttribute("userList");
            if (userList != null && !userList.isEmpty()) {
        %>
        <table>
            <tr>
                <th>Account ID</th>
                <th>Username</th>
                <th>Password    </th>
                <th>Full Name</th>
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
                    <a href="UserController?action=update&userId=<%=user1.getAccountID()%>">Edit</a> |
                    <a href="UserController?action=delete&userId=<%=user1.getAccountID()%>">Delete</a>
                </td>
            </tr>
            <% }%>
        </table>
        <%}%>




        <% if (request.getAttribute("message") != null) {%>
        <p style="color: green"><%=request.getAttribute("message")%></p>
        <%}%>

    </body>
</html>

<%-- 
    Document   : createProduct
    Created on : Mar 8, 2024, 9:48:14 PM
    Author     : TRUNG VI
--%>

<%@page import="java.util.HashMap"%>
<%@page import="Model.DTO.Category"%>
<%@page import="Model.DTO.Supplier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <% HashMap<Integer, Supplier> supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
            HashMap<Integer, Category> categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
        %>

        <form action="ProductController?action=create" method="POST">
            <label>Product Name:</label>
            <input type="text" name="productName" /><br/>
            <label>Supplier:</label>
            <select name="supplierID">
                <% for (Supplier supplier : supplierList.values()) {%>
                <option value="<%=supplier.getSupplierID()%>"><%=supplier.getCompanyName()%></option>
                <% } %>
            </select><br/>
            <label>Category:</label>
            <select name="categoryID">
                <% for (Category category : categoryList.values()) {%>
                <option value="<%=category.getCategoryID()%>"><%=category.getCategoryName()%></option>
                <% }%>
            </select><br/>
            <label>Quantity Per Unit:</label>
            <input type="number" name="quantityPerUnit" /><br/>
            <label>Unit Price:</label>
            <input type="number" step="0.01" name="UnitPrice" /><br/>
            <label>Product Image:</label>
            <input type="text" name="productImage" /><br/>
            <% if (request.getAttribute("message") != null) {%>
            <p style="color: green"><%=request.getAttribute("message")%></p>
            <%}%>
            <div>
                <a href="ProductController?action=view"> back</a>
                <input type="submit" name="action" value="Create"/>
            </div>
        </form>
    </body>
</html>

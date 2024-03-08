<%-- 
    Document   : editProduct
    Created on : Mar 8, 2024, 7:53:29 PM
    Author     : TRUNG VI
--%>

<%@page import="Model.DTO.Product"%>
<%@page import="Model.DTO.Category"%>
<%@page import="Model.DTO.Supplier"%>
<%@page import="java.util.HashMap"%>
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
            Product editted = (Product) request.getAttribute("product");
        %>
        <h1>Edit Product</h1>
        <form action="ProductController?action=update" method="POST">
            <input type="hidden" name="productId" value="<%=editted.getProductID()%>"/><br/>
            <label>Product Name:</label>
            <input type="text" name="productName" value="<%=editted.getProductName()%>"/><br/>
            <label>Supplier:</label>
            <select name="supplierID">
                <% for (Supplier supplier : supplierList.values()) {%>
                <option value="<%=supplier.getSupplierID()%>" <%=supplier.getSupplierID() == editted.getSupplierID() ? "selected" : ""%>><%=supplier.getCompanyName()%></option>
                <% } %>
            </select><br/>
            <label>Category:</label>
            <select name="categoryID">
                <% for (Category category : categoryList.values()) {%>
                <option value="<%=category.getCategoryID()%>" <%=category.getCategoryID() == editted.getCategoryID() ? "selected" : ""%>><%=category.getCategoryName()%></option>
                <% }%>
            </select><br/>
            <label>Quantity Per Unit:</label>
            <input type="number" name="quantityPerUnit" value="<%=editted.getQuantityPerUnit()%>"/><br/>
            <label>Unit Price:</label>
            <input type="number" step="0.01" name="UnitPrice" value="<%=editted.getUnitPrice()%>"/><br/>
            <label>Product Image:</label>
            <input type="text" name="productImage" value="<%=editted.getProductImage()%>"/><br/>
            <% if (request.getAttribute("message") != null) {%>
            <p style="color: red"><%=request.getAttribute("message")%></p>
            <%}%>
            <div>
                <a href="ProductController?action=view"> back</a>
                <input type="submit" name="action" value="Update"/>
            </div>

        </form>
    </body>
</html>

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
        <jsp:include page="Header.jsp"/>

        <% HashMap<Integer, Supplier> supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
            HashMap<Integer, Category> categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
        %>
        <div class="container-fluid ">
            <h1 class="text-center">Create Product</h1>
            <div class="row justify-content-center w-full"> 
                <div class="col-4">

                    <form action="ProductController?action=create" method="POST" >
                        <div class="form-group">
                            <label for="productName">Product Name:</label>
                            <input type="text" name="productName" id="productName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="supplierID">Supplier:</label>
                            <select name="supplierID" id="supplierID" class="form-control">
                                <% for (Supplier supplier : supplierList.values()) {%>
                                <option value="<%=supplier.getSupplierID()%>"><%=supplier.getCompanyName()%></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="categoryID">Category:</label>
                            <select name="categoryID" id="categoryID" class="form-control">
                                <% for (Category category : categoryList.values()) {%>
                                <option value="<%=category.getCategoryID()%>"><%=category.getCategoryName()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="quantityPerUnit">Quantity Per Unit:</label>
                            <input type="number" name="quantityPerUnit" id="quantityPerUnit" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="UnitPrice">Unit Price:</label>
                            <input type="number" step="0.01" name="UnitPrice" id="UnitPrice" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="productImage">Product Image:</label>
                            <input type="text" name="productImage" id="productImage" class="form-control"/>
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
                        <div class="form-group">
                            <a href="ProductController?action=view" class="btn btn-secondary">Back</a>
                            <input type="submit" name="action" value="Create" class="btn btn-primary"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

    </body>
</html>

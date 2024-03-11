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
        <title>Edit Product</title>
        <jsp:include page="Header.jsp" />
    </head>
    <body>
        <% HashMap<Integer, Supplier> supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
            HashMap<Integer, Category> categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
            Product editted = (Product) request.getAttribute("product");
        %>
        <div class="container">
            <h1 class="text-center my-4">Edit Product</h1>
            <div class="row">
                <div class="col-6">
                    <form action="ProductController?action=update" method="POST" class="mx-auto border rounded p-3" style="max-width: 500px;">
                        <input type="hidden" name="productId" value="<%=editted.getProductID()%>"/>
                        <div class="form-group">
                            <label>Product Name:</label>
                            <input type="text" name="productName" value="<%=editted.getProductName()%>" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Supplier:</label>
                            <select name="supplierID" class="form-control">
                                <% for (Supplier supplier : supplierList.values()) {%>
                                <option value="<%=supplier.getSupplierID()%>" <%=supplier.getSupplierID() == editted.getSupplierID() ? "selected" : ""%>><%=supplier.getCompanyName()%></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Category:</label>
                            <select name="categoryID" class="form-control">
                                <% for (Category category : categoryList.values()) {%>
                                <option value="<%=category.getCategoryID()%>" <%=category.getCategoryID() == editted.getCategoryID() ? "selected" : ""%>><%=category.getCategoryName()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Quantity Per Unit:</label>
                            <input type="number" name="quantityPerUnit" value="<%=editted.getQuantityPerUnit()%>" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Unit Price:</label>
                            <input type="number" step="1" name="UnitPrice" value="<%=editted.getUnitPrice()%>" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>Product Image:</label>
                            <input type="text" name="productImage" value="<%=editted.getProductImage()%>" class="form-control"/>
                        </div>

                        <div class="form-group mt-3">
                            <a href="ProductController?action=view" class="btn btn-secondary">Back</a>
                            <input type="submit" name="action" value="Update" class="btn btn-primary"/>
                        </div>
                    </form>
                </div>
                <div class="col-6">
                    <h3 class="text-center">Live Preview:</h3>
                    <div class="card mb-3 mx-auto " style="width: 300px">
                        <img src="${product.productImage}" class="card-img-top" alt="${product.productName}">
                        <div class="card-body">
                            <h5 class="card-title">${product.productName}</h5>
                            <p class="card-text">${requestScope.categoryList.get(product.categoryID).description}</p>
                            <p class="card-text">$ ${product.unitPrice}</p>
                            <button class="btn btn-primary">Add to Cart</button>
                        </div>
                    </div>
                </div>
            </div>

            <% if (request.getAttribute("message") != null) {%>
            <div class="alert alert-success fixed-top text-center mt-3" id="alert-message" style="margin-left: auto; margin-right: auto; left: 0; right: 0; width: 200px;">
                <%=request.getAttribute("message")%>
            </div>
            <%}%>
        </div>
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
</html>

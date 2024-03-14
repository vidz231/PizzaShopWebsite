<%-- 
    Document   : createProduct
    Created on : Mar 8, 2024, 9:48:14 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="Model.DTO.Category"%>
<%@page import="Model.DTO.Supplier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Product</title>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>

        <% HashMap<Integer, Supplier> supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
            HashMap<Integer, Category> categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
        %>
        <div class="container">
            <h1 class="text-center mt-5">Create Product</h1>
            <div class="row justify-content-center">
                <div class="col-md-6   border rounded p-3">
                    <form action="ProductController?action=create" method="POST" class="mt-4">
                        <div class="mb-3">
                            <label for="productName" class="form-label">Product Name:</label>
                            <input type="text" name="productName" id="productName" value="${requestScope.product.productName}" class="form-control" required>
                            <div class="invalid-feedback">Please provide a product name.</div>
                            <c:if test="${requestScope.isError == true}">
                                <p class="text-danger">${requestScope.productError.productNameError}</p>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label for="supplierID" class="form-label">Supplier:</label>
                            <select name="supplierID" id="supplierID" class="form-select" required>
                                <option value="" disabled selected>Select Supplier</option>
                                <% for (Supplier supplier : supplierList.values()) {%>
                                <option value="<%=supplier.getSupplierID()%>"><%=supplier.getCompanyName()%></option>
                                <% } %>
                            </select>
                            <div class="invalid-feedback">Please select a supplier.</div>
                        </div>
                        <div class="mb-3">
                            <label for="categoryID" class="form-label">Category:</label>
                            <select name="categoryID" id="categoryID" class="form-select" required>
                                <option value="" disabled selected>Select Category</option>
                                <% for (Category category : categoryList.values()) {%>
                                <option value="<%=category.getCategoryID()%>"><%=category.getCategoryName()%></option>
                                <% } %>
                            </select>
                            <div class="invalid-feedback">Please select a category.</div>
                        </div>
                        <div class="mb-3">
                            <label for="quantityPerUnit" class="form-label">Quantity Per Unit:</label>
                            <input type="number" name="quantityPerUnit" value="${requestScope.product.quantityPerUnit}" id="quantityPerUnit" class="form-control" required>
                            <div class="invalid-feedback">Please provide the quantity per unit.</div>
                            <c:if test="${requestScope.isError == true}">
                                <p class="text-danger">${requestScope.productError.quantityPerUnitError}</p>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label for="UnitPrice" class="form-label">Unit Price:</label>
                            <input type="number" step="0.01" value="${requestScope.product.unitPrice}" name="UnitPrice" id="UnitPrice" class="form-control" required>
                            <div class="invalid-feedback">Please provide the unit price.</div>
                            <c:if test="${requestScope.isError == true}">
                                <p class="text-danger">${requestScope.productError.unitPriceError}</p>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label for="productImage" class="form-label">Product Image:</label>
                            <input type="text" name="productImage" id="productImage" value="${requestScope.product.productImage}" class="form-control" required>
                            <div class="invalid-feedback">Please provide a product image URL.</div>
                            <c:if test="${requestScope.isError == true}">
                                <p class="text-danger">${requestScope.productError.productImageError}</p>
                            </c:if>
                        </div>
                        <% if (request.getAttribute("message") != null) {%>
                        <div class="alert alert-success mb-3" role="alert">
                            <%=request.getAttribute("message")%>
                        </div>
                        <% }%>
                        <div class="mb-3">
                            <a href="ProductController?action=view" class="btn btn-secondary">Back</a>
                            <button type="submit" name="action" value="Create" class="btn btn-primary">Create</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            // Client-side form validation
            (function () {
                'use strict';
                var forms = document.querySelectorAll('.needs-validation');
                Array.prototype.slice.call(forms).forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            })();
        </script>
    </body>
</html>

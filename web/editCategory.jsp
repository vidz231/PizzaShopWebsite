<%-- 
    Document   : editCategory
    Created on : Mar 15, 2024, 10:16:49 PM
    Author     : TRUNG VI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="Header.jsp"/>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center my-4 ">Edit Product</h1>
            <div class="row">
                <div class="col-12">
                    <form action="CategoryController" method="POST" class="mx-auto border rounded p-3" style="max-width: 500px;">
                        <input type="hidden" name="categoryId" value="${category.categoryID}"/>
                        <div class="form-group mb-3">
                            <label>Category Name:</label>
                            <input type="text" name="categoryName" value="${category.categoryName}" class="form-control"/>
                        </div>
                        <div class="form-group mb-3">
                            <label>Description:</label>
                            <input type="text" name="categoryDescription" value="${category.description}" class="form-control"/>
                        </div>
                        <div class="form-group mt-3 mb-3">
                            <a href="CategoryController?action=view" class="btn btn-secondary">Back</a>
                            <input type="submit" name="action" value="update" class="btn btn-primary"/>
                        </div>
                    </form>

                    <c:if test="${isError}">
                        <div class="alert alert-danger">
                            <c:out value="${categoryError.categoryNameError}"/>
                            <c:out value="${categoryError.descriptionError}"/>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>

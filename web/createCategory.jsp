<%-- 
    Document   : createCategory
    Created on : Mar 15, 2024, 8:00:41 PM
    Author     : TRUNG VI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Category Page</title>
        <!-- Header -->
        <jsp:include page="Header.jsp"/>

        <!-- Main Content -->
    <h1 class="text-center">Create Category</h1>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">
                <!-- Form -->
                <form action="CategoryController" method="POST">
                    <div class=" mb-3">
                        <input type="hidden" name="action" value="create"/>
                        <label for="categoryName" class="form-label">Category Name:</label>
                        <input type="text" class="form-control" id="categoryName" value="${requestScope.category.categoryName}" name="categoryName" required>
                        <c:if test="${requestScope.isError == true}">
                            <p class="text-danger">${categoryError.categoryNameError}</p>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="categoryDescription" class="form-label">Category Description:</label>
                        <textarea class="form-control" id="categoryDescription" value="${requestScope.category.description}" name="categoryDescription"></textarea>
                        <c:if test="${requestScope.isError == true}">
                            <p class="text-danger">${categoryError.descriptionError}</p>
                        </c:if>
                    </div>
                    <div>
                        <a href="CategoryController?action=view" class=" btn    btn-secondary">Back </a>
                        <input type="submit" value="create" class="btn btn-primary">         
                    </div>

                </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</html>

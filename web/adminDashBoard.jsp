<%-- 
    Document   : adminDashBoard
    Created on : Mar 7, 2024, 2:59:33 PM
    Author     : TRUNG VI
--%>

<%@page import="Model.DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% User user = (User)session.getAttribute("user");
        if(user.getType()!= 0){
            response.sendRedirect(request.getContextPath());
        }
    %>
    <body>
        <h1>Hello Admin!</h1>
        <a href="CategoryManagement.jsp">Category manager</a>
        <a href="SupplierManagement.jsp">Supplier manager</a>

    </body>
</html>

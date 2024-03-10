<%-- 
    Document   : footer
    Created on : Mar 9, 2024, 10:37:19 PM
    Author     : TRUNG VI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <footer>
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
        </footer>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</html>

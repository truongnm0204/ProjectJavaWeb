<%-- 
    Document   : listProduct
    Created on : Oct 4, 2024, 11:00:01 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% //Customer cus=(Customer)request.getAttribute("customer");
        Customer cus=(Customer)session.getAttribute("customer");
            %>
        <p align="right">
            <%if(cus==null){%>
              <a href="CustomerURL?service=loginCustomer">login</a>
            <%}else{%>
                <%= "Welcome "+cus%> 
                <a href="CustomerURL?service=logoutCustomer">logout</a>
            <%}%>
        </p>
       
    </body>
</html>

<%-- 
    Document   : listCustomerDemo
    Created on : Oct 27, 2024, 4:43:17 AM
    Author     : 84968
--%>

<%@page import="java.util.Vector"%>
<%@page import="entity.CustomerCustomerDemo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            Vector<CustomerCustomerDemo> vector = (Vector<CustomerCustomerDemo>) request.getAttribute("dataCus");

        %>
        <form action="CustomerCustomerDemoURL" method="get">
            <p>CustomerID: <input type="text" name="name" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="CustomerCustomerDemoURL?service=insert">insert CusDeMo</a></p>
        <!--<p align="right" ><a href="CartURL?service=showCart"> Show Cart</a></p>-->
        <table border="1">


            <tr>
                <th>CustomerID</th>
                <th>CustomerTypeID</th>               
                <th>update</th>
                <th>delete</th>
                <!--<th>Add to Cart</th>-->
            </tr>

            <%for (CustomerCustomerDemo cus : vector) {%>

            <tr>
                <td><%=cus.getCustomerID()%></td>
                <td><%=cus.getCustomerTypeID()%></td>

                <td><a href="CustomerCustomerDemoURL?service=updateRegion&pid=<%=cus.getCustomerID()%>"> Update</a></td>
                <td><a href="CustomerCustomerDemoURL?service=deleteRegion&pid=<%=cus.getCustomerID()%>"> Delete</a></td>

            </tr>                   

            <%}%>
        </table>
    </body>
</html>

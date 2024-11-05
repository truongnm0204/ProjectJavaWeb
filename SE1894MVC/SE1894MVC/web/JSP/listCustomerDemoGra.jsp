<%-- 
    Document   : listCustomerDemoGra
    Created on : Oct 27, 2024, 4:51:02 AM
    Author     : 84968
--%>

<%@page import="java.util.Vector"%>
<%@page import="entity.CustomerDemographics"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            Vector<CustomerDemographics> vector = (Vector<CustomerDemographics>) request.getAttribute("dataCusDemoGra");

        %>
        <form action="CustomerDemographicsURL" method="get">
            <p>CustomerTypeID: <input type="text" name="name" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="CustomerDemographicsURL?service=insert">insert CusDeMo</a></p>
        <!--<p align="right" ><a href="CartURL?service=showCart"> Show Cart</a></p>-->
        <table border="1">


            <tr>
                <th>CustomerID</th>
                <th>CustomerTypeID</th>               
                <th>update</th>
                <th>delete</th>
                <!--<th>Add to Cart</th>-->
            </tr>

            <%for (CustomerDemographics cus : vector) {%>

            <tr>
                <td><%=cus.getCustomerTypeID()%></td>
                <td><%=cus.getCustomerDesc()%></td>

                <td><a href="CustomerDemographicsURL?service=update&pid=<%=cus.getCustomerTypeID()%>"> Update</a></td>
                <td><a href="CustoCustomerDemographicsURLmerCustomerDemoURL?service=delete&pid=<%=cus.getCustomerTypeID()%>"> Delete</a></td>

            </tr>                   

            <%}%>
        </table>
    </body>
</html>

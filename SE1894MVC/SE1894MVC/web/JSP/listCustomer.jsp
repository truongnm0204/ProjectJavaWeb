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
          <% Customer cus = (Customer)session.getAttribute("customer");%>
        <p align="right">
            <% if (cus == null){%>
            <a href="CustomerURL?service=loginCustomer">Login</a>
            <%}else{%>
                <%= "Welcom " + cus.getContactName()%>
                <a href="CustomerURL?service=logoutCustomer">logout</a>
            <%}%>
        </p>
        <% Vector<Customer> vector=(Vector<Customer>)request.getAttribute("dataCustomer");
        String title=(String)request.getAttribute("titleTable");
        %>
        <form action="CustomerURL" method="get">
            <input type="hidden" name="service" value="listAllCustomer">
            <p>Customer ID<input type="text" name="cid" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="CustomerURL?service=insertCustomer">Insert Customer</a></p>
        <p><a href="CustomerURL?service=loginCustomer">login</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>CustomerID</th>
                <th>CompanyName</th>
                <th>ContactName</th>
                <th>ContactTitle</th>
                <th>Address</th>
                <th>City</th>
                <th>Region</th>
                <th>PostalCode</th>
                <th>Country</th>
                <th>Phone</th>
                <th>Fax</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for (Customer customer:vector){%>
            <tr>
                <td><%=customer.getCustomerID()%></td>
                <td><%=customer.getCompanyName()%></td>
                <td><%=customer.getContactName()%></td>
                <td><%=customer.getContactTitle()%></td>
                <td><%=customer.getAddress()%></td>
                <td><%=customer.getCity()%></td>
                <td><%=customer.getRegion()%></td>
                <td><%=customer.getPostalCode()%></td>
                <td><%=customer.getCountry()%></td>
                <td><%=customer.getPhone()%></td>
                <td><%=customer.getFax()%></td>
                <td><a href="CustomerURL?service=updateCustomer&cid=<%=customer.getCustomerID()%>">Update</a></td>
                <td><a href="CustomerURL?service=deleteCustomer&cid=<%=customer.getCustomerID()%>">Delete</a></td>
            </tr>
            <%}%>
        </table>
        
    </body>
</html>

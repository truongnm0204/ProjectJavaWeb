
<%@page import="java.util.Vector, entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
            Vector<Customer> vector = (Vector<Customer>)request.getAttribute("vector");
            Customer customer = vector.get(0);
    %>
    <body>
        
        <form action="CustomerURL" method="post">
            <input type="hidden" name="service" value="updateCustomer">
            <table>
                <caption>Update Customer</caption>
                <tr>
                    <td><label for="CustomerID">CustomerID</label></td>
                    <td><input type="text" name="CustomerID" id="CustomerID" value="<%=customer.getCustomerID()%>"></td>
                </tr>
                <tr>
                    <td><label for="CompanyName">CompanyName</label></td>
                    <td><input type="text" name="CompanyName" id="CompanyName" value="<%=customer.getCompanyName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ContactName">ContactName</label></td>
                    <td><input type="text" name="ContactName" id="ContactName" value="<%=customer.getContactName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ContactTitle">ContactTitle</label></td>
                    <td><input type="text" name="ContactTitle" id="ContactTitle" value="<%=customer.getContactTitle()%>"></td>
                </tr>
                
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address" value="<%=customer.getAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="City">City</label></td>
                    <td><input type="text" name="City" id="City" value="<%=customer.getCity()%>"></td>
                </tr>
                <tr>
                    <td><label for="Region">Region</label></td>
                    <td><input type="text" name="Region" id="Region" value="<%=customer.getRegion()%>"></td>
                </tr>
                <tr>
                    <td><label for="PostalCode">PostalCode</label></td>
                    <td><input type="text" name="PostalCode" id="PostalCode" value="<%=customer.getPostalCode()%>"></td>
                </tr>
                <tr>
                    <td><label for="Country">Country</label></td>
                    <td><input type="text" name="Country" id="Country" value="<%=customer.getCountry()%>"></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone" value="<%=customer.getPhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="Fax">Fax</label></td>
                    <td><input type="text" name="Fax" id="Fax" value="<%=customer.getFax()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="updateCustomer" id="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>

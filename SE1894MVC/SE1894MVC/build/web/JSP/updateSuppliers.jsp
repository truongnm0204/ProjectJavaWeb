
<%@page import="java.util.Vector, entity.Suppliers" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            Vector<Suppliers> vector=(Vector<Suppliers>)request.getAttribute("vector");
            Suppliers suppliers = vector.get(0);
        %>
        <form action="SuppliersURL" method="post">
            <input type="hidden" name="service" value="updateSuppliers">
            <table>
                <caption>Update Suppliers</caption>
                <tr>
                    <td><label for="SupplierID">SupplierID</label></td>
                    <td><input type="text" name="SupplierID" id="SupplierID" readonly value="<%=suppliers.getSupplierID()%>"></td>
                </tr>
                <tr>
                    <td><label for="CompanyName">CompanyName</label></td>
                    <td><input type="text" name="CompanyName" id="CompanyName" value="<%=suppliers.getCompanyName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ContactName">ContactName</label></td>
                    <td><input type="text" name="ContactName" id="ContactName" value="<%=suppliers.getContactName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ContactTitle">ContactTitle</label></td>
                    <td><input type="text" name="ContactTitle" id="ContactTitle" value="<%=suppliers.getContactTitle()%>"></td>
                </tr>
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address" value="<%=suppliers.getAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="City">City</label></td>
                    <td><input type="text" name="City" id="City" value="<%=suppliers.getCity()%>"></td>
                </tr>
                <tr>
                    <td><label for="Region">Region</label></td>
                    <td><input type="text" name="Region" id="Region" value="<%=suppliers.getRegion()%>"></td>
                </tr>
                <tr>
                    <td><label for="PostalCode">PostalCode</label></td>
                    <td><input type="text" name="PostalCode" id="PostalCode" value="<%=suppliers.getPostalCode()%>"></td>
                </tr>
                <tr>
                    <td><label for="Country">Country</label></td>
                    <td><input type="text" name="Country" id="Country" value="<%=suppliers.getCountry()%>"></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone" value="<%=suppliers.getPhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="Fax">Fax</label></td>
                    <td><input type="text" name="Fax" id="Fax" value="<%=suppliers.getFax()%>"></td>
                </tr>
                <tr>
                    <td><label for="HomePage">HomePage</label></td>
                    <td><input type="text" name="HomePage" id="HomePage" value="<%=suppliers.getHomePage()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="updateSuppliers" id="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>


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
            Vector<Suppliers> vector=(Vector<Suppliers>)request.getAttribute("dataSuppliers");
            String title = (String) request.getAttribute("titleTable");
        %>
        <form action="SuppliersURL" method="get">
            <input type="hidden" name="service" value="listAllSuppliers">
            <p>Company Name <input type="text" name="sname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="SuppliersURL?service=insertSuppliers">Insert Suppliers</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>SupplierID</th>
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
                <th>HomePage</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for(Suppliers suppliers : vector){%>
            <tr>
                <td><%=suppliers.getSupplierID()%></td>
                <!-- comment <td><%=suppliers.getCompanyName()%></td>-->
                <td><a href="ProductURL?service=searchProduct&sid=<%=suppliers.getSupplierID()%>"><%=suppliers.getCompanyName()%></td>
                <td><%=suppliers.getContactName()%></td>
                <td><%=suppliers.getContactTitle()%></td>
                <td><%=suppliers.getAddress()%></td>
                <td><%=suppliers.getCity()%></td>
                <td><%=suppliers.getRegion()%></td>
                <td><%=suppliers.getPostalCode()%></td>
                <td><%=suppliers.getCountry()%></td>
                <td><%=suppliers.getPhone()%></td>
                <td><%=suppliers.getFax()%></td>
                <td><%=suppliers.getHomePage()%></td>
                <td><a href="SuppliersURL?service=updateSuppliers&sid=<%=suppliers.getSupplierID()%>">Update</a></td>
                <td><a href="SuppliersURL?service=deleteSuppliers&sid=<%=suppliers.getSupplierID()%>">Delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>

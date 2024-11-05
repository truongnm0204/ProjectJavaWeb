<%-- 
    Document   : listProduct
    Created on : Oct 4, 2024, 10:59:24 AM
    Author     : 84968
--%>
<%@page  import="java.util.Vector,entity.Product" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Product> vector = (Vector<Product>) request.getAttribute("DataProduct");
            String title = (String) request.getAttribute("titleTable");

        %>
        <form action="ProductURL" method="get">
            <p>Product name  <input type="text" name="pname" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="ProductURL?service=insertProduct">insert Products</a></p>
        <p align="right" ><a href="CartURL?service=showCart"> Show Cart</a></p>
        <p align="left" ><a href="ProductURL?service=logout"> Logout</a></p>
        <table border="1">
            
            <caption> <%=title%>
                <tr>
                    <th>ProductID</th>
                    <th>ProductName</th>
                    <th>SupplierID</th>
                    <th>CategoryID</th>
                    <th>QuantityPerUnit</th>
                    <th>UnitPrice</th>
                    <th>UnitsInStock</th>
                    <th>UnitsOnOrder</th>
                    <th>ReorderLevel</th>
                    <th>Discontinued</th>
                    <th>update</th>
                    <th>delete</th>
                    <th>Add to Cart</th>
                </tr>

                <%for (Product product : vector) {%>

                <tr>
                    <td><%=product.getProductID()%></td>
                    <td><%=product.getProductName()%></td>
                    <td><%=product.getSupplierID()%></td>
                    <td><%=product.getCategoryID()%></td>
                    <td><%=product.getQuantityPerUnit()%></td>
                    <td><%=product.getUnitPrice()%></td>
                    <td><%=product.getUnitsInStock()%></td>
                    <td><%=product.getUnitsOnOrder()%></td>
                    <td><%=product.getReorderLevel()%></td>
                    <td><%=(product.isDiscontinued() == true ? "Continue" : "Discontinue")%></td>
                    <td><a href="ProductURL?service=updateProduct&pid=<%=product.getProductID()%>"> UpdateProduct</a></td>
                    <td><a href="ProductURL?service=deleteProduct&pid=<%=product.getProductID()%>"> DeleteProduct</a></td>
                    <td><a href="CartURL?service=addToCart&pid=<%=product.getProductID()%>"> AddToCart</a></td>
                    
                </tr>                   

                <%}%>
        </table>
        

    </body>
</html>

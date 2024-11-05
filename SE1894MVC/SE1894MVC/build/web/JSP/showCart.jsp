<%-- 
    Document   : listProduct
    Created on : Oct 4, 2024, 10:59:24 AM
    Author     : 84968
--%>
<%@page  import="java.util.Vector,entity.Cart" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Cart> vector = (Vector<Cart>) request.getAttribute("vectorCart");

        %>
        <form action="ProductURL" method="get">
            <p>Product name  <input type="text" name="pname" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="ProductURL">Continue Shopping</a></p>


        <table border="1">


            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>Quantity</th>
                <th>UnitPrice</th>
                <th>Discount</th>
                <th>SubTotal</th>
                <th>Remove</th>

            </tr>

            <%for (Cart product : vector) {%>

            <tr>
                <td><%=product.getProductID()%></td>
                <td><%=product.getProductName()%></td>
                <td><%=product.getQuantity()%></td>              
                <td><%=product.getUnitPrice()%></td>               
                <td><%=product.getDiscount()%></td>
                <td><%=product.getQuantity() * product.getUnitPrice()%></td>
                <td><a href="CartURL?service=removeCart&pid=<%=product.getProductID()%>"> Remove Cart</a></td>

            </tr>                   

            <%}%>
        </table>
            <%
            float total = 0;
            %>
            <%for (Cart cart : vector){
               
               total += cart.getQuantity()*cart.getUnitPrice();
            }%>
        
        <h2>Total Price: <%=total%> </h2>

    </body>
</html>

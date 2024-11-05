
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">
            <nav class="navbar n">
                <form action="OrderDetailsURL?action=search" method="POST">
                    <p>Order ID: <input type="text" name="oid" id="">
                        <input type="submit" value="Search" name="submit">
                        <input type="reset" value="Clear">
                    </p>
                </form>
                <p><a href="OrderDetailsURL?action=insertOrderDetails">insert Order</a></p>
            </nav>
            <table border=1>
                <caption> </caption>
                <tr>
                    <th>OrderID</th>
                    <th>ProductID</th>
                    <th>UnitPrice</th>
                    <th>Quantity</th>
                    <th>Discount</th>  
                    <th></th>  
                    <th></th>  
                    
                </tr>
                <c:forEach items="${listOrderDetails}" var="o">
                <tr>
                    <td>${o.getOrderID()}</td>
                    <td>${o.getProductID()}</td>
                    <td>${o.getUnitPrice()}</td>
                    <td>${o.getQuantity()}</td>
                    <td>${o.getDiscount()}</td>
                    <td>update</td>
                    <td>delete</td>
                </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</html>

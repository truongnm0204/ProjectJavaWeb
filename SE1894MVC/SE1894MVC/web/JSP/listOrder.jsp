
<%@page import="java.util.Vector,entity.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Order> vector=(Vector<Order>)request.getAttribute("dataOrder");
        String title=(String)request.getAttribute("titleTable");
        %>
        <div class="container-fluid">
            <nav class="navbar n">
                <form action="OrderURL" method="get">
                    <input type="hidden" name="service" value="listAllOrder">
                    <p>Order id <input type="text" name="oid" id="">
                        <input type="submit" value="Search" name="submit">
                        <input type="reset" value="Clear">
                    </p>
                </form>
                <p><a href="OrderURL?service=insertOrder">Insert Order</a></p>
            </nav>
            <table border=1>
                <caption><%=title%></caption>
                <tr>
                    <th>OrderID</th>
                    <th>CustomerID</th>
                    <th>EmployeeID</th>
                    <th>OrderDate</th>
                    <th>RequiredDate</th>
                    <th>ShippedDate</th>
                    <th>ShipVia</th>
                    <th>Freight</th>
                    <th>ShipName</th>
                    <th>ShipAddress</th>
                    <th>ShipCity</th>
                    <th>ShipRegion</th>
                    <th>ShipPostalCode</th>
                    <th>ShipCountry</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
                <%for(Order order:vector){%>
                <tr>
                    <td><%=order.getOrderID()%></td>
                    <td><%=order.getCustomerID()%></td>
                    <td><%=order.getEmployeeID()%></td>
                    <td><%=order.getOrderDate()%></td>
                    <td><%=order.getRequiredDate()%></td>
                    <td><%=order.getShippedDate()%></td>
                    <td><%=order.getShipVia()%></td>
                    <td><%=order.getFreight()%></td>
                    <td><%=order.getShipName()%></td>
                    <td><%=order.getShipAddress()%></td>
                    <td><%=order.getShipCity()%></td>
                    <td><%=order.getShipRegion()%></td>
                    <td><%=order.getShipPostalCode()%></td>
                    <td><%=order.getShipCountry()%></td>
                    <td><a href="OrderURL?service=updateOrder&oid=<%=order.getOrderID()%>">update</a></td>
                    <td><a href="OrderURL?service=deleteOrder&oid=<%=order.getOrderID()%>">delete</a></td>
                </tr>
                <%}%>
            </table>
        </div>

    </body>
</html>

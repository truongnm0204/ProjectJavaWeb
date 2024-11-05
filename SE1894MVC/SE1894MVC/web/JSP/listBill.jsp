<%-- 
    Document   : listBill
    Created on : Nov 4, 2024, 5:14:59 AM
    Author     : 84968
--%>

<%@page import="entity.Bill"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Bill> vector = (Vector<Bill>) request.getAttribute("DataBill");
            Bill bill = vector.get(0);

        %>

        <h3>Bill</h3>

        <p>OrderID:<%=bill.getOrderID()%></p>
        <p>Order Date:<%=bill.getOrderDate()%></p>
        <p>Required Date:<%=bill.getRequiredDate()%></p>
        <p>Shipped Date:<%=bill.getShippedDate()%></p>
        <p>Contact Name:<%=bill.getContactName()%></p>
        <p>Address:<%=bill.getAddress()%></p>
        <p>Phone:<%=bill.getPhone()%></p>
        <p>Fax:<%=bill.getFax()%></p>

        <br/>
        <table border="1">


            <tr>
                <td>ProductID</td>
                <td>ProductName</td>
                <td>Unit Price</td>
                <td>Quantity</td>
                <td>Discount</td>
                <td>Subtotal</td>
            </tr>

            <%for (Bill b : vector) {%>
            <tr>
                <td><%=b.getProductID()%></td>
                <td><%=b.getProductName()%></td>
                <td><%=b.getUnitPrice()%></td>
                <td><%=b.getQuantity()%></td>
                <td><%=b.getDiscount()%></td>
                <td><%=b.getUnitPrice() * b.getQuantity() *(1- b.getDiscount())%></td>
            </tr>

            <%  }%>

        </table>
            <br/>
            
            <%
            float total = 0;
            %>
            <%for (Bill bil : vector){
               
               total += bil.getQuantity()*bil.getUnitPrice()*(1- bil.getDiscount());
            }%>
        
        <h2>Total:<%=total%> $</h2>






    </body>
</html>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="OrderDetailsURL" method="POST">
            <table>
                <tr>
                    <td>OrderID</td>
                    <td><input type="text" name="oid" value="orderid" readonly></td>
                </tr>
                <tr>
                    <td>ProductID</td>
                    <td><input type="text" name="pid" value="proId" readonly></td>
                </tr>
                <tr>
                    <td>UnitPrice</td>
                    <td><input type="text" name="unitPrice"></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity"></td>
                </tr>
                <tr>
                    <td>Discount</td>
                    <td><input type="text" name="discount"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="submit"></td>
                </tr>

            </table>
        </form>  
    </body>
</html>

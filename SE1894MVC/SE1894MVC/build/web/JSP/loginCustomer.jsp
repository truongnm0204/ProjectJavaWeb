<%-- 
    Document   : insertProduct
    Created on : Oct 5, 2024, 8:09:43 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <% String error=(String)request.getAttribute("error");%>
        <%=(error!=null?error:"")%>
        <form action="CustomerURL" method="post">
            <input type="hidden" name="service" value="loginCustomer">
            <table>
                <caption>Login</caption>
                <tr>
                    <td><label for="username">username</label></td>
                    <td><input type="text" name="username" id="username" required></td>
                </tr>
                <tr>
                    <td><label for="password">password</label></td>
                    <td><input type="password" name="password" id="password" required></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <body>
        <%String error = (String)request.getAttribute("error");%>
        <%=(error!=null?error:"")%>
        <form action="EmployeeURL" method="post">
            <input type="hidden" name="service" value="loginEmployee">
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
                    <td><input type="submit" value="login" name="submit"></label></td>
                    <td><input type="reset" value="clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>

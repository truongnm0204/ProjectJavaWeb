
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String error = (String)request.getAttribute("error");
        %>
        <%=(error!=null?error:"")%>
        
        <form action="CategrieURL" method="post">
            <input type="hidden" name="service" value="loginCategorie">
            
            <h2>Login Form Categorie</h2>
            <table >

                    <tr>
                        <td>Enter usename:</td>
                        <td> <input type="text" name="username" value="" ></td>
                    </tr>
                    <tr>
                        <td>Enter password: </td>
                        <td> <input type="password" name="password" value="" > </td>
                    </tr>
                    <tr>
                        <td> <input type="submit" value="Login" name="submit"/> </td>
                        <td><input type="reset" value="Clear" /></td>
                    </tr>              
            </table>

            
            
        </form>
        
        
    </body>
</html>

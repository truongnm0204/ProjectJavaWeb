<%-- 
    Document   : insertCategorie
    Created on : Oct 5, 2024, 11:49:47 AM
    Author     : 84968
--%>
<%@page import="java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="CategrieURL" method="post">
            <input type="hidden" name="service" value="insertCategori">
            <table>
                <caption>Insert Categori</caption>
                <tr>
                    <td><label for="CategoryID">CategoryID</label></td>
                    <td><input type="number" name="CategoryID" id="CategoryID" min="1" ></td>
                </tr>
                <tr>
                    <td><label for="CategoryName">CategoryName</label></td>
                    <td><input type="text" name="CategoryName" id="CategoryName"></td>
                </tr>
                <tr>
                    <td><label for="Description">Description</label></td>
                   <td><input type="text" name="Description" id="Description"></td>

                </tr>
                <tr>
                    <td><label for="Picture">Picture</label></td>
                     <td><input type="text" name="Picture" id="Picture"></td>

                </tr>
               
                <tr>
                    <td><input type="submit" value="insertCategori" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>     
    </body>
</html>

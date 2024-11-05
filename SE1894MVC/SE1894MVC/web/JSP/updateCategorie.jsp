
<%@page import="java.sql.ResultSet" %>
<%@page import="entity.Categorie"%>
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
            Vector<Categorie> vector = (Vector<Categorie>) request.getAttribute("vector");
            Categorie categori = vector.get(0);
        
        %>
     <form action="CategrieURL" method="post">
            <input type="hidden" name="service" value="updateCategori">
            <table>
                <caption>Update Categori</caption>
                <tr>
                    <td><label for="CategoryID">CategoryID</label></td>
                    <td><input type="number" name="CategoryID" readonly="" id="CategoryID" value="<%= categori.getCategoryID() %>" ></td>
                </tr>
                <tr>
                    <td><label for="CategoryName">CategoryName</label></td>
                    <td><input type="text" name="CategoryName" id="CategoryName" value="<%= categori.getCategoryName() %>"></td>
                </tr>
                <tr>
                    <td><label for="Description">Description</label></td>
                   <td><input type="text" name="Description" id="Description" value="<%= categori.getDescription() %>"></td>

                </tr>
                <tr>
                    <td><label for="Picture">Picture</label></td>
                     <td><input type="text" name="Picture" id="Picture" value="<%= categori.getPicture()%> "></td>

                </tr>
                
                <tr>
                    <td><label for="Discontinue">Discontinued</label></td>
                    <td>
                        <input type="radio" name="Discontinue" id="Discontinue" value="0" <%=(categori.isDiscontinue() == true ? "Checked" : "")%>>Discontinued
                        <input type="radio" name="Discontinue" id="Discontinue" value="1" <%=(categori.isDiscontinue() == false ? "Checked" : "")%>>Continued
                    </td>
                </tr>
               
                <tr>
                    <td><input type="submit" value="updateCategori" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>     
    </body>
</html>

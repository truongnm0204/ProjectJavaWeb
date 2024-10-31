

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Categories" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Categories> vector=(Vector<Categories>)request.getAttribute("dataCategories");
        String title=(String)request.getAttribute("titleTable");
        %>        
        <form action="CategoriesURL" method="get">
            <input type="hidden" name="service" value="listAllCategories">
            <p>Category ID <input type="text" name="cID" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="CategoriesURL?service=insertCategories">insert category</a></p>

        <table border="1">
            <caption><%=title%></caption>
            <tr>
                <th>CategoryID</th>
                <th>CategoryName</th>
                <th>Description</th>
                <th>Picture</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <% for(Categories categories:vector){%>
            <tr>
                <td><%=categories.getCategoryID()%></td>
                <td><a href="ProductURL?service=searchProduct&cid=<%=categories.getCategoryID()%>"><%=categories.getCategoryName()%></td>
                <td><%=categories.getDescription()%></td>
                <td><%=categories.getPicture()%></td>
                <td><a href="CategoriesURL?service=updateCategories&cID=<%=categories.getCategoryID()%>">Update</a></td>
                <td><a href="CategoriesURL?service=deleteCategories&cID=<%=categories.getCategoryID()%>">Delete</a></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>

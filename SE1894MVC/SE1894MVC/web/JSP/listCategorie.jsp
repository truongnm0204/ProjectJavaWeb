<%-- 
    Document   : listCategorie
    Created on : Oct 4, 2024, 10:17:00 PM
    Author     : 84968
--%>
<%@page  import="java.util.Vector,entity.Categorie" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
            Vector<Categorie> vector = (Vector<Categorie>) request.getAttribute("DataCategorie");
            String title = (String) request.getAttribute("titleTable");

        %>
        
        <%
        Categorie cate =(Categorie) session.getAttribute("categorie");
        %>
         <form action="CategrieURL" method="get">
            <p>CategrieID  <input type="text" name="pname" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
         
          <p><a href="CategrieURL?service=insertCategorie">insert Category</a></p>
          
          <p align="right">
            <%
                if(cate==null){
            %>
            
                <a href="CategrieURL?service=loginCategorie">Login</a>
            <%}else{%>
            
            <a href="CategrieURL?service=logoutCategorie">Logout</a>
            <%}%>
            
            </p>   
            
         <table border="1">
         
            <tr>
                <th>CategoryID</th>
                <th>CategoryName</th>
                <th>Description</th>
                <th>Picture</th> 
                <th>Discontinued</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            
            <%for(Categorie categorie:vector){%>

            <tr>
                <td><%=categorie.getCategoryID() %></td>
                <td><a href="ProductURL?service=searchProduct&pid=<%=categorie.getCategoryID()%>"><%=categorie.getCategoryName() %></a></td>
                <td><%=categorie.getDescription() %></td>
                <td><%=categorie.getCategoryName() %></td>  
                <td><%=(categorie.isDiscontinue()==true ?"Continue":"Discontinue") %></td>
                
                <td><a href="CategrieURL?service=updateCategori&pid=<%=categorie.getCategoryID()%>"> update</a></td>
               <td><a href="CategrieURL?service=deleteCategrie&pid=<%=categorie.getCategoryID()%>"> Delete</a></td>
            </tr>                   

            <%}%>
         
        
    </body>
</html>

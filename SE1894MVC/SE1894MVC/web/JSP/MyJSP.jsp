<%-- 
    Document   : MyJSP
    Created on : Oct 4, 2024, 10:22:03 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p> Script: thuan code java: code servlet trong HTML
         <% // script
             float x=100.3f;
             out.println("<h2>value x="+x+"</h2>");
         %>   
        <p> expression: hien thi gia tri bieu thuc
        <h2>value of x*4 is <%=x*4%></h2>    
        <% for(int i=10;i<=200;i+=10){%>
            <hr width="<%=i%>">
        <%}%>
        <p> declare: khai bao bien toan cuc global va methods
        <%! int global=100;%>
        <%! int getValue(){
            return global;
        }%>
        <h3>Global value <%=getValue()%></h3>
        
        
    </body>
</html>

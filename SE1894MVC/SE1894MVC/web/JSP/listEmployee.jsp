<%-- 
    Document   : listEmployee
    Created on : Nov 1, 2024, 6:59:56 AM
    Author     : PC
--%>
<%@page import="java.util.Vector,entity.Employee" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Employee employee = (Employee)session.getAttribute("employee");%>
        <p align="right">
            <%if( employee == null){%>
            <a href="EmployeeURL?service=loginEmployee">login</a>
            <%} else {%>
            <%= "Welcome" + employee %>
            <a href="EmployeeURL?service=logoutEmployee">logout</a>
            <%}%>
        </p>
        <%String title=(String)request.getAttribute("titleTable");%>
        <div class="container-fluid">
            <nav class="navbar n">
                <form action="EmployeeURL" method="get">
                    <input type="hidden" name="service" value="listAllEmployee">
                    <p>Employee id <input type="text" name="epid" id="">
                        <input type="submit" value="Search" name="submit">
                        <input type="reset" value="Clear">
                    </p>
                </form>
                <p><a href="EmployeeURL?service=insertEmployee">insert employee</a></p>
            </nav>
            <table border=1>
                <caption><%=title%></caption>
                <tr>
                    <th>EmployeeID</th>
                    <th>LastName</th>
                    <th>FirstName</th>
                    <th>Title</th>
                    <th>TitleOfCourtesy</th>
                    <th>BirthDate</th>
                    <th>HireDate</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Region</th>
                    <th>PostalCode</th>
                    <th>Country</th>
                    <th>HomePhone</th>
                    <th>Extension</th>
                    <th>Photo</th>
                    <th>Notes</th>
                    <th>ReportsTo</th>
                    <th>PhotoPath</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>

                <%Vector<Employee> employees = (Vector<Employee>)request.getAttribute("dataEmployee");%>
                <%for(Employee emp : employees){%>
                <tr>
                    <td><%=emp.getEmployeeID()%></td>
                    <td><%=emp.getLastName()%></td>
                    <td><%=emp.getFirstName()%></td>
                    <td><%=emp.getTitle()%></td>
                    <td><%=emp.getTitleOfCourtesy()%></td>
                    <td><%=emp.getBirthDate()%></td>
                    <td><%=emp.getHireDate()%></td>
                    <td><%=emp.getAddress()%></td>
                    <td><%=emp.getCity()%></td>
                    <td><%=emp.getRegion()%></td>
                    <td><%=emp.getPostalCode()%></td>
                    <td><%=emp.getCountry()%></td>
                    <td><%=emp.getHomePhone()%></td>
                    <td><%=emp.getExtension()%></td>
                    <td>Photo</td>
                    <td><%=emp.getPhoto()%></td>
                    <td><%=emp.getNotes()%></td>
                    <td><%=emp.getReportsTo()%></td>
                    <td><%=emp.getPhotoPath()%></td>
                    <td><a href="EmployeeURL?service=updateEmployee&eid=<%=emp.getEmployeeID()%>">Update</a></td>
                    <td><a href="EmployeeURL?service=deleteEmployee&eid=<%=emp.getEmployeeID()%>">Delete</a></td>
                </tr>
                <%}%>
            </table>
        </div>

    </body>
</html>

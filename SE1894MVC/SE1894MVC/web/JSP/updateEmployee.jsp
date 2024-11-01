<%-- 
    Document   : updateEmployee
    Created on : Nov 1, 2024, 6:58:25 AM
    Author     : PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet, java.util.Vector, entity.Employee"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <%ResultSet rsReportTo = (ResultSet)request.getAttribute("rsReportTo");
         Vector<Employee> vector=(Vector<Employee>)request.getAttribute("vector");
         Employee employee = vector.get(0);
        %>
        <form action="EmployeeURL" method="post">
            <input type="hidden" name="service" value="updateEmployee">
            <table>
                <caption>Update Employee</caption>
                <tr>
                    <td><label for="EmployeeID">EmployeeID </label></td>
                    <td><input type="text" name="EmployeeID" id="EmployeeID" readonly value="<%=employee.getEmployeeID()%>"></td>
                </tr>
                <tr>
                    <td><label for="LastName">LastName</label></td>
                    <td><input type="text" name="LastName" id="LastName" value="<%=employee.getLastName()%>"></td>
                </tr>
                <tr>
                    <td><label for="FirstName">FirstName</label></td>
                    <td><input type="text" name="FirstName" id="FirstName" value="<%=employee.getFirstName()%>"></td>
                </tr>
                <tr>
                    <td><label for="Title">Title</label></td>
                    <td><input type="text" name="Title" id="Title" value="<%=employee.getTitle()%>"></td>
                </tr>
                <tr>
                    <td><label for="TitleOfCourtesy">TitleOfCourtesy</label></td>
                    <td><input type="text" name="TitleOfCourtesy" id="TitleOfCourtesy" value="<%=employee.getTitleOfCourtesy()%>"></td>
                </tr>
                <tr>
                    <td><label for="BirthDate">BirthDate</label></td>
                    <td><input type="text" name="BirthDate" id="BirthDate" value="<%=employee.getBirthDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="HireDate">HireDate</label></td>
                    <td><input type="text" name="HireDate" id="HireDate" value="<%=employee.getHireDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="Address">Address</label></td>
                    <td><input type="text" name="Address" id="Address" value="<%=employee.getAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="City"></label>City</td>
                    <td><input type="text" name="City" id="City" value="<%=employee.getCity()%>"></td>
                </tr>
                <tr>
                    <td><label for="Region">Region</label></td>
                    <td><input type="text" name="Region" id="Region" value="<%=employee.getRegion()%>"></td>
                </tr>
                <tr>
                    <td><label for="PostalCode">PostalCode</label></td>
                    <td><input type="text" name="PostalCode" id="PostalCode" value="<%=employee.getPostalCode()%>"></td>
                </tr>
                <tr>
                    <td><label for="Country">Country</label></td>
                    <td><input type="text" name="Country" id="Country" value="<%=employee.getCountry()%>"></td>
                </tr>
                <tr>
                    <td><label for="HomePhone">HomePhone</label></td>
                    <td><input type="text" name="HomePhone" id="HomePhone" value="<%=employee.getHomePhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="Extension">Extension</label></td>
                    <td><input type="text" name="Extension" id="Extension" value="<%=employee.getExtension()%>"></td>
                </tr>
                <tr>
                    <td><label for="Photo">Photo</label></td>
                    <td><input type="text" name="Photo" id="Photo" value="<%=employee.getPhoto()%>"></td>
                </tr>
                <tr>
                    <td><label for="Notes">Notes</label></td>
                    <td><input type="text" name="Notes" id="Notes" value="<%=employee.getNotes()%>"></td>
                </tr>
                <tr>
                    <td><label for="ReportsTo">ReportsTo</label></td>
                    <td>
                        <select name="ReportsTo">
                            <%while (rsReportTo.next()) {%>
                            <option value="<%=rsReportTo.getInt(1)%>">
                                <%=rsReportTo.getString(2)%>
                                <%=rsReportTo.getString(3)%>
                            </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="PhotoPath">PhotoPath</label></td>
                    <td><input type="text" name="PhotoPath" id="PhotoPath" value="<%=employee.getPhotoPath()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="updateEmployee" id="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>

package model;

import entity.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmployee extends DBConnect {

    public int addEmployee(Employee e) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Employees]
                                ([LastName]
                                ,[FirstName]
                                ,[Title]
                                ,[TitleOfCourtesy]
                                ,[BirthDate]
                                ,[HireDate]
                                ,[Address]
                                ,[City]
                                ,[Region]
                                ,[PostalCode]
                                ,[Country]
                                ,[HomePhone]
                                ,[Extension]
                                ,[Photo]
                                ,[Notes]
                                ,[ReportsTo]
                                ,[PhotoPath])
                          VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, e.getLastName());
            pre.setString(2, e.getFirstName());
            pre.setString(3, e.getTitle());
            pre.setString(4, e.getTitleOfCourtesy());
            pre.setString(5, e.getBirthDate());
            pre.setString(6, e.getHireDate());
            pre.setString(7, e.getAddress());
            pre.setString(8, e.getCity());
            pre.setString(9, e.getRegion());
            pre.setString(10, e.getPostalCode());
            pre.setString(11, e.getCountry());
            pre.setString(12, e.getHomePhone());
            pre.setString(13, e.getExtension());
            pre.setBytes(14, e.getPhoto().getBytes());
            pre.setString(15, e.getNotes());
            pre.setInt(16, e.getReportsTo());
            pre.setString(17, e.getPhotoPath());

            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateEmployee(Employee e) {
        int n = 0;
        String sql = "UPDATE [dbo].[Employees]\n"
                + "   SET [LastName] = ?"
                + "      ,[FirstName] = ?"
                + "      ,[Title] = ?"
                + "      ,[TitleOfCourtesy] = ?"
                + "      ,[BirthDate] = ?"
                + "      ,[HireDate] = ?"
                + "      ,[Address] = ?"
                + "      ,[City] = ?"
                + "      ,[Region] = ?"
                + "      ,[PostalCode] = ?"
                + "      ,[Country] = ?"
                + "      ,[HomePhone] = ?"
                + "      ,[Extension] =?"
                + "      ,[Photo] = ?"
                + "      ,[Notes] = ?"
                + "      ,[ReportsTo] =?"
                + "      ,[PhotoPath] = ?"
                + " WHERE EmployeeID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, e.getLastName());
            pre.setString(2, e.getFirstName());
            pre.setString(3, e.getTitle());
            pre.setString(4, e.getTitleOfCourtesy());
            pre.setString(5, e.getBirthDate());
            pre.setString(6, e.getHireDate());
            pre.setString(7, e.getAddress());
            pre.setString(8, e.getCity());
            pre.setString(9, e.getRegion());
            pre.setString(10, e.getPostalCode());
            pre.setString(11, e.getCountry());
            pre.setString(12, e.getHomePhone());
            pre.setString(13, e.getExtension());
            pre.setBytes(14, e.getPhoto().getBytes());
            pre.setString(15, e.getNotes());
            pre.setInt(16, e.getReportsTo());
            pre.setString(17, e.getPhotoPath());
            pre.setInt(18, e.getEmployeeID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeEmployee(int e) {
        int n = 0;
        String sql = "delete from Employees where EmployeeID =" + e;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Employee> getEmployees(String sql) {
        Vector<Employee> vector = new Vector<Employee>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int EmployeeID = rs.getInt(1);
                String LastName = rs.getString(2);
                String FirstName = rs.getString(3);
                String Title = rs.getString(4);
                String TitleOfCourtesy = rs.getString(5);
                String BirthDate = rs.getString(6);
                String HireDate = rs.getString(7);
                String Address = rs.getString(8);
                String City = rs.getString(9);
                String Region = rs.getString(10);
                String PostalCode = rs.getString(11);
                String Country = rs.getString(12);
                String HomePhone = rs.getString(13);
                String Extension = rs.getString(14);
                String Photo = rs.getString(15);
                String Notes = rs.getString(16);
                int ReportsTo = rs.getInt(17);
                String PhotoPath = rs.getString(18);
                Employee employee = new Employee(EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, ReportsTo, PhotoPath);
                vector.add(employee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public void displayyAll(String sql) {
        ResultSet rs = null;

        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);

            while (rs.next()) {
                String lastName = rs.getString(1);
                String firstName = rs.getString(2);
                String title = rs.getString(3);
                String titleOfCourtesy = rs.getString(4);
                String birthDate = rs.getString(5);
                String hireDate = rs.getString(6);
                String address = rs.getString(7);
                String city = rs.getString(8);
                String region = rs.getString(9);
                String postalCode = rs.getString(10);
                String country = rs.getString(11);
                String homePhone = rs.getString(12);
                String extension = rs.getString(13);
                String photo = rs.getString(14);
                String notes = rs.getString(15);
                String reportsTo = rs.getString(16);
                String reportsPath = rs.getString(17);

                Employee emp = new Employee(1, lastName, firstName, title, titleOfCourtesy, birthDate, hireDate, address, city, region, postalCode, country, homePhone, extension, photo, notes, 0, photo);
                System.out.println(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Employee loginEmployee(String userName, String password) {
        Employee emp = null;
        String sql = "select * from Employees where FirstName = ? and Title = ? ";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                emp = new Employee(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14),
                        rs.getString(15), rs.getString(16),
                        rs.getInt(17), rs.getString(18));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emp;
    }

    public static void main(String[] args) {
        int EmployeeID = 123;
        String LastName = "Nguyen";
        String FirstName = "An";
        String Title = "Manager";
        String TitleOfCourtesy = "Mr.";
        String BirthDate = "1990-01-01";
        String HireDate = "2020-06-15";
        String Address = "123 Main Street";
        String City = "Hanoi";
        String Region = "North";
        String PostalCode = "100000";
        String Country = "Vietnam";
        String HomePhone = "0123456789";
        String Extension = "101";
        String Photo = "photo.jpg";
        String Notes = "Excellent employee";
        int ReportsTo = 1;
        String PhotoPath = "/images/photo.jpg";

        Employee e = new Employee(EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes, ReportsTo, PhotoPath);

        System.out.println(new DAOEmployee().addEmployee(e));
    }
}

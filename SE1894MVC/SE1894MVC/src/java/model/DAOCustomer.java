/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOCustomer extends DBConnect {

    public Customer login(String userName, String password) {
        Customer customer = null;
        String sql = "select * from Customers where CustomerID=? and CompanyName=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public int insertCustomer(Customer customer) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "           ([CustomerID]\n"
                + "           ,[CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax])\n"
                + "     VALUES\n"
                + "('" + customer.getCustomerID() + "',"
                + "'" + customer.getCompanyName() + "',"
                + "'" + customer.getContactName() + "',"
                + "'" + customer.getContactTitle() + "',"
                + "'" + customer.getAddress() + "',"
                + "'" + customer.getCity() + "',"
                + "'" + customer.getRegion() + "',"
                + "'" + customer.getPostalCode() + "',"
                + "'" + customer.getCountry() + "',"
                + "'" + customer.getPhone() + "',"
                + "'" + customer.getFax() + "'" + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addCustomer(Customer customer) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "           ([CustomerID]\n"
                + "           ,[CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?)\n";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCustomerID());
            pre.setString(2, customer.getCompanyName());
            pre.setString(3, customer.getContactName());
            pre.setString(4, customer.getContactTitle());
            pre.setString(5, customer.getAddress());
            pre.setString(6, customer.getCity());
            pre.setString(7, customer.getRegion());
            pre.setString(8, customer.getPostalCode());
            pre.setString(9, customer.getCountry());
            pre.setString(10, customer.getPhone());
            pre.setString(11, customer.getFax());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCustomer(Customer customer) {
        int n = 0;
        String sql = "UPDATE [dbo].[Customers]\n"
                + "  SET [CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Fax] = ?\n"
                + " WHERE CustomerID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, customer.getCompanyName());
            pre.setString(2, customer.getContactName());
            pre.setString(3, customer.getContactTitle());
            pre.setString(4, customer.getAddress());
            pre.setString(5, customer.getCity());
            pre.setString(6, customer.getRegion());
            pre.setString(7, customer.getPostalCode());
            pre.setString(8, customer.getCountry());
            pre.setString(9, customer.getPhone());
            pre.setString(10, customer.getFax());
            pre.setString(11, customer.getCustomerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeCustomer(String cid) {
        int n = 0;
        String sql = "delete from Customers where CustomerID = '" + cid + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Customer> getCustomers(String sql) {
        Vector<Customer> vector = new Vector<Customer>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String CustomerID = rs.getString(1);
                String CompanyName = rs.getString(2);
                String ContactName = rs.getString(3);
                String ContactTitle = rs.getString(4);
                String Address = rs.getString(5);
                String City = rs.getString(6);
                String Region = rs.getString(7);
                String PostalCode = rs.getString(8);
                String Country = rs.getString(9);
                String Phone = rs.getString(10);
                String Fax = rs.getString(11);
                Customer customer = new Customer(CustomerID, CompanyName, ContactName,
                        ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                vector.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

//    public void displayAll(String sql) {
//        ResultSet rs = null;
//        try {
//            Statement state = conn.createStatement();
//            rs = state.executeQuery(sql);
//            while (rs.next()) {
//                String CustomerID = rs.getString(1);
//                String CompanyName = rs.getString(2);
//                String ContactName = rs.getString(3);
//                String ContactTitle = rs.getString(4);
//                String Address = rs.getString(5);
//                String City = rs.getString(6);
//                String Region = rs.getString(7);
//                String PostalCode = rs.getString(8);
//                String Country = rs.getString(9);
//                String Phone = rs.getString(10);
//                String Fax = rs.getString(11);
//                Customer customer = new Customer(CustomerID, CompanyName, ContactName,
//                        ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
//                System.out.println(customer);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

 

}

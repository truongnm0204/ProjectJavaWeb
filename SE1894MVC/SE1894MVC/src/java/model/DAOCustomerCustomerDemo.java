/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author 84968
 */
public class DAOCustomerCustomerDemo extends DBConnect {

    public int insertCustomerCustomerDemo(CustomerCustomerDemo cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES\n"
                + "           ('" + cus.getCustomerID() + "'\n"
                + "           ,'" + cus.getCustomerTypeID() + "')";

        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addCustomerCustomerDemo(CustomerCustomerDemo cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES(?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCustomerTypeID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCustomerCustomerDemo(CustomerCustomerDemo cus) {
        int n = 0;
        String sql = "UPDATE [dbo].[CustomerCustomerDemo]\n"
                + "   SET [CustomerID] = ?\n"
                + "      ,[CustomerTypeID] = ?\n"
                + " WHERE CustomerID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCustomerTypeID());
            pre.setString(3, cus.getCustomerID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    
    public int removeCustomerCustomerDemo(String cusId){
        int n =0;
        String sql = "delete from CustomerCustomerDemo where CustomerID =" + cusId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    
    public Vector<CustomerCustomerDemo> getCustomer(String sql){
        Vector<CustomerCustomerDemo> vector = new Vector<CustomerCustomerDemo>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
                String CustomerID = rs.getString(1);
                String CustomerTypeID = rs.getString(2);
                CustomerCustomerDemo cus = new CustomerCustomerDemo(CustomerID, CustomerTypeID);
                vector.add(cus);
               
            }
               
                    
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
       
    }
    
    public void displayAll(String sql){
        ResultSet  rs = null;
        try {
            Statement state = conn.createStatement();
            rs=state.executeQuery(sql);
             while(rs.next()){
                String CustomerID = rs.getString(1);
                String CustomerTypeID = rs.getString(2);
                CustomerCustomerDemo cus = new CustomerCustomerDemo(CustomerID, CustomerTypeID);
                 System.out.println(cus);              
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

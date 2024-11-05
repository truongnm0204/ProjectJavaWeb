/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import entity.CustomerDemographics;

/**
 *
 * @author 84968
 */
public class DAOCustomerDemographics extends DBConnect {

    public int insertCustomerDemographics(CustomerDemographics cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerDemographics]\n"
                + "           ([CustomerTypeID]\n"
                + "           ,[CustomerDesc])\n"
                + "     VALUES\n"
                + "           ('" + cus.getCustomerTypeID() + "'\n"
                + "           ,'" + cus.getCustomerDesc() + "')";

        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addCustomerDemographics(CustomerDemographics cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerDemographics]\n"
                + "           ([CustomerTypeID]\n"
                + "           ,[CustomerDesc])\n"
                + "     VALUES(?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerTypeID());
            pre.setString(2, cus.getCustomerDesc());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;

    }

    public int updateCustomerDemographics(CustomerDemographics cus) {
        int n = 0;
        String sql = "UPDATE [dbo].[CustomerDemographics]\n"
                + "   SET [CustomerTypeID] = ?\n"
                + "      ,[CustomerDesc] = ?\n"
                + " WHERE CustomerTypeID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerTypeID());
            pre.setString(2, cus.getCustomerDesc());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    
    public int removeCustomerDemographics(String id){
        int n = 0;
        
        String sql = "delete from CustomerDemographics where CustomerTypeID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n ;
    }
    
    public Vector<CustomerDemographics> getCus(String sql){
        Vector<CustomerDemographics> vector = new Vector<CustomerDemographics>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
                String CustomerTypeID = rs.getString(1);
                String CustomerDesc = rs.getString(2);
                CustomerDemographics cus = new CustomerDemographics(CustomerTypeID, CustomerDesc);
                vector.add(cus);
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vector;
    }
    
    public void displayAll(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs= state.executeQuery(sql);
             while(rs.next()){
                String CustomerTypeID = rs.getString(1);
                String CustomerDesc = rs.getString(2);
                CustomerDemographics cus = new CustomerDemographics(CustomerTypeID, CustomerDesc);
                 System.out.println(cus);
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemographics.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public static void main(String[] args) {
        
    }
    

}

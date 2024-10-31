/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOCustomer extends DBConnect{
    public Customer login(String userName,String password){
        Customer customer=null;
        String sql="select * from Customers where CustomerID=? and CompanyName=?";
        try {
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, password);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                customer=new Customer(rs.getString(1), rs.getString(2), 
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
    
}

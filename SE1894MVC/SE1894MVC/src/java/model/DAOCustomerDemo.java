
package model;

import entity.CustomerCustomerDemo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOCustomerDemo extends DBConnect {

    public int insertCustomerDemo(CustomerCustomerDemo ccd) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES\n"
                + "('" + ccd.getCustomerID() + "',"
                + "'" + ccd.getCustomerTypeID() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addCustomerDemo(CustomerCustomerDemo ccd) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n"
                + "           ([CustomerID]\n"
                + "           ,[CustomerTypeID])\n"
                + "     VALUES(?,?)\n";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ccd.getCustomerID());
            pre.setString(2, ccd.getCustomerTypeID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCustomerDemo(CustomerCustomerDemo ccd) {
        int n = 0;
        String sql = "UPDATE [dbo].[CustomerCustomerDemo]\n"
                + " [CustomerID] = ?,\n"
                + "[CustomerTypeID] = ?,\n"
                + " WHERE CustomerID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ccd.getCustomerID());
            pre.setString(2, ccd.getCustomerTypeID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeCustomerDemo(int cid) {
        int n = 0;
        String sql = "delete from CustomerCustomerDemo where CustomerID =" + cid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<CustomerCustomerDemo> getCustomerDemos(String sql) {
        Vector<CustomerCustomerDemo> vector = new Vector<CustomerCustomerDemo>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String CustomerID = rs.getString(1);
                String CustomerTypeID = rs.getString(2);
                CustomerCustomerDemo ccd = new CustomerCustomerDemo(CustomerID, CustomerTypeID);
                vector.add(ccd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                String CustomerID = rs.getString(1);
                String CustomerTypeID = rs.getString(2);
                CustomerCustomerDemo ccd = new CustomerCustomerDemo(CustomerID, CustomerTypeID);
                System.out.println(ccd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

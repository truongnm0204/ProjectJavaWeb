
package model;

import entity.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOOrderDetails extends DBConnect {

    public int insertOrderDetails(OrderDetails od) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order Details]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Quantity]\n"
                + "           ,[Discount])\n"
                + "     VALUES(" + od.getOrderID() + "," + od.getProductID() + "," + od.getUnitPrice() + ","
                + od.getQuantity() + "," + od.getDiscount() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addOrderDetails(OrderDetails od) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order Details]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Quantity]\n"
                + "           ,[Discount])\n"
                + "     VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());

            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateOrderDetails(OrderDetails od) {
        int n = 0;
        String sql = "UPDATE [dbo].[Order Details]\n"
                + "   SET [OrderID] = ?\n"
                + "      ,[ProductID] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[Discount] = ?\n"
                + " WHERE OrderID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeOrderDetails(int od) {
        int n = 0;
        String sql = "delete from Order Details where OrderID = " + od;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayyAll(String sql) {
        ResultSet rs = null;

        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);

            while (rs.next()) {
                int orderID = rs.getInt(1);
                int productID = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                int discount = rs.getInt(5);
                OrderDetails orderDetails = new OrderDetails(orderID, productID, unitPrice, quantity, discount);
                System.out.println(orderDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

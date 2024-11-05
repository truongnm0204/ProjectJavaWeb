package model;

import entity.OrderDetails;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOOrderDetails extends DBConnect {
    protected PreparedStatement statement;
    
    public List<OrderDetails> findAll() {
        List<OrderDetails> listFound = new ArrayList<>();
        try {
            //chuan bi cau lenh Sql
            String sql = "SELECT * FROM [dbo].[Order Details]";
            // tao ra doi tuong sql

            statement = conn.prepareStatement(sql);

            //set parameter
            //thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
               int OrderID = resultSet.getInt("OrderID");
               int ProductID = resultSet.getInt("ProductID");
               double UnitPrice = resultSet.getDouble("UnitPrice");
               int Quantity = resultSet.getInt("Quantity");
               double Discount = resultSet.getDouble("Discount");
               OrderDetails od = new OrderDetails();
               od.setOrderID(OrderID);
               od.setProductID(ProductID);
               od.setUnitPrice(UnitPrice);
               od.setQuantity(Quantity);
               od.setDiscount(Discount);
                listFound.add(od);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());;
        }
        return listFound;
    }

    public List<OrderDetails> searchByOrderID(String search) {
        List<OrderDetails> listFound = new ArrayList<>();
        try {
            //chuan bi cau lenh Sql
            String sql = "SELECT * FROM [dbo].[Order Details] where OrderID = ?";
            // tao ra doi tuong sql

            statement = conn.prepareStatement(sql);

            //set parameter
            statement.setObject(1, search);
            //thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
               int OrderID = resultSet.getInt("OrderID");
               int ProductID = resultSet.getInt("ProductID");
               double UnitPrice = resultSet.getDouble("UnitPrice");
               int Quantity = resultSet.getInt("Quantity");
               double Discount = resultSet.getDouble("Discount");
               OrderDetails od = new OrderDetails();
               od.setOrderID(OrderID);
               od.setProductID(ProductID);
               od.setUnitPrice(UnitPrice);
               od.setQuantity(Quantity);
               od.setDiscount(Discount);
               listFound.add(od);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getStackTrace());;
        }
        return listFound;
    }

    public void insert(OrderDetails od) {
            //ket noi voi DB
        //tao cau lenh SQL
        String sql = "INSERT INTO [dbo].[Order Details]\n" +
"           ([OrderID]\n" +
"           ,[ProductID]\n" +
"           ,[UnitPrice]\n" +
"           ,[Quantity]\n" +
"           ,[Discount])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)";
        try {
            //tao doi tuong prepared statement ( them generated key vao tham so thu 2)
            statement = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            //set parameter
            statement.setObject(1, od.getOrderID());
            statement.setObject(2, od.getProductID());
            statement.setObject(3, od.getUnitPrice());
            statement.setObject(4, od.getQuantity());
            statement.setObject(5, od.getDiscount());
            //thuc thi cau lenh
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

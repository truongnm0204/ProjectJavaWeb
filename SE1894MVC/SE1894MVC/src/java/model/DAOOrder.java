
package model;

import entity.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOOrder extends DBConnect {

    public int insertOrder(Order order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([CustomerID]\n"
                + "           ,[EmployeeID]\n"
                + "           ,[OrderDate]\n"
                + "           ,[RequiredDate]\n"
                + "           ,[ShippedDate]\n"
                + "           ,[ShipVia]\n"
                + "           ,[Freight]\n"
                + "           ,[ShipName]\n"
                + "           ,[ShipAddress]\n"
                + "           ,[ShipCity]\n"
                + "           ,[ShipRegion]\n"
                + "           ,[ShipPostalCode]\n"
                + "           ,[ShipCountry])\n"
                + "     VALUES\n"
                + "           ('" + order.getCustomerID() + "'\n"
                + "           ," + order.getEmployeeID() + "\n"
                + "           ,'" + order.getOrderDate() + "'\n"
                + "           ,'" + order.getRequiredDate() + "'\n"
                + "           ,'" + order.getShippedDate() + "'\n"
                + "           ," + order.getShipVia() + "\n"
                + "           ," + order.getFreight() + "\n"
                + "           ,'" + order.getShipName() + "'\n"
                + "           ,'" + order.getShipAddress() + "'\n"
                + "           ,'" + order.getShipCity() + "'\n"
                + "           ,'" + order.getShipRegion() + "'\n"
                + "           ,'" + order.getShipPostalCode() + "'\n"
                + "           ,'" + order.getShipCountry() + "')";

        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addOrder(Order order) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([CustomerID]\n"
                + "           ,[EmployeeID]\n"
                + "           ,[OrderDate]\n"
                + "           ,[RequiredDate]\n"
                + "           ,[ShippedDate]\n"
                + "           ,[ShipVia]\n"
                + "           ,[Freight]\n"
                + "           ,[ShipName]\n"
                + "           ,[ShipAddress]\n"
                + "           ,[ShipCity]\n"
                + "           ,[ShipRegion]\n"
                + "           ,[ShipPostalCode]\n"
                + "           ,[ShipCountry])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, order.getCustomerID());
            pre.setInt(2, order.getEmployeeID());
            pre.setString(3, order.getOrderDate());
            pre.setString(4, order.getRequiredDate());
            pre.setString(5, order.getShippedDate());
            pre.setInt(6, order.getShipVia());
            pre.setDouble(7, order.getFreight());
            pre.setString(8, order.getShipName());
            pre.setString(9, order.getShipAddress());
            pre.setString(10, order.getShipCity());
            pre.setString(11, order.getShipRegion());
            pre.setString(12, order.getShipPostalCode());
            pre.setString(13, order.getShipCountry());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateOrder(Order order) {
        int n = 0;
        String sql = "UPDATE [dbo].[Orders] SET [CustomerID] = ?, [EmployeeID] = ?, [OrderDate] = ?, [RequiredDate] = ?, [ShippedDate] = ?, [ShipVia] = ?, [Freight] = ?, [ShipName] = ?, [ShipAddress] = ?, [ShipCity] = ?, [ShipRegion] = ?, [ShipPostalCode] = ?, [ShipCountry] = ? WHERE [OrderID] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, order.getCustomerID());
            pre.setInt(2, order.getEmployeeID());
            pre.setString(3, order.getOrderDate());
            pre.setString(4, order.getRequiredDate());
            pre.setString(5, order.getShippedDate());
            pre.setInt(6, order.getShipVia());
            pre.setDouble(7, order.getFreight());
            pre.setString(8, order.getShipName());
            pre.setString(9, order.getShipAddress());
            pre.setString(10, order.getShipCity());
            pre.setString(11, order.getShipRegion());
            pre.setString(12, order.getShipPostalCode());
            pre.setString(13, order.getShipCountry());
            pre.setInt(14, order.getOrderID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int removeOrder(int id) {
        int n = 0;
        String sql = "delete from Orders where OrderID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public Vector<Order> getOrder(String sql) {
        Vector<Order> vector = new Vector<Order>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int OrderID = rs.getInt(1);
                String CustomerID = rs.getString(2);
                int EmployeeID = rs.getInt(3);
                String OrderDate = rs.getString(4);

                String RequiredDate = rs.getString(5);
                String ShippedDate = rs.getString(6);
                int ShipVia = rs.getInt(7);
                double Freight = rs.getDouble(8);
                String ShipName = rs.getString(9),
                        ShipAddress = rs.getString(10), ShipCity = rs.getString(11),
                        ShipRegion = rs.getString(12), ShipPostalCode = rs.getString(13), ShipCountry = rs.getString(14);

                Order order = new Order(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                vector.add(order);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                int OrderID = rs.getInt(1);
                String CustomerID = rs.getString(2);
                int EmployeeID = rs.getInt(3);
                String OrderDate = rs.getString(4);
                String RequiredDate = rs.getString(5);
                String ShippedDate = rs.getString(6);
                int ShipVia = rs.getInt(7);
                double Freight = rs.getDouble(8);
                String ShipName = rs.getString(9),
                        ShipAddress = rs.getString(10), ShipCity = rs.getString(11),
                        ShipRegion = rs.getString(12), ShipPostalCode = rs.getString(13), ShipCountry = rs.getString(14);

                Order order = new Order(OrderID, CustomerID, EmployeeID,
                        OrderDate, RequiredDate, ShippedDate,
                        ShipVia, Freight, ShipName, ShipAddress,
                        ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                System.out.println(order);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();
//
////        DAOOrders dao = new DAOOrders();
//        int n = dao.insertOrder(new Order(1, "VINET", 1,
//                "2004-02-07", "2004-03-07", "2004-02-17",
//                1, 1, "ShipName", "ShipAddress", "HaNoi",
//                "FPT", "10", "VN"));
//        if (n > 0) {
//            System.out.println("Inserted");
//        }
//        int n = dao.addOrder(new Orders(1, "VINET", 1,
//                "OrderDate", "RequiredDate", "ShippedDate",
//                1, 1, "ShipName", "ShipAddress", "ShipCity",
//                "ShipRegion", "ShipPostalCode", "ShipCountry"));
//        if(n > 0){
//            System.out.println("Added");
//        }
//
//        int n = dao.updateOrder(new Orders(1, "CustomerID", 1,
//                "OrderDate", "RequiredDate", "ShippedDate",
//                1, 1, "ShipName", "ShipAddress", "ShipCity",
//                "ShipRegion", "ShipPostalCode", "ShipCountry"));
//        if(n >0){
//            System.out.println("Updated");
//        }
//
//        int n = dao.removeOrder(70);
//        if(n >0){
//            System.out.println("Deleted");
//        DAOProduct dao = new DAOProduct();
//        Vector<Orders> vector = dao.getOrders("select * from Orders");
//        for (Orders orders : vector) {
//            System.out.println(orders);
//        }
//
//    }
        // dao.displayAll("select * from Orders");
        ResultSet rsCus = dao.getData("Select CustomerID, CompanyName from Customers");
        try {

            while (rsCus.next()) {
                System.out.println(rsCus.getNString(1));
                System.out.println(rsCus.getNString(2));
            }
        } catch (Exception e) {
        }
    }
}

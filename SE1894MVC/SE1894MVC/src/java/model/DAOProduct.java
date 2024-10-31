/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
//import java.sql.Connection;

import entity.Product;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProduct extends DBConnect {
//    DBConnect dao=new DBConnect();
//    Connection con=dao.conn;

    public int insertProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES('" + pro.getProductName() + "'," + pro.getSupplierID()
                + "," + pro.getCategoryID() + ",'" + pro.getQuantityPerUnit()
                + "'," + pro.getUnitPrice() + "," + pro.getUnitsInStock()
                + "," + pro.getUnitsOnOrder() + "," + pro.getReorderLevel()
                + "," + (pro.isDiscontinued() == true ? 1 : 0) + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            //        ?: field ; index of ? start 1;  first (1): ProductName; second(2):SupplierID
            //        ... ?(9): Discontinued;
            PreparedStatement pre = conn.prepareStatement(sql);
            /*set value/parameter for ?
            pre.setDataType(indexOf?,value/para);
            Datatype is dataType of field; Ex: ProductName: String; SupplierID: int
     private int ProductID;
    private String ProductName;
    private int SupplierID, CategoryID;
    private String QuantityPerUnit;
    private double UnitPrice;
    private int UnitsInStock, UnitsOnOrder, ReorderLevel;
    private boolean Discontinued;       
             */
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, (pro.isDiscontinued() == true ? 1 : 0));
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]  SET [ProductName] = ?,[SupplierID] = ? ,[CategoryID] = ?,[QuantityPerUnit] = ?\n"
                + "      ,[UnitPrice] = ?,[UnitsInStock] = ?,[UnitsOnOrder] = ? ,[ReorderLevel] = ?,[Discontinued] = ?\n"
                + " WHERE ProductID= ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, (pro.isDiscontinued() == true ? 1 : 0));
            pre.setInt(10, pro.getProductID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void changDiscontinued(int pid, int newValue) {
        String sql = "update Products set Discontinued=" + newValue + " WHERE ProductID=" + pid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeProduct(int pid) {
        int n = 0;
//        check freign key
        ResultSet rs = getData("select * from [order details] where ProductID=" + pid);
        try {
            if (rs.next()) { //exist foreign key
                changDiscontinued(pid, 0);
//                return 0;
            } else {
                String sql = "delete from Products where ProductID=" + pid;
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
//        String sql = "delete from Products where ProductID=" + pid;
//        try {
//            Statement state = conn.createStatement();
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return n;
    }

    public Vector<Product> getProducts(String sql) {
        Vector<Product> vector = new Vector<Product>();
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY
            //ResultSet.TYPE_SCROLL_SENSITIVE: ThreadSafe
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString("ProductName");
                int SupplierID = rs.getInt(3), CategoryID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7), UnitsOnOrder = rs.getInt(8),
                        ReorderLevel = rs.getInt(9);
                boolean Discontinued = (rs.getInt(10) == 1 ? true : false);
                Product pro = new Product(ProductID, ProductName, SupplierID,
                        CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock,
                        UnitsOnOrder, ReorderLevel, Discontinued);
                //System.out.println(pro);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public void displayAll(String sql) {
        //sql="select * from Products";
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(indexField,fieldName)
                //  int pid = rs.getInt(1); //int pid=rs.getInt("ProductID");
                // String pname = rs.getString("ProductName");
                //String pname=rs.getString(2);
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString("ProductName");
                int SupplierID = rs.getInt(3), CategoryID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7), UnitsOnOrder = rs.getInt(8),
                        ReorderLevel = rs.getInt(9);
                boolean Discontinued = (rs.getInt(10) == 1 ? true : false);
                Product pro = new Product(ProductID, ProductName, SupplierID,
                        CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock,
                        UnitsOnOrder, ReorderLevel, Discontinued);
                System.out.println(pro);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
//        int n = dao.insertProduct(
//                new Product(1, "ProductName",
//                        1, 1,
//                        "QuantityPerUnit", 100.1,
//                        200, 20, 1, true));
//        int n = dao.addProduct(
//                new Product(1, "new ProductName",
//                        1, 1,
//                        "QuantityPerUnit", 100.1,
//                        200, 20, 1, true));
//         int n = dao.updateProduct(
//                new Product(78, "new ProductName",
//                        1, 1,
//                        "QuantityPerUnit", 100.1,
//                        200, 20, 1, true));
        //int n=dao.removeProduct(78);
//        int n = dao.removeProduct(1);
//        if (n > 0) {
//            System.out.println("deleted");
//        }
        //dao.displayAll("select * from Products");
        Vector<Product> vector = dao.getProducts("select * from Products");
        for (Product pro : vector) {
            System.out.println(pro);
        }
    }
}

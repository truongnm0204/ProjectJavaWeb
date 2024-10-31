/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOProduct extends DBConnection {

    //Connection conn=new DBConnection().conn;
    public int insertProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[model_year]\n"
                + "           ,[list_price]\n"
                + "           ,[brand_name]\n"
                + "           ,[category_name])\n"
                + "     VALUES (" + pro.getProduct_id() + ",'" + pro.getProduct_name()
                + "'," + pro.getModel_year() + "," + pro.getList_price()
                + ",'" + pro.getBrand_name() + "','" + pro.getCategory_name() + "')";
        // System.out.println(sql);
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
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[model_year]\n"
                + "           ,[list_price]\n"
                + "           ,[brand_name]\n"
                + "           ,[category_name])\n"
                + "     VALUES (?,?,?,?,?,?)";
        try {
            // ?: field, start from 1. first: product_id ; second:product_name
            PreparedStatement pre = conn.prepareStatement(sql);
//            set value/parameter for ?
//            pre.setDataType(index of ?, value/para);
//            DataType is dataType of field; first 1: product_id: int ; 
//            second 2:product_name: String
/*
private int product_id;
    private String product_name;
    private int model_year;
    private double list_price;
    private String brand_name, category_name;
             */
            pre.setInt(1, pro.getProduct_id());
            pre.setString(2, pro.getProduct_name());
            pre.setInt(3, pro.getModel_year());
            pre.setDouble(4, pro.getList_price());
            pre.setString(5, pro.getBrand_name());
            pre.setString(6, pro.getCategory_name());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [product_name] = ?,[model_year] = ? ,[list_price] = ?,[brand_name] = ?,[category_name] = ?\n"
                + " WHERE [product_id] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProduct_name());
            pre.setInt(2, pro.getModel_year());
            pre.setDouble(3, pro.getList_price());
            pre.setString(4, pro.getBrand_name());
            pre.setString(5, pro.getCategory_name());
            pre.setInt(6, pro.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changeStatus(int pid, int newStatus) {
        int n = 0;
        String sql = "update products set product_status=" + newStatus + " where product_id=" + pid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeProduct(int pid) {
        int n = 0;

        // check foreign key
        String sqlSelect = "select product_id from order_items where product_id=" + pid
                + " union\n"
                + "select product_id from stocks where product_id=" + pid;
        ResultSet rs = this.getData(sqlSelect);
        try {
            if (rs.next()) { //ton tai gia tri been khoa ngoai
                changeStatus(pid, 0);
            } else {
                String sql = "delete from products where product_id=" + pid;
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Product> getProducts(String sql) { //sql: select
        Vector<Product> vector = new Vector<Product>();
        try {
            //default:ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY
//            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
            ResultSet rs = getData(sql);
            while (rs.next()) {
                //rs.getDataType(index|fieldName);
                int product_id = rs.getInt(1);
                // int product_id=rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name"),
                        category_name = rs.getString("brand_name");
                Product pro = new Product(product_id, product_name,
                        model_year, list_price,
                        brand_name, category_name);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
//        int n=dao.insertProduct(new Product(202,"product_name",
//                2024,200.3,"brand_name",
//                "category_name"));
//        int n = dao.addProduct(new Product(203, "product_name",
//                2024, 200.3, "brand_name",
//                "category_name"));
//        int n = dao.updateProduct(new Product(203, "new product_name",
//                2024, 200.3, "brand_name",
//                "category_name"));
//        int n = dao.removeProduct(200);
//        if (n > 0) {
//            System.out.println("updated");
//        }
//Vector<Product> vector = dao.getProducts("select * from Products where model_year=2024");
        Vector<Product> vector = dao.getProducts("select * from Products");
        for (Product pro : vector) {
            System.out.println(pro);
        }
    }
}

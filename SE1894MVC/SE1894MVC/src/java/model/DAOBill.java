/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Bill;
import entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 84968
 */
public class DAOBill extends DBConnect {

    public Vector<Bill> getBill(String sql) {
        Vector<Bill> vector = new Vector<>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int OrderID = rs.getInt(1);
                String OrderDate = rs.getString(2);
                String RequiredDate = rs.getString(3);
                String ShippedDate = rs.getString(4);
                String ContactName = rs.getString(5);
                String Address = rs.getString(6);
                String Phone = rs.getString(7);
                String Fax = rs.getString(8);
                int ProductID = rs.getInt(9);
                String ProductName = rs.getString(10);
                double UnitPrice = rs.getDouble(11);
                int Quantity = rs.getInt(12);
                double Discount = rs.getDouble(13);

                Bill bill = new Bill(OrderID, OrderDate, RequiredDate, ShippedDate, ContactName, Address, Phone, Fax, ProductID, ProductName, UnitPrice, Quantity, Discount);
                vector.add(bill);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
}

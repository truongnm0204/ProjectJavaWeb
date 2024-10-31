
package model;

import entity.Suppliers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOSuppliers extends DBConnect {

    public int insertSuppliers(Suppliers sup) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax]\n"
                + "           ,[HomePage])\n"
                + "     VALUES('" + sup.getCompanyName() + "," + sup.getContactName() + "," + sup.getContactTitle()
                + "," + sup.getAddress() + "," + sup.getCity() + "," + sup.getRegion() + ","
                + sup.getPostalCode() + "," + sup.getCountry() + "," + sup.getPhone() + ","
                + sup.getFax() + "," + sup.getHomePage() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addSuppliers(Suppliers sup) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Suppliers]\n"
                + "           ([CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax]\n"
                + "           ,[HomePage])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getContactName());
            pre.setString(3, sup.getContactTitle());
            pre.setString(4, sup.getAddress());
            pre.setString(5, sup.getCity());
            pre.setString(6, sup.getRegion());
            pre.setString(7, sup.getPostalCode());
            pre.setString(8, sup.getCountry());
            pre.setString(9, sup.getPhone());
            pre.setString(10, sup.getFax());
            pre.setString(11, sup.getHomePage());

            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateSuppliers(Suppliers sup) {
        int n = 0;
        String sql = "UPDATE [dbo].[Suppliers]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] = ?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] = ?\n"
                + "      ,[Country] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Fax] = ?\n"
                + "      ,[HomePage] = ?\n"
                + " WHERE  SupplierID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getContactName());
            pre.setString(3, sup.getContactTitle());
            pre.setString(4, sup.getAddress());
            pre.setString(5, sup.getCity());
            pre.setString(6, sup.getRegion());
            pre.setString(7, sup.getPostalCode());
            pre.setString(8, sup.getCountry());
            pre.setString(9, sup.getPhone());
            pre.setString(10, sup.getFax());
            pre.setString(11, sup.getHomePage());
            pre.setInt(12, sup.getSupplierID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeSuppliers(int supId) {
        int n = 0;
        String sql = "delete from Suppliers where SupplierId = " + supId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayyAll(String sql) {
        ResultSet rs = null;

        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);

            while (rs.next()) {
                String companyName = rs.getString(1);
                String contactName = rs.getString(2);
                String contactTitle = rs.getString(3);
                String address = rs.getString(4);
                String city = rs.getString(5);
                String region = rs.getString(6);
                String postalCode = rs.getString(7);
                String country = rs.getString(8);
                String phone = rs.getString(9);
                String fax = rs.getString(10);
                String homePage = rs.getString(11);

                Suppliers suppliers = new Suppliers(1, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax, homePage);
                System.out.println(suppliers);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Suppliers> getSuppliers(String sql) {
        Vector<Suppliers> vector = new Vector<Suppliers>();
        try {
            // Sử dụng Statement với ResultSet.TYPE_SCROLL_SENSITIVE và ResultSet.CONCUR_UPDATABLE
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet
                int SupplierID = rs.getInt(1);
                String CompanyName = rs.getString(2);
                String ContactName = rs.getString(3);
                String ContactTitle = rs.getString(4);
                String Address = rs.getString(5);
                String City = rs.getString(6);
                String Region = rs.getString(7);
                String PostalCode = rs.getString(8);
                String Country = rs.getString(9);
                String Phone = rs.getString(10);
                String Fax = rs.getString(11);
                String HomePage = rs.getString(12);

                // Tạo đối tượng Suppliers và thêm vào Vector
                Suppliers supplier = new Suppliers(SupplierID, CompanyName, ContactName,
                        ContactTitle, Address, City, Region, PostalCode, Country,
                        Phone, Fax, HomePage);
                vector.add(supplier);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public static void main(String[] args) {
        for (Suppliers supplier : new DAOSuppliers().getSuppliers("select * from Suppliers")) {
            System.out.println(supplier);
        }
    }

}

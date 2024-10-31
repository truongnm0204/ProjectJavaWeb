
package model;

import entity.Shippers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOShippers extends DBConnect {

    public int insertShippers(Shippers s) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]\n"
                + "           ([CompanyName]\n"
                + "           ,[Phone])\n"
                + "     VALUES('" + s.getCompanyName() + "," + s.getPhone() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addShippers(Shippers s) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]\n"
                + "           ([CompanyName]\n"
                + "           ,[Phone])\n"
                + "     VALUES(?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateShippers(Shippers s) {
        int n = 0;
        String sql = "UPDATE [dbo].[Shippers]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[Phone] = ?\n"
                + " WHERE RegionID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, s.getCompanyName());
            pre.setString(2, s.getPhone());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeShippers(int sId) {
        int n = 0;
        String sql = "delete from Shipper where ShipperID = " + sId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
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
                String phone = rs.getString(2);

                Shippers shippers = new Shippers(1, companyName, phone);
                System.out.println(shippers);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShippers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

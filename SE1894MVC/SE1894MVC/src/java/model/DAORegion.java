
package model;

import entity.Region;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAORegion extends DBConnect {

    public int insertRegion(Region r) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Region]\n"
                + "           ([RegionID]\n"
                + "           ,[RegionDescription])\n"
                + "     VALUES('" + r.getRegionID() + "," + r.getRegionDescription() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addRegion(Region r) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Region]\n"
                + "           ([RegionID]\n"
                + "           ,[RegionDescription])\n"
                + "     VALUES(?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, r.getRegionID());
            pre.setString(2, r.getRegionDescription());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateRegion(Region r) {
        int n = 0;
        String sql = "UPDATE [dbo].[Region]\n"
                + "   SET [RegionID] =?\n"
                + "      ,[RegionDescription] = ?\n"
                + " WHERE RegionID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, r.getRegionID());
            pre.setString(2, r.getRegionDescription());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeRegion(int rId) {
        int n = 0;
        String sql = "delete from Region where RegionID = " + rId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayyAll(String sql) {
        ResultSet rs = null;

        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);

            while (rs.next()) {
                int regionID = rs.getInt(1);
                String regionDescription = rs.getString(2);

                Region region = new Region(regionID, regionDescription);
                System.out.println(region);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAORegion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

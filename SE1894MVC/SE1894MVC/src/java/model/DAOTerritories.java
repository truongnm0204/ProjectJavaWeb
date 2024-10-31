
package model;

import entity.Territories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTerritories extends DBConnect {

    public int insertTerritory(Territories ter) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Territories]\n"
                + "           ([TerritoryID]\n"
                + "           ,[TerritoryDescription]\n"
                + "           ,[RegionID])\n"
                + "     VALUES('" + ter.getTerritoryID() + "," + ter.getTerritoryDescription() + "," + ter.getRegionID() + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addTerritory(Territories ter) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Territories]\n"
                + "           ([TerritoryID]\n"
                + "           ,[TerritoryDescription]\n"
                + "           ,[RegionID])\n"
                + "     VALUES(?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, ter.getTerritoryID());
            pre.setString(2, ter.getTerritoryDescription());
            pre.setInt(3, ter.getRegionID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateTerritory(Territories ter) {
        int n = 0;
        String sql = "UPDATE [dbo].[Territories]\n"
                + "   SET [TerritoryID] = ?\n"
                + "      ,[TerritoryDescription] = ?\n"
                + "      ,[RegionID] = ?\n"
                + " WHERE  TerritoryID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, ter.getTerritoryID());
            pre.setString(2, ter.getTerritoryDescription());
            pre.setInt(3, ter.getRegionID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeTerritory(int terId) {
        int n = 0;
        String sql = "delete from Territories where TerritoryID = " + terId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayyAll(String sql) {
        ResultSet rs = null;

        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);

            while (rs.next()) {
                String territoryID = rs.getString(2);
                String territoryDescription = rs.getString(2);
                int regionID = rs.getInt(1);

                Territories territories = new Territories(territoryID, territoryDescription, regionID);
                System.out.println(territories);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

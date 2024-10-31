
package model;

import entity.EmployeeTerritories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmployeeTerritories extends DBConnect {

    public int insertEmployeeTerritories(EmployeeTerritories emt) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[EmployeeTerritories]\n"
                + "           ([EmployeeID]\n"
                + "           ,[TerritoryID])\n"
                + "     VALUES\n"
                + emt.getEmployeeID() + ","
                + "'" + emt.getTerritoryID() + "'";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addEmployeeTeritories(EmployeeTerritories emt) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[EmployeeTerritories]\n"
                + "           ([EmployeeID]\n"
                + "           ,[TerritoryID])\n"
                + "     VALUES(?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, emt.getEmployeeID());
            pre.setString(2, emt.getTerritoryID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateEmployeeTeritori(EmployeeTerritories emt) {
        int n = 0;
        String sql = "UPDATE [dbo].[EmployeeTerritories]\n"
                + "           ([EmployeeID] =?\n"
                + "           ,[TerritoryID]=?)\n"
                + "  WHERE EmployeeID =?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, emt.getEmployeeID());
            pre.setString(2, emt.getTerritoryID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int removeEmployeeTeritori(int id) {
        int n = 0;
        String sql = "delete from EmployeeTeritories where EmployeeID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<EmployeeTerritories> getEmployee(String sql) {
        Vector<EmployeeTerritories> vector = new Vector<EmployeeTerritories>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int EmployeeID = rs.getInt(1);
                String TerritoryID = rs.getString(2);
                EmployeeTerritories em = new EmployeeTerritories(EmployeeID, TerritoryID);
                vector.add(em);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public void displayAll(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement();
            while (rs.next()) {
                int EmployeeID = rs.getInt(1);
                String TerritoryID = rs.getString(2);
                EmployeeTerritories em = new EmployeeTerritories(EmployeeID, TerritoryID);
                System.out.println(em);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployeeTerritories.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        DAOEmployeeTerritories dao = new DAOEmployeeTerritories();
        int n = dao.insertEmployeeTerritories(new EmployeeTerritories(1, "HE186745"));
        if (n > 0) {
            System.out.println("inserted");
        }
    }

}

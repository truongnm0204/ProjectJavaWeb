/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DAOCategories extends DBConnect {
    
    public int insertCategories(Categories cate) {
        int n = 0;
        String sql = "NSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description]\n"
                + "           ,[Picture])\n"
                + "     VALUES\n"
                + "('" + cate.getCategoryName() + "', "
                + "'" + cate.getDescription() + "',"
                + "'" + cate.getPicture() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addCategories(Categories cate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description]\n"
                + "           ,[Picture])\n"
                + "     VALUES(?,?,?)\n";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            pre.setString(3, cate.getPicture());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCategories(Categories cate) {
        int n = 0;
        String sql = "UPDATE [dbo].[Categories] SET "
                + "[CategoryName] = ?, "
                + "[Description] = ?, "
                + "[Picture] = ?\n "
                + "WHERE [CategoryID] = ?";

        try {
            // Create the PreparedStatement object
            PreparedStatement pre = conn.prepareStatement(sql);

            // Set values for placeholders
            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            pre.setString(3, cate.getPicture());
            //pre.setInt(4, cate.getCategoryID());

            // Execute the update
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeCategories(int cateId) {
        int n = 0;
        String sql = "delete from Categories where CategoryID=" + cateId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
        public Vector<Categories> getCategory(String sql) {
        Vector<Categories> vector = new Vector<Categories>();
        Statement statement;
        try {
            // Create a Statement object with scrollable and updatable ResultSet
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);

            // Loop through the result set and fetch category data
            while (rs.next()) {
                int CategoryID = rs.getInt(1);
                String CategoryName = rs.getString(2);
                String Description = rs.getString(3);
                String Picture = rs.getString(4);

                // Create a new Category object
                Categories cate = new Categories(CategoryID, CategoryName, Description, Picture);

                // Add the Category object to the vector
                vector.add(cate);
            }
        } catch (SQLException ex) {
            // Log the exception if an error occurs
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the vector of Category objects
        return vector;
    }
}

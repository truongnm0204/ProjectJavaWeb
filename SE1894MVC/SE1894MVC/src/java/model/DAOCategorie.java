/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categorie;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author 84968
 */
public class DAOCategorie extends DBConnect {
    
    
    public Categorie login (String username, String password){
        Categorie cate = null;
        String sql = "select * from Categories where CategoryName=? and  CategoryID=?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                cate = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4));
            }
        } catch (Exception e) {
            
        }
        
        
        
        return cate;
    }
   
    
    
    public int insertCategorie(Categorie cate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description]\n"
                + "           ,[Picture])\n"
                + "     VALUES\n"
                + "           ('" + cate.getCategoryName() + "'\n"
                + "           ,'" + cate.getDescription() + "'\n"
                + "           ,'" + cate.getPicture() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addCategorie(Categorie cate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description]\n"
                + "           ,[Picture])\n"
                + "     VALUES(?,?,null)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            //pre.setString(3, cate.getPicture());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCategorie(Categorie cate) {
        int n = 0;
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [CategoryName] = ?"
                + "      ,[Description] = ?"
                + "      ,[Picture] = null"
                + "      ,[Discontinue] = ?"
                + " WHERE CategoryID =?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            //pre.setString(3, cate.getPicture());
            pre.setInt(3, (cate.isDiscontinue() == true ? 1 : 0));
            pre.setInt(4, cate.getCategoryID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
   public void changeDiscontinued(int pid, int newVlue) {
        String sql = "update Categories set Discontinue =" + newVlue + "WHERE CategoryID =" + pid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    public int removeCategorie(int id){
        int n =0;
        ResultSet rs = getData("select * from Products where CategoryID="+id);
        
        try {
            if(rs.next()){
                changeDiscontinued(id, 0);
            }
            String sql = "delete from Categories where CategoryID ="+id;
            Statement state = conn.createStatement();
             n = state.executeUpdate(sql);
        
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return n;
    }
    
    public Vector<Categorie> getCategorie(String sql){
        Vector<Categorie> vector = new Vector<>();
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            
            
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int CategoryID = rs.getInt(1);
                String CategoryName = rs.getString(2);
                String Description = rs.getString(3);
                String Picture = rs.getString(4);
                boolean Discontinue = (rs.getInt(5) == 1 ? true : false);
                Categorie cate = new Categorie(CategoryID, CategoryName, Description, Picture,Discontinue);
                vector.add(cate);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return vector;
    }
    
    public void displayAll(String sql){
        ResultSet rs = null;
        
        try {
            Statement state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                int CategoryID = rs.getInt(1);
                String CategoryName = rs.getString(2);
                String Description = rs.getString(3);
                String Picture = rs.getString(4);
                
                Categorie cate = new Categorie(CategoryID, CategoryName, Description, Picture);
                System.out.println(cate);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    

   // public static void main(String[] args) {
     //   DAOCategorie dao = new DAOCategorie();
//        int n = dao.insertCategorie(
//                new Categorie(1, "CategoryName", "Description", "Picture")
//        );
//        if(n >0){
//            System.out.println("success!");
//        }

//        int n = dao.addCategorie(
//                new Categorie(2, "CategoryName", "Description", "Picture")
//        );
//        if (n > 0) {
//            System.out.println("success!");
//        }


//
//        int n = dao.updateCategorie(
//                new Categorie(2, "CategoryName", "Description", "")
//        );
//        if (n > 0) {
//            System.out.println("success!");
//        }

//     int n = dao.removeCategorie(12);
//        if (n > 0) {
//            System.out.println("Deleted");
//        }
//    
//    }

//    Vector<Categorie> vector = dao.getCategorie("select * from Categories ");
//    
//        for (Categorie categorie : vector) {
//            System.out.println(categorie);
//        }
//    

//    dao.displayAll("select * from Categories ");
//
//
//  }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnect {

    public Connection conn = null;
    protected ResultSet resultSet;

    public DBConnect(String URL, String userName, String password) {
        try {
            //URL: String connection: server, database
            // userName, password: account of SQLServer
            //Call driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                //Call connect
                conn = DriverManager.getConnection(URL, userName, password);
                System.out.println("connected");
            } catch (SQLException ex) {
                ex.printStackTrace();
                //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            //Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private String URL = "";

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=SE1894",
                "sa", "123456");
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            rs = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
}

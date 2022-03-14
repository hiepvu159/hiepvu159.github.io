/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiep Vu
 */
public class ProductDAO {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    
    
    public ProductDAO() {
        connection();
    }

    public void connection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TheAlley;user=sa;password=123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }
    public void addProduct(Product pd ) {
        String sql = "insert into Product values(?,?,?,?)";
        try {
            
            pst = con.prepareStatement(sql);
            pst.setString(1, pd.getIDProduct());
            pst.setString(2, pd.getProductName());
            pst.setString(3, pd.getIDType());
            pst.setInt(4, pd.getPrice());
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }
    public void updateProduct(Product pd){
        String sql = "update Product set ProductName=?, IDType=?, Price=? where IDProduct=?";
        try {
            
            pst = con.prepareStatement(sql);
            pst.setString(1, pd.getProductName());
            pst.setString(2, pd.getIDType());//cbBox
            pst.setInt(3, pd.getPrice());//kieu int
            pst.setString(4, pd.getIDProduct());
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deleteProduct(Product pd){
         String sql = "Delete Product where IDProduct=?";
        try {
           
            pst = con.prepareStatement(sql);
            pst.setString(1, pd.getIDProduct());
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    public boolean checkDeleteProduct(Product pd){
        String sql1 = "select * from OrderDetails where IDProduct=?";
        boolean kq = true;
        try {
         pst = con.prepareStatement(sql1);
         pst.setString(1, pd.getIDProduct());
         rs = pst.executeQuery();
            if (rs.next()) {
                kq = false;
            }
        } catch (Exception e) {
        }
        return kq;
    }
 
}

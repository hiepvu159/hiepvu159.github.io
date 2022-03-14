/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Promotion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Hiep Vu
 */
public class PromotionsDAO {
     private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    
    
    public PromotionsDAO() {
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
    
    public void addPromo(Promotion pm){
        String sql = "Insert into Promotions values(?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pm.getNamePromo());
            pst.setInt(2, pm.getDiscountPromo());
            pst.setString(3, pm.getStartPromo());
            pst.setString(4, pm.getEndPromo());
            pst.setString(5, pm.getDescription());
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void updatePromo(Promotion pm){
        String sql = "Update Promotions set DiscountPromo=?, StartPromo=?, EndPromo=?, Description=? where IDPromo=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, pm.getDiscountPromo());
            pst.setString(2, pm.getStartPromo());
            pst.setString(3, pm.getEndPromo());
            pst.setString(4, pm.getDescription());
            pst.setInt(5, pm.getIDPromo());
            pst.executeUpdate();
        } catch (Exception e) {
        }
        
    }
    
    public void delelePromo(Promotion pm){
        String sql = "Delete from Promotions where IDPromo=?";
        try {
             pst = con.prepareStatement("Delete from Promotions where IDPromo=?");
             pst.setInt(1, pm.getIDPromo());
             pst.executeUpdate();
        } catch (Exception e) {
        }
    }
}

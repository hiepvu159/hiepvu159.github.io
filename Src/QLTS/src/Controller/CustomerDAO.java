/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Hiep Vu
 */
public class CustomerDAO {
     private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    
    
    public CustomerDAO() {
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
    public Boolean checkID(Customer cus){
        String sql = "select * from Customer where IdentityCard=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cus.getIdentityCard());
            rs = pst.executeQuery();
            if (rs.next()) {
                cus = new Customer(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(1), rs.getInt(7), rs.getInt(8));
                return true;
            }
        } catch (Exception e) {
        }
         return false;
    }
    public void addCus(Customer cus){
        try {
             pst = con.prepareStatement("Insert into Customer values(?,?,convert(varchar(20),getdate(),?,?,?,?)");
             pst.setString(1, cus.getIdentityCard());
             pst.setString(2, cus.getCusName());
             pst.setString(3, cus.getPhone());
             pst.setString(4, cus.getEmail());
             pst.setInt(5, cus.getQuantity());
             pst.setInt(6, cus.getDiscount());
             pst.executeQuery();
        } catch (Exception e) {
        }
    }
    public void updateCus(Customer cus){
        String sql = "Update Customer set Phone=?,Email=? where IDCus=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cus.getPhone());
            pst.setString(2, cus.getEmail());
            pst.setInt(3, cus.getIDCus());
            pst.executeQuery();
        } catch (Exception e) {
        }
    }
    public void delelteCus(Customer cus){
        String sql = "Delete from Customer where IDCus=?";
        try {
             pst = con.prepareStatement(sql);
             pst.setInt(1, cus.getIDCus());
             pst.executeQuery();
        } catch (Exception e) {
        }
    }

   
}

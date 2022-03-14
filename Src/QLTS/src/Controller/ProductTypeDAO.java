/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.ProductType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Hiep Vu
 */
public class ProductTypeDAO {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    
    
    public ProductTypeDAO() {
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
    
    public void addProductType(ProductType pdt ) {
        
        try {
            String sql = "insert into ProductType values(?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, pdt.getIDType());
            pst.setString(2, pdt.getTypeName());
            pst.setString(3, pdt.getSize());//cbBox           
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }
    
    public void updateProductType(ProductType pdt){
        try {
            String sql = "update ProductType set TypeName=?, Size=? where IDType=?";
            pst = con.prepareStatement(sql);
            pst.setString(3, pdt.getIDType());
            pst.setString(1, pdt.getTypeName());
            pst.setString(2, pdt.getSize());
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deleteProductType(ProductType pdt){
        try {
            String sql = "Delete ProductType where IDType=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, pdt.getIDType());
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList getTypeProduct() {
        ArrayList arry = new ArrayList();
        String sql = "SELECT * FROM ProductType";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ProductType pdt = new ProductType();
                arry.add(pdt = new ProductType(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
}

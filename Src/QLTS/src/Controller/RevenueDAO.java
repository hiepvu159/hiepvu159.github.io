/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Revenue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Hiep Vu
 */
public class RevenueDAO {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
//    private Revenue rev ;
    
    public RevenueDAO() {
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
    
    public List<Revenue> getList(){
        List<Revenue> arry = new ArrayList<Revenue>();
        try {
            pst = con.prepareStatement("select * from Revenue ");         
            rs = pst.executeQuery();
            while (rs.next()) {
                Revenue rev = new Revenue(rs.getString(2), rs.getString(3), rs.getInt(1));
                arry.add(rev);
            }
        } catch (Exception e) {
        }
        return arry;
    }
//    public Boolean checkList(Revenue rev){
//        try {
//            pst = con.prepareStatement("select * from Revenue where Date like ?");
//            pst.setString(1, "%"+ rev.getDate());
//            rs = pst.executeQuery();
//            if(rs.next()){
//                rev.setIDRevenue(1);
//                return  true;
//            }
//        } catch (Exception e) {
//        }
//        
//        return false;
//        
//    }
    public Boolean checkDate(Revenue rev){
        List<Revenue> arry = new ArrayList<Revenue>();
         try {
            pst = con.prepareStatement("select * from Revenue where Date like ?");
            pst.setString(1, "%" + rev.getDate());
            rs = pst.executeQuery();
            if(rs.next()){
                pst = con.prepareStatement("select * from Revenue where Date like ?");
                pst.setString(1, "%" + rev.getDate());
                rs = pst.executeQuery();
                while (rs.next()) {                    
                    rev = new Revenue(rs.getString(2), rs.getString(3), rs.getInt(1));
                    arry.add(rev);
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Admin;
import Model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Hiep Vu
 */
public class LoginDAO {
     private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    
    
    public LoginDAO() {
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
    
    public Boolean loginAdmin(Admin adm){
     
        String admin = "select * from Administrator where Username=? and Password=? ";
         try {
             pst = con.prepareStatement(admin);
             pst.setString(1, adm.getUsername());
             pst.setString(2, adm.getPassword());
             rs = pst.executeQuery();
            if (rs.next()) {
                adm.setUsername(rs.getString(1));
                adm.setPassword(rs.getString(2));
                
                return true;
            }
        } catch (Exception e) {
        }
         return false;
      
    }
//    public Boolean loginEmp(Employee emp){
//        String Emp = "select * from Employee where UsernameEmp=?  and Password=? ";
//        try {
//             pst = con.prepareStatement(Emp);
//             pst.setString(1, emp.getUserNameEmp());
//             pst.setString(2, emp.getPassWord());
//             pst.setString(3, emp.getNameEmp());
//             rs = pst.executeQuery();
//             if (rs.next()) {
//                emp.setUserNameEmp(rs.getString(1));
//                emp.setPassWord(rs.getString(2));
//                emp.setNameEmp(rs.getString(3));
//                return true;
//            }
//        } catch (Exception e) {
//        }
//         return false;
//    }
}

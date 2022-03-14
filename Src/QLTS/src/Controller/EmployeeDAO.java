/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.EmployeeDAO;
import Model.Employee;
import gui.EmployeeManager;
import server.DBHelper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Hiep Vu
 */
public class EmployeeDAO {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    
    
    public EmployeeDAO() {
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
    
   public boolean addAccount(Employee empl) {
        try {
            pst = con.prepareStatement("Insert into Employee values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, empl.getUserNameEmp());
            pst.setString(2, empl.getPassWord());
            pst.setString(3, empl.getNameEmp());
            pst.setString(4, empl.getGender());
            pst.setString(5, empl.getBirthDay());
            pst.setString(6, empl.getPhone());
            pst.setString(7, empl.getEmail());
            pst.setString(8, empl.getAddress());
            pst.setString(9,empl.getHinh());

            pst.executeUpdate();
           

           
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
   public void updateAccount(Employee empl) {
        
        try {
            pst = con.prepareStatement("update Employee set Password=?,NameEmp=?,Gender=?,Birthday=?,Phone=?,Email=?,Address=?,Hinh=? where UsernameEmp=?");

            pst.setString(9, empl.getUserNameEmp());
            pst.setString(1, empl.getPassWord());
            pst.setString(2, empl.getNameEmp());
            pst.setString(3, empl.getGender());
            pst.setString(4, empl.getBirthDay());
            pst.setString(5, empl.getPhone());
            pst.setString(6, empl.getEmail());
            pst.setString(7, empl.getAddress());
            pst.setString(8, empl.getHinh());
            

            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void deleteAccount(Employee empl) {

        try {
            pst = con.prepareStatement("Delete from Employee where UsernameEmp=?");
            pst.setString(1, empl.getUserNameEmp());
            pst.executeUpdate();
            pst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   public boolean checkOldPass(Employee emp){
        boolean kq = true;
        String sql = "Select * from Employee where NameEmp=? and Password=? COLLATE SQL_Latin1_General_CP1_CS_AS";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, emp.getNameEmp());
            pst.setString(2, emp.getPassWord());
            rs = pst.executeQuery();
            while (kq) {                
                
            }
        } catch (Exception e) {
        }
         return true;
    }
    public void changePassword(Employee emp){
        String sql = "Update Employee set Password=? where NameEmp=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,emp.getPassWord() );
            pst.setString(2, emp.getNameEmp());
            pst.executeUpdate();

        } catch (Exception e) {
        }
   
    }
   public void getUserName(){
       try {
            String url = "Select UsernameEmp from Employee";
            pst = con.prepareStatement(url);
            rs = pst.executeQuery();
          
            while (rs.next()) {
                Employee emp = new Employee();
                rs.getString(emp.getNameEmp());
            }
       } catch (Exception e) {
       }
   }
}

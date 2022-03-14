/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import Model.Employee;
import Controller.EmployeeDAO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Hiep
 */
public class PasswordChange extends javax.swing.JFrame {

    /**
     * Creates new form PasswordChange
     */
    ResultSet rs;
    PreparedStatement ps;
    server.DBHelper db = new server.DBHelper();
    Connection con = db.getCon();
    boolean flag1 = false, flag2 = false, flag3 = false;

    public PasswordChange(String EmpName) {
        initComponents();
        ImageIcon img = new ImageIcon("image//change_pass.png");
        this.setIconImage(img.getImage());
        lbName.setText(EmpName);
        lbName.setVisible(false);
        btnOK.setEnabled(false);
    }

    private PasswordChange() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("image//back_Login.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbPassCu = new javax.swing.JLabel();
        lbPassNew = new javax.swing.JLabel();
        lbConfirmPass = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbName = new javax.swing.JLabel();
        txtPassCu = new javax.swing.JPasswordField();
        txtPassNew = new javax.swing.JPasswordField();
        txtConfirmPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đổi mật khẩu");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Mật khẩu cũ:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Mật khẩu mới:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Nhập lại mật khẩu:");

        lbPassCu.setForeground(new java.awt.Color(255, 0, 0));
        lbPassCu.setText(" ");

        lbPassNew.setForeground(new java.awt.Color(255, 0, 0));
        lbPassNew.setText(" ");

        lbConfirmPass.setForeground(new java.awt.Color(255, 0, 0));
        lbConfirmPass.setText(" ");

        btnOK.setText("Đồng ý");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbName.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        lbName.setText("Trống");

        txtPassCu.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPassCuCaretUpdate(evt);
            }
        });

        txtPassNew.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPassNewCaretUpdate(evt);
            }
        });

        txtConfirmPass.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConfirmPassCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbName)
                            .addGap(383, 383, 383))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbConfirmPass)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnOK)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton2)))
                                    .addGap(0, 103, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtConfirmPass))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(35, 35, 35)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbPassNew)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(txtPassNew)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(116, 116, 116)
                            .addComponent(lbPassCu)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(45, 45, 45)
                            .addComponent(txtPassCu)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbName)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtPassCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPassCu)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbPassNew)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbConfirmPass)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOK)
                        .addComponent(jButton2))
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txtPassCu.setText("");
        txtPassNew.setText("");
        txtConfirmPass.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (flag1 == true && flag2 == true && flag3 == true) {
           Employee emp = new Employee();
           EmployeeDAO empd = new EmployeeDAO();
           emp.setPassWord(txtConfirmPass.getText());
           emp.setNameEmp(lbName.getText());
           empd.changePassword(emp);
           JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!");
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtPassCuCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPassCuCaretUpdate
        if (txtPassCu.getText().trim().equals("")) {
            lbPassCu.setText(" ");
            btnOK.setEnabled(false);
        } else {
            
            EmployeeDAO empd= new EmployeeDAO();
            Employee emp = new Employee();
            emp.setPassWord(txtPassCu.getText());
            emp.setNameEmp(lbName.getText());
            if(txtPassCu.getText().trim().equals("")){
                lbPassCu.setText("Đúng mật khẩu.");
                lbPassCu.setForeground(Color.blue);
                flag1 = true;
                btnOK.setEnabled(false);
                        
            }else{           
                lbPassCu.setText("Sai mật khẩu.");
                lbPassCu.setForeground(Color.red);
                flag1 = false;
                btnOK.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txtPassCuCaretUpdate

    private void txtPassNewCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPassNewCaretUpdate
        if (txtPassNew.getText().trim().equals("")) {
            lbPassNew.setText(" ");
            btnOK.setEnabled(false);
        } else if (txtPassNew.getText().equals(txtPassCu.getText())) {
            lbPassNew.setText("Mật khẩu mới phải khác mật khẩu cũ.");
            lbPassNew.setForeground(Color.red);
            btnOK.setEnabled(false);
        } else {
            char x;
            for (int i = 0; i < txtPassNew.getText().length(); i++) {
                x = txtPassNew.getText().charAt(i);
                if (x == ' ') {
                    lbPassNew.setText("Mật khẩu không thể chứa khoảng trắng.");
                    lbPassNew.setForeground(Color.red);
                    flag2 = false;
                    btnOK.setEnabled(false);
                    return;
                }
            }
            while (true) {
                if (txtPassNew.getText().length() < 6 || txtPassNew.getText().length() > 18) {
                    lbPassNew.setText("Độ dài mật khẩu trong khoảng 6-18 kí tự.");
                    lbPassNew.setForeground(Color.red);
                    flag2 = false;
                    btnOK.setEnabled(false);
                    return;
                } else {
                    lbPassNew.setText("Mật khẩu hợp lệ.");
                    lbPassNew.setForeground(Color.blue);
                    flag2 = true;
                    btnOK.setEnabled(false);
                    break;
                }
            }
        }
    }//GEN-LAST:event_txtPassNewCaretUpdate

    private void txtConfirmPassCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConfirmPassCaretUpdate
        if (txtConfirmPass.getText().trim().equals("")) {
            lbConfirmPass.setText(" ");
            btnOK.setEnabled(false);
        } else {
            while (true) {
                if (!txtConfirmPass.getText().equals(txtPassNew.getText())) {
                    lbConfirmPass.setText("Nhập lại mật khẩu phải giống mật khẩu.");
                    lbConfirmPass.setForeground(Color.red);
                    flag3 = false;
                    btnOK.setEnabled(false);
                    return;
                } else {
                    lbConfirmPass.setText("Hợp lệ.");
                    lbConfirmPass.setForeground(Color.blue);
                    flag3 = true;
                    btnOK.setEnabled(true);
                    break;
                }
            }
        }
    }//GEN-LAST:event_txtConfirmPassCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbConfirmPass;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPassCu;
    private javax.swing.JLabel lbPassNew;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JPasswordField txtPassCu;
    private javax.swing.JPasswordField txtPassNew;
    // End of variables declaration//GEN-END:variables
}

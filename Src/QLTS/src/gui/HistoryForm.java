/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import Model.*;
import Controller.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hiep
 */
public class HistoryForm extends javax.swing.JFrame {

    /**
     * Creates new form History
     */
    Vector vecKhongCTKM, vecKHV, vecCTKM, vecEmp;
    Connection con;
    ResultSet rsKhongCTKM, rsKHV, rsCTKM, rsEmp, rsInfoEmp, rs;
    PreparedStatement ps;
    DefaultTableModel tblModel = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }

    };
    server.DBHelper db = new server.DBHelper();
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("#,###");

    public HistoryForm() {
        initComponents();
        ImageIcon img = new ImageIcon("image//history-icon-68319.png");
        this.setIconImage(img.getImage());
        btnSearch.setSize(30, 30);
        new SetImage().setImageButton(btnSearch, "image//search-512.png");
        btnReset.setSize(30, 30);
        new SetImage().setImageButton(btnReset, "image//3a4a6aea2a19c76c632a9092e7fd9a3f.png");
        btnPrint.setSize(50, 50);
        new SetImage().setImageButton(btnPrint, "image//Printer-icon.png");
        con = db.getCon();
        tblModel.addColumn("Mã đơn hàng");
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Số lượng (Ly)");
        tblModel.addColumn("Đơn giá (VNĐ)");
        tblModel.addColumn("Tên CTKM");
        tblModel.addColumn("Mã khách hàng");
        tblModel.addColumn("Chiết khấu (%)");
        tblModel.addColumn("Thời gian");
        tblModel.addColumn("Ngày");
        tblModel.addColumn("TK nhân viên");
        tblModel.addColumn("Thành tiền (VNĐ)");
        tblHistory.setModel(tblModel);
        
        try {
            String url = "Select UsernameEmp from Employee";
            ps = con.prepareStatement(url);
            rsEmp = ps.executeQuery();
            vecEmp = new Vector();
            while (rsEmp.next()) {
                vecEmp.add(rsEmp.getString("UsernameEmp"));
            }
            JTextField text = (JTextField) cbEmp.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbEmp, vecEmp));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        ReloadTbl();
        LoadlbTotal();
        btnResetActionPerformed(null);
    }
   
    public void LoadlbTotal() {
        int total = 0;
        int line = tblHistory.getRowCount();
        for (int i = 0; i < line; i++) {
            String ThanhTien = (String) tblHistory.getValueAt(i, 10);
            total += Integer.parseInt(ThanhTien.replaceAll(",", ""));
        }
        lbTotal.setText(formatter.format(total) + " VNĐ");
    }

    public void ReloadTbl() {
        tblModel.getDataVector().removeAllElements();
       //select theo khách hàng VIP
        try {
            ps = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                    + "where Orderdetails.CusName != 'Khách vãng lai'");
            rsKHV = ps.executeQuery();
            while (rsKHV.next()) {
                vecKHV = new Vector();
                vecKHV.add(rsKHV.getString("IDOrder"));
                vecKHV.add(rsKHV.getString("IDProduct"));
                vecKHV.add(rsKHV.getString("Quantity"));
                vecKHV.add(formatter.format(rsKHV.getInt("Price")));
                vecKHV.add(rsKHV.getString("NamePromo"));
                vecKHV.add(rsKHV.getString("CusName"));
                vecKHV.add(rsKHV.getString("Discount"));
                vecKHV.add(rsKHV.getString("TimeOrder"));
                vecKHV.add(rsKHV.getString("DateOrder"));
                vecKHV.add(rsKHV.getString("UsernameEmp"));
                int quanKHV = rsKHV.getInt("Quantity");
                int priceKHV = rsKHV.getInt("Price");
                int discountKHV = rsKHV.getInt("Discount");
                int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                vecKHV.add(formatter.format(totalKHV));
                tblModel.addRow(vecKHV);
            }
            tblHistory.setModel(tblModel);
        } catch (Exception e) {
        }
        //select theo CTKM
        try {
            ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo");
            rsCTKM = ps.executeQuery();
            while (rsCTKM.next()) {
                vecCTKM = new Vector();
                vecCTKM.add(rsCTKM.getString("IDOrder"));
                vecCTKM.add(rsCTKM.getString("IDProduct"));
                vecCTKM.add(rsCTKM.getString("Quantity"));
                vecCTKM.add(formatter.format(rsCTKM.getInt("Price")));
                vecCTKM.add(rsCTKM.getString("NamePromo"));
                vecCTKM.add(rsCTKM.getString("CusName"));
                vecCTKM.add(rsCTKM.getString("DiscountPromo"));
                vecCTKM.add(rsCTKM.getString("TimeOrder"));
                vecCTKM.add(rsCTKM.getString("DateOrder"));
                vecCTKM.add(rsCTKM.getString("UsernameEmp"));
                int quanCTKM = rsCTKM.getInt("Quantity");
                int priceCTKM = rsCTKM.getInt("Price");
                int discountCTKM = rsCTKM.getInt("DiscountPromo");
                int dismoneyCTKM = (quanCTKM * priceCTKM * discountCTKM) / 100;
                int totalCTKM = (priceCTKM * quanCTKM) - dismoneyCTKM;
                vecCTKM.add(formatter.format(totalCTKM));
                tblModel.addRow(vecCTKM);
            }
            tblHistory.setModel(tblModel);
        } catch (SQLException ex) {
        }
        //select theo không áp dụng CTKM
        try {
            ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "where NamePromo='Không có'");
            rsKhongCTKM = ps.executeQuery();
            while (rsKhongCTKM.next()) {
                vecKhongCTKM = new Vector();
                vecKhongCTKM.add(rsKhongCTKM.getString("IDOrder"));
                vecKhongCTKM.add(rsKhongCTKM.getString("IDProduct"));
                vecKhongCTKM.add(rsKhongCTKM.getString("Quantity"));
                vecKhongCTKM.add(formatter.format(rsKhongCTKM.getInt("Price")));
                vecKhongCTKM.add(rsKhongCTKM.getString("NamePromo"));
                vecKhongCTKM.add(rsKhongCTKM.getString("CusName"));
                vecKhongCTKM.add("0");
                vecKhongCTKM.add(rsKhongCTKM.getString("TimeOrder"));
                vecKhongCTKM.add(rsKhongCTKM.getString("DateOrder"));
                vecKhongCTKM.add(rsKhongCTKM.getString("UsernameEmp"));
                int quanKhongCTKM = rsKhongCTKM.getInt("Quantity");
                int priceKhongCTKM = rsKhongCTKM.getInt("Price");
                int totalKhongCTKM = priceKhongCTKM * quanKhongCTKM;
                vecKhongCTKM.add(formatter.format(totalKhongCTKM));
                tblModel.addRow(vecKhongCTKM);
            }
            tblHistory.setModel(tblModel);
        } catch (SQLException ex) {
        }
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
            ImageIcon icon = new ImageIcon("image//bkgr.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbEmp = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        txtdate = new com.toedter.calendar.JDateChooser();
        btnReset = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lịch sử bán hàng");
        setResizable(false);

        tblHistory.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblHistory);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("TK nhân viên:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Ngày lập đơn hàng:");

        cbEmp.setEditable(true);
        cbEmp.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtdate.setDateFormatString("dd/MM/yyyy");

        btnReset.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Tổng số tiền:");

        lbTotal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbTotal.setText("0 VNĐ");

        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1004, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbTotal))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSearch)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnReset)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(cbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSearch)
                                    .addComponent(btnReset)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(lbTotal))
                            .addGap(0, 3, Short.MAX_VALUE))
                        .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String date = ft.format(txtdate.getDate());
        String name = (String) cbEmp.getSelectedItem();
        if (cbEmp.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Tài khoản nhân viên không được để trống.");
            btnPrint.setEnabled(false);
        } else {
            try {
                ps = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                        + "where UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                rs = ps.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nhân viên " + name + " chưa bán được sản phẩm nào trong ngày " + date + ".");
                    btnResetActionPerformed(evt);
                } else {
                    btnPrint.setEnabled(true);
                    tblModel.getDataVector().removeAllElements();
                    //select theo khách hàng VIP
                    try {
                        ps = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                                + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                                + "where Orderdetails.CusName != 'Khách vãng lai' and UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rsKHV = ps.executeQuery();
                        while (rsKHV.next()) {
                            vecKHV = new Vector();
                            vecKHV.add(rsKHV.getString("IDOrder"));
                            vecKHV.add(rsKHV.getString("IDProduct"));
                            vecKHV.add(rsKHV.getString("Quantity"));
                            vecKHV.add(formatter.format(rsKHV.getInt("Price")));
                            vecKHV.add(rsKHV.getString("NamePromo"));
                            vecKHV.add(rsKHV.getString("CusName"));
                            vecKHV.add(rsKHV.getString("Discount"));
                            vecKHV.add(rsKHV.getString("TimeOrder"));
                            vecKHV.add(rsKHV.getString("DateOrder"));
                            vecKHV.add(rsKHV.getString("UsernameEmp"));
                            int quanKHV = rsKHV.getInt("Quantity");
                            int priceKHV = rsKHV.getInt("Price");
                            int discountKHV = rsKHV.getInt("Discount");
                            int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                            int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                            vecKHV.add(formatter.format(totalKHV));
                            tblModel.addRow(vecKHV);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException e) {
                    }
                    //select theo CTKM
                    try {
                        ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                                + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo "
                                + "where UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rsCTKM = ps.executeQuery();
                        while (rsCTKM.next()) {
                            vecCTKM = new Vector();
                            vecCTKM.add(rsCTKM.getString("IDOrder"));
                            vecCTKM.add(rsCTKM.getString("IDProduct"));
                            vecCTKM.add(rsCTKM.getString("Quantity"));
                            vecCTKM.add(formatter.format(rsCTKM.getInt("Price")));
                            vecCTKM.add(rsCTKM.getString("NamePromo"));
                            vecCTKM.add(rsCTKM.getString("CusName"));
                            vecCTKM.add(rsCTKM.getString("DiscountPromo"));
                            vecCTKM.add(rsCTKM.getString("TimeOrder"));
                            vecCTKM.add(rsCTKM.getString("DateOrder"));
                            vecCTKM.add(rsCTKM.getString("UsernameEmp"));
                            int quanCTKM = rsCTKM.getInt("Quantity");
                            int priceCTKM = rsCTKM.getInt("Price");
                            int discountCTKM = rsCTKM.getInt("DiscountPromo");
                            int dismoneyCTKM = (quanCTKM * priceCTKM * discountCTKM) / 100;
                            int totalCTKM = (priceCTKM * quanCTKM) - dismoneyCTKM;
                            vecCTKM.add(formatter.format(totalCTKM));
                            tblModel.addRow(vecCTKM);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException ex) {
                    }
                    //select theo không áp dụng CTKM
                    try {
                        ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                                + "where NamePromo='Không có' and UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rsKhongCTKM = ps.executeQuery();
                        while (rsKhongCTKM.next()) {
                            vecKhongCTKM = new Vector();
                            vecKhongCTKM.add(rsKhongCTKM.getString("IDOrder"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("IDProduct"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("Quantity"));
                            vecKhongCTKM.add(formatter.format(rsKhongCTKM.getInt("Price")));
                            vecKhongCTKM.add(rsKhongCTKM.getString("NamePromo"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("CusName"));
                            vecKhongCTKM.add("0");
                            vecKhongCTKM.add(rsKhongCTKM.getString("TimeOrder"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("DateOrder"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("UsernameEmp"));
                            int quanKhongCTKM = rsKhongCTKM.getInt("Quantity");
                            int priceKhongCTKM = rsKhongCTKM.getInt("Price");
                            int totalKhongCTKM = priceKhongCTKM * quanKhongCTKM;
                            vecKhongCTKM.add(formatter.format(totalKhongCTKM));
                            tblModel.addRow(vecKhongCTKM);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException ex) {
                    }
                }
            } catch (SQLException ex) {
            }
        }
        LoadlbTotal();
    
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        cbEmp.setSelectedIndex(-1);
        ReloadTbl();
        LoadlbTotal();
        btnPrint.setEnabled(false);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        //xóa file txt
        File file = new File("History.txt");
        file.delete();
        //Viết vào file txt
        try {
            Date now = new Date();
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History.txt"), "UTF8"));
            b.write("THE ALLEY\r\n\r\n");
            b.write("Địa chỉ: Hà Đông - Hà Nội\r\n");
            b.write("SĐT: 0984896523\r\n");
            b.write("Thời gian: " + time.format(now) + "\r\n\r\n");
            b.write("\t\t\t\t\tBẢNG THỐNG KÊ BÁN HÀNG NGÀY " + ft.format(txtdate.getDate()) + "\r\n");
            b.write("Tài khoản: " + cbEmp.getSelectedItem() + "\r\n");
            try {
                ps = con.prepareStatement("Select * from Employee where UsernameEmp=?");
                ps.setString(1, (String) cbEmp.getSelectedItem());
                rsInfoEmp = ps.executeQuery();
                if (rsInfoEmp.next()) {
                    b.write("Tên nhân viên: " + rsInfoEmp.getString("NameEmp") + "\r\n\r\n");
                }
            } catch (SQLException ex) {
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("Mã ĐH\tMã SP\tSố lượng (ly)\tĐơn giá (VNĐ)\tTên CTKM\tMã khách hàng\tChiết khấu (%)\tThời gian\tThành tiền (VNĐ)\r\n");
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");

            int line = tblHistory.getRowCount();
            for (int i = 0; i < line; i++) {
                String s1 = (String) tblHistory.getValueAt(i, 0);
                String s2 = (String) tblHistory.getValueAt(i, 1);
                String s3 = (String) tblHistory.getValueAt(i, 2);
                String s4 = (String) tblHistory.getValueAt(i, 3);
                String s5 = (String) tblHistory.getValueAt(i, 4);
                String MKH = (String) tblHistory.getValueAt(i, 5);
                String s6;
                if (!MKH.equals("Khách vãng lai")) {
                    s6 = (String) tblHistory.getValueAt(i, 5) + "\t";
                } else {
                    s6 = (String) tblHistory.getValueAt(i, 5);
                }
                String s7 = (String) tblHistory.getValueAt(i, 6);
                String s8 = (String) tblHistory.getValueAt(i, 7);
                String s11 = (String) tblHistory.getValueAt(i, 10);
                b.write(s1 + "\t" + s2 + "\t" + s3 + "\t\t" + s4 + "\t\t" + s5 + "\t" + s6 + "\t" + s7 + "\t\t" + s8 + "\t" + s11 + "\r\n");
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("Tổng tiền: " + lbTotal.getText());
            b.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //Mở file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad History.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        btnResetActionPerformed(evt);
    }//GEN-LAST:event_btnPrintActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tblHistory;
    private com.toedter.calendar.JDateChooser txtdate;
    // End of variables declaration//GEN-END:variables
}
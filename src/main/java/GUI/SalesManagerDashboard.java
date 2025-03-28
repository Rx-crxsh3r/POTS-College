package GUI;

import Admin.LoginFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author abdul
 */
public class SalesManagerDashboard extends javax.swing.JFrame {
    String role;
    

    /**
     * Creates new form SalesManagerDashboard
     * @param result
     */
    public SalesManagerDashboard(String role) {
        this.role = role; // Pass the role parameter
        initComponents();
        configureUIBasedOnRole();
    }
    
    private void configureUIBasedOnRole() {
        if ("Administrator".equalsIgnoreCase(role)) {
            AdminHeader.setText("You Have Access To SM Functionalities!"); // Hide AdminHeader for Administrator
            AdminHeader.setFont(AdminHeader.getFont().deriveFont(18f)); // Set smaller font size
            logoutBtn.setVisible(false);   // Hide logout button for Administrator
        }
    }
    
    // Method to hide the AdminHeader
    public void hideAdminHeader() {
        AdminHeader.setVisible(false);
    }

    // Method to hide logout button
    public void setLogoutButtonVisible(boolean isVisible) {
        logoutBtn.setVisible(isVisible);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jBtnViewItems = new javax.swing.JButton();
        jBtnRetails = new javax.swing.JButton();
        jBtnSalesReport = new javax.swing.JButton();
        jBtnStockLevel = new javax.swing.JButton();
        jBtnViewPO = new javax.swing.JButton();
        AdminHeader = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(300, 600));

        jBtnViewItems.setText("View Items");
        jBtnViewItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnViewItemsActionPerformed(evt);
            }
        });

        jBtnRetails.setText("Retails");
        jBtnRetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRetailsActionPerformed(evt);
            }
        });

        jBtnSalesReport.setText("Sales Report");
        jBtnSalesReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalesReportActionPerformed(evt);
            }
        });

        jBtnStockLevel.setText("Stock Level");
        jBtnStockLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnStockLevelActionPerformed(evt);
            }
        });

        jBtnViewPO.setText("View Purchaser Orders ");
        jBtnViewPO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnViewPOActionPerformed(evt);
            }
        });

        AdminHeader.setFont(new java.awt.Font("sansserif", 3, 30)); // NOI18N
        AdminHeader.setText("Sales Manager Menu");

        logoutBtn.setBackground(new java.awt.Color(255, 51, 51));
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jBtnViewPO, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBtnStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBtnSalesReport, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBtnRetails, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jBtnViewItems, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AdminHeader))))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(AdminHeader)
                .addGap(38, 38, 38)
                .addComponent(jBtnViewItems, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnRetails, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSalesReport, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnStockLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnViewPO, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnViewItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnViewItemsActionPerformed
        // TODO add your handling code here:
        ViewItems VI = new ViewItems(role);
        VI.show();
        this.hide();
    }//GEN-LAST:event_jBtnViewItemsActionPerformed

    private void jBtnRetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRetailsActionPerformed
        // TODO add your handling code here:
        var RetailsForm = new Retails(role);
        RetailsForm.show();
        this.hide();
    }//GEN-LAST:event_jBtnRetailsActionPerformed

    private void jBtnSalesReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalesReportActionPerformed
        // TODO add your handling code here:
        var SalesReportForm = new SalesReport(role);
        SalesReportForm.show();
        this.hide();
    }//GEN-LAST:event_jBtnSalesReportActionPerformed

    private void jBtnStockLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnStockLevelActionPerformed
        // TODO add your handling code here:
        var StockLevelForm = new StockLevel(role);
        StockLevelForm.show();
        this.hide();    
    }//GEN-LAST:event_jBtnStockLevelActionPerformed

    private void jBtnViewPOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnViewPOActionPerformed
        // TODO add your handling code here:
        viewPOSM VPS = new viewPOSM(role);
        VPS.show();
        this.hide();
    }//GEN-LAST:event_jBtnViewPOActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            window.dispose();
        }
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_logoutBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalesManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AdminHeader;
    private javax.swing.JButton jBtnRetails;
    private javax.swing.JButton jBtnSalesReport;
    private javax.swing.JButton jBtnStockLevel;
    private javax.swing.JButton jBtnViewItems;
    private javax.swing.JButton jBtnViewPO;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton logoutBtn;
    // End of variables declaration//GEN-END:variables
}

package GUI;

import static Admin.LoginFrame.getLoggedInUserId;
import Classes.item;
import Classes.Request;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author abdul
 */
public class StockLevel extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private Request requisition;
    private List<item> items;
    String r;


    /**
     * Creates new form StockLevel
     */
    public StockLevel(String role) {
        initComponents();
        requisition = new Request();
        items = requisition.loadItems();
        r = role;

        tableModel = new DefaultTableModel(new String[]{"Item Code", "Item Name", "Stock", "Reorder Level", "Status"}, 0);
        jTable1.setModel(tableModel);

        populateItemsTable(items);
        jRadioButton1.setSelected(true);
        ButtonGroup filterGroup = new ButtonGroup();
        filterGroup.add(jRadioButton1);
        filterGroup.add(jRadioButton2);
    }

    
    private void populateItemsTable(List<item> items) {
       tableModel.setRowCount(0);

       for (item item : items) {
           tableModel.addRow(new Object[]{
               item.getItemCode(),
               item.getItemName(),
               item.getQuantityInStock(),
               item.getReorderLevel(),
               item.getStatus()
           });
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuItem1 = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButtonRequest = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jbackButton4 = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(jTable1);

        jRadioButton1.setText("All Items");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("below the reorder level");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButtonRequest.setText("Request");
        jButtonRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRequestActionPerformed(evt);
            }
        });

        jLabel1.setText("filter Stock Level:");

        jbackButton4.setText("Back");
        jbackButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbackButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1)
                            .addComponent(jbackButton4))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jButtonRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbackButton4)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1)
                        .addGap(24, 24, 24)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:

        populateItemsTable(items); 
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        List<item> filteredItems = requisition.filterItemsBelowReorderLevel(items);
        populateItemsTable(filteredItems);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButtonRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRequestActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            String itemCode = (String) jTable1.getValueAt(selectedRow, 0);
            String itemName = (String) jTable1.getValueAt(selectedRow, 1);
            int quantityInStock = (int) jTable1.getValueAt(selectedRow, 2); 
            int reorderLevel = (int) jTable1.getValueAt(selectedRow, 3);

            if (quantityInStock < reorderLevel) {
                // Proceed to create requisition
                int quantityRequired = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity required:"));
                String requestedBy = r; 

                requisition.create(itemCode, itemName, quantityRequired, requestedBy);
                JOptionPane.showMessageDialog(this, "Requisition created successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "The item is above the reorder level. No need to create a requisition.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item.");
        }

    }//GEN-LAST:event_jButtonRequestActionPerformed

    private void jbackButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbackButton4ActionPerformed
        // TODO add your handling code here:
        var SalesManagerDashboard = new SalesManagerDashboard(r);
        SalesManagerDashboard.show();
        this.hide();
    }//GEN-LAST:event_jbackButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(StockLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButtonRequest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbackButton4;
    // End of variables declaration//GEN-END:variables
}

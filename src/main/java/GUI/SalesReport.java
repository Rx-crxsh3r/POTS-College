package GUI;

import Classes.SalesReportClass;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author abdul
 */
public class SalesReport extends javax.swing.JFrame {
    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = {"Item Code", "Item Name", "Item category", "Quantity", "Date", "Total Amount"};
    private SalesReportClass reportGenerator;
    String r;

    /**
     * Creates new form SalesReport
     */
    public SalesReport(String role) {
        initComponents();
        model.setColumnIdentifiers(columnName);
        reportGenerator = new SalesReportClass();
        populateComboBoxes();
        r = role;
    }
    
    private void populateComboBoxes() {
        Set<String> itemCodes = reportGenerator.getItemCodes();
        Set<String> years = reportGenerator.getYears();
        Set<String> months = reportGenerator.getMonths();

        jComboBoxItemCode.setModel(new DefaultComboBoxModel<>(itemCodes.toArray(String[]::new)));
        jComboBoxYear.setModel(new DefaultComboBoxModel<>(years.toArray(String[]::new)));
        jComboBoxMonth.setModel(new DefaultComboBoxModel<>(months.toArray(String[]::new)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBoxItemCode = new javax.swing.JComboBox<>();
        jComboBoxYear = new javax.swing.JComboBox<>();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonView = new javax.swing.JButton();
        jbackButton = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jComboBoxItemCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Item Code:");

        jLabel2.setText("Year:");

        jLabel3.setText("Month:");

        jButtonView.setText("View");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });

        jbackButton.setText("Back");
        jbackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbackButtonActionPerformed(evt);
            }
        });

        jButtonExport.setText("Export");
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 72, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbackButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButtonExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbackButton)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonView)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExport)
                        .addGap(50, 50, 50))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        // TODO add your handling code here:                                           
                                           
        // Get selected values from combo boxes
        String selectedItemCode = (String) jComboBoxItemCode.getSelectedItem();
        String selectedYear = (String) jComboBoxYear.getSelectedItem();
        String selectedMonth = (String) jComboBoxMonth.getSelectedItem();

        // Check if all fields have a valid selection
        if (selectedItemCode == null || selectedItemCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a valid Item Code.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (selectedYear == null || selectedYear.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a valid Year.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (selectedMonth == null || selectedMonth.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a valid Month.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate the report using SalesReportClass
        List<String[]> filteredEntries = reportGenerator.generateReport(selectedItemCode, selectedYear, selectedMonth);

        // Clear existing table rows
        model.setRowCount(0);

        // Initialize variables for total quantity and total amount
        int totalQuantity = 0;
        double totalAmount = 0.0;

        // Populate the table with the filtered entries
        if (filteredEntries.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data found for the selected filters.", "No Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (String[] entry : filteredEntries) {
                model.addRow(entry);
                // Accumulate the quantity and total amount
                try {
                    totalQuantity += Integer.parseInt(entry[3]); // Quantity is at index 3
                    totalAmount += Double.parseDouble(entry[5]);  // Total Amount is at index 5
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            // Add a new row for total values at the bottom of the table
            model.addRow(new Object[]{"", "Total", "", totalQuantity, "", totalAmount});
        }
        
    }//GEN-LAST:event_jButtonViewActionPerformed

    private void jbackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbackButtonActionPerformed
        // TODO add your handling code here:
        var SalesManagerDashboard = new SalesManagerDashboard(r);
        SalesManagerDashboard.show();
        this.hide();
    }//GEN-LAST:event_jbackButtonActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        // TODO add your handling code here:
        List<String[]> reportData = new ArrayList<>();
    
        // Iterate over the rows of the table model
        for (int i = 0; i < model.getRowCount(); i++) {
            // Get each row as an array of Strings
            String[] row = new String[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                row[j] = model.getValueAt(i, j).toString();
            }
            // Add the row to the report data list
            reportData.add(row);
        }

        // Use JFileChooser to allow the user to choose the path to save the file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Report As");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

        // Set default file extension to PDF
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Files", "pdf"));

        int userSelection = fileChooser.showSaveDialog(this);

        // Check if user clicked "Save"
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();

            // Ensure the file has the .pdf extension
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.endsWith(".pdf")) {
                filePath += ".pdf";  // Add .pdf extension if not already present
            }
            

            try {
                // Call the export method from SalesReportClass with the chosen file path
                reportGenerator.exportReportToPdf(reportData, filePath);

                JOptionPane.showMessageDialog(this, "Report exported successfully to: " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error exporting report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Export canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButtonExportActionPerformed

    
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
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonView;
    private javax.swing.JComboBox<String> jComboBoxItemCode;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JComboBox<String> jComboBoxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbackButton;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    // End of variables declaration//GEN-END:variables
}

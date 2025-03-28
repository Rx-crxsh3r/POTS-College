/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package IMGUI;
import Classes.Supplier;
import Classes.FilePath;
import com.itextpdf.text.BaseColor;
import java.io.BufferedReader;
import java.io.FileReader;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thebl
 */
public class SupplierGui extends javax.swing.JFrame {
    String role;
    /**
     * Creates new form SupplierGui
     */
    public SupplierGui(String role) {
        this.role = role;
        initComponents();
        loadSupplierData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableSuppliers = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "Supplier Name", "Description", "Status", "Email"
            }
        ));
        jScrollPane1.setViewportView(tableSuppliers);
        if (tableSuppliers.getColumnModel().getColumnCount() > 0) {
            tableSuppliers.getColumnModel().getColumn(4).setResizable(false);
        }

        btnAdd.setText("Add new Supplier");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit Existing Supplier");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete Supplier");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jButton1.setText("Return to menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnExport.setText("Export Data");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(35, 35, 35)
                        .addComponent(btnAdd)
                        .addGap(34, 34, 34)
                        .addComponent(btnEdit)
                        .addGap(31, 31, 31)
                        .addComponent(btnDelete)
                        .addGap(28, 28, 28)
                        .addComponent(btnExport)))
                .addGap(0, 42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void loadSupplierData() {
        // Get the table model from the supplier table
        DefaultTableModel tableModel = (DefaultTableModel) tableSuppliers.getModel();

        // Define the file path for the suppliers.txt file
        String filePath = FilePath.supplierPath;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into columns
                String[] rowData = line.split(",");
                if (rowData.length == 5) { // Ensure the correct number of columns (Supplier ID, Name, Description, Status)
                    // Add the row to the table
                    tableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading supplier data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        AddNewSupplier ANS = new AddNewSupplier();
            //a referesher for the data, same idea as the one in itemgui
            ANS.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) tableSuppliers.getModel();
                tableModel.setRowCount(0);
                loadSupplierData();
            }
        });

        ANS.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // Get the selected supplier ID from the table (assume first column is the supplier ID)
        int selectedRow = tableSuppliers.getSelectedRow();
        if (selectedRow != -1) {
            String supplierId = tableSuppliers.getValueAt(selectedRow, 0).toString();

            // Open the EditSupplier form and pass the supplierId
            EditSupplier editSupplier = new EditSupplier(supplierId);
            
            //a referesher for the data, same idea as the one in itemgui
            editSupplier.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) tableSuppliers.getModel();
                tableModel.setRowCount(0);
                loadSupplierData();
            }
        });
            editSupplier.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a supplier to edit.");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    // Check if a row is selected
    int selectedRow = tableSuppliers.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a supplier to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Get the Supplier ID from the selected row (assuming the first column is the Supplier ID)
    String supplierId = tableSuppliers.getValueAt(selectedRow, 0).toString();

    // Confirm deletion
    int confirmation = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete supplier: " + supplierId + "?", 
            "Delete Supplier", JOptionPane.YES_NO_OPTION);

    if (confirmation == JOptionPane.YES_OPTION) {
        boolean success = Supplier.deleteSupplierFromFile(
                FilePath.supplierPath,
                supplierId);

        if (success) {
            JOptionPane.showMessageDialog(this, "Supplier deleted successfully.");
            ((DefaultTableModel) tableSuppliers.getModel()).removeRow(selectedRow); // Remove row from table
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete the supplier.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Action cancelled.");
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
        InventoryManagerGUI IM = new InventoryManagerGUI(role);
        IM.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void exportTableDataToPdf(DefaultTableModel model, String filePath) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();
        document.add(new Paragraph("Supplier Data Report"));
        document.add(new Paragraph("Generated on: " + java.time.LocalDate.now()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(model.getColumnCount());
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);

        // Add table headers
        for (int i = 0; i < model.getColumnCount(); i++) {
            PdfPCell headerCell = new PdfPCell(new Paragraph(model.getColumnName(i)));
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(headerCell);
        }

        // Add table data
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object value = model.getValueAt(row, col);
                PdfPCell dataCell = new PdfPCell(new Paragraph(value == null ? "" : value.toString()));
                dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(dataCell);
            }
        }

        document.add(table);
        document.close();
}

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Supplier Data as PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".pdf")) {
                filePath += ".pdf"; // Ensure the file has a .pdf extension
            }

            try {
                exportTableDataToPdf((DefaultTableModel) tableSuppliers.getModel(), filePath);
                JOptionPane.showMessageDialog(this, "PDF exported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to export PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnExportActionPerformed

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
            java.util.logging.Logger.getLogger(SupplierGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupplierGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupplierGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupplierGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String role = "";
                new SupplierGui(role).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableSuppliers;
    // End of variables declaration//GEN-END:variables
}

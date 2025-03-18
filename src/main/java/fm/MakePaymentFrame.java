package fm;

import Classes.FilePath;
import Classes.FinanceManager;
import Classes.Payment;
import Classes.purchaseOrder;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JFileChooser;
import java.io.FileOutputStream;

public class MakePaymentFrame extends javax.swing.JFrame {

    private FinanceManager fm;

    public MakePaymentFrame(FinanceManager fm) {
        this.fm = fm;
        initComponents();
        loadApprovedPOs(); // Populate the table with approved POs
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        poTable = new javax.swing.JTable();
        makePaymentButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        poTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PO ID", "Date", "Supplier ID", "Item ID", "Quantity", "Total Amount", "Status"
            }
        ));
        jScrollPane1.setViewportView(poTable);

        makePaymentButton.setText("Make Payment");
        makePaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makePaymentButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Make Payment");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(makePaymentButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(closeButton))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(makePaymentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void makePaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makePaymentButtonActionPerformed

    makePayment(); // Call the makePayment method directly
    }//GEN-LAST:event_makePaymentButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    closeButton.addActionListener(e -> dispose());        // TODO add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed
//private supplier getSupplierInfo(String supplierID) {
//    List<supplier> suppliers = supplier.readSuppliersFromFile(FinanceManager.getSupplierFile());
//    for (supplier s : suppliers) {
//        if (item.getSupplierId().equals(supplierID)) {
//            return s; // Return the supplier object
//        }
//    }
//    return null; // Return null if no supplier found
//}

    /**
     * @param args the command line arguments
     */
    
    private void loadApprovedPOs() {
        // Fetch all POs and filter them to get only approved ones
        List<purchaseOrder> approvedPOs = FinanceManager.viewAllPOs().stream()
                .filter(po -> "Approved".equalsIgnoreCase(po.getPayment_status())) // Filter only "Approved" POs
                .toList();

        DefaultTableModel model = (DefaultTableModel) poTable.getModel();
        model.setRowCount(0); // Clear existing rows

        // Loop through approved POs and add them to the table
        for (purchaseOrder po : approvedPOs) {
            model.addRow(new Object[]{
                po.getPoID(),
                po.getDate(),
                po.getSupplierId(),
                po.getItemCode(),
                po.getQuantityRequired(),
                po.getTotal(),
                po.getPayment_status()
            });
        }
    }

        
        
private void makePayment() {
    int selectedRow = poTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a PO to make payment.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Retrieve selected row details
    String poID = poTable.getValueAt(selectedRow, 0).toString();
    String date = poTable.getValueAt(selectedRow, 1).toString();
    String supplierID = poTable.getValueAt(selectedRow, 2).toString();
    String itemID = poTable.getValueAt(selectedRow, 3).toString();
    int quantity = Integer.parseInt(poTable.getValueAt(selectedRow, 4).toString());
    double total = Double.parseDouble(poTable.getValueAt(selectedRow, 5).toString());
    String status = "Paid"; // Set status to "Paid"
    String deliveryStatus = "In Progress"; // Set delivery status to "In Progress"

    try {
        // Update PO status to "Paid"
        FinanceManager.approveOrRejectPO(poID, status);

        // Save payment details to payment.txt
        String paymentFile = FilePath.paymentPath;
        Payment payment = new Payment(poID, date, supplierID, itemID, quantity, total, status, deliveryStatus);
        Payment.saveToFile(paymentFile, payment);

        // Refresh the table
        loadApprovedPOs();

        // Ask if user wants to print receipt
        int option = JOptionPane.showConfirmDialog(this, "Print Receipt?", "Receipt", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            generateReceipt(poID, date, supplierID, itemID, quantity, total, status, deliveryStatus);
            JOptionPane.showMessageDialog(this, "Receipt has been downloaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}




private void generateReceipt(String poID, String date, String supplierID, String itemID, int quantity, double total, String status, String deliveryStatus) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Receipt As"); // Title for the file chooser dialog
    fileChooser.setSelectedFile(new java.io.File("Receipt_" + poID + ".pdf")); // Default file name

    // Show the Save dialog
    int userSelection = fileChooser.showSaveDialog(this);

    // If the user approves, save the file
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        java.io.File fileToSave = fileChooser.getSelectedFile();

        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
            document.open();

            // Add Company Name
            com.itextpdf.text.Paragraph companyName = new com.itextpdf.text.Paragraph("International Company for Selling Devices");
            companyName.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(companyName);

            // Add Receipt Title
            document.add(new com.itextpdf.text.Paragraph("Payment Receipt"));
            document.add(new com.itextpdf.text.Paragraph(" ")); // Add space

            // Add Payment Details
            document.add(new com.itextpdf.text.Paragraph("Purchase Order ID: " + poID));
            document.add(new com.itextpdf.text.Paragraph("Date: " + date));
            document.add(new com.itextpdf.text.Paragraph("Supplier ID: " + supplierID));
            document.add(new com.itextpdf.text.Paragraph("Item ID: " + itemID));
            document.add(new com.itextpdf.text.Paragraph("Quantity: " + quantity));
            document.add(new com.itextpdf.text.Paragraph("Total Amount: $" + total));
            document.add(new com.itextpdf.text.Paragraph("Payment Status: " + status));
            document.add(new com.itextpdf.text.Paragraph("Delivery Status: " + deliveryStatus));

            // Add Thank You Note
            document.add(new com.itextpdf.text.Paragraph(" "));
            document.add(new com.itextpdf.text.Paragraph("Thank you for your business!"));

            document.close();
            JOptionPane.showMessageDialog(this, "Receipt has been saved to: " + fileToSave.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to generate receipt: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Receipt generation canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}


    
    
        
         
        
        
    public static void main(String[] args) {
        String poFile = FilePath.posPath;
        String stockFile = FilePath.itemPath ;
        String supplierFile = FilePath.supplierPath;

        FinanceManager fm = new FinanceManager(poFile, stockFile, supplierFile);
        java.awt.EventQueue.invokeLater(() -> new MakePaymentFrame(fm).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton makePaymentButton;
    private javax.swing.JTable poTable;
    // End of variables declaration//GEN-END:variables
}

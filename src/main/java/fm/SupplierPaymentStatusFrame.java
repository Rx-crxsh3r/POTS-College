package fm;
import Classes.FilePath;
import Classes.purchaseOrder;
import Classes.FinanceManager;
import static Classes.FinanceManager.viewAllPOs;
import Classes.Supplier;
import Classes.Payment;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import Classes.Supplier;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierPaymentStatusFrame extends javax.swing.JFrame {

    private FinanceManager fm;

    public SupplierPaymentStatusFrame(FinanceManager fm) {
        this.fm = fm;
        initComponents();
        loadSupplierData();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        paymentHistoryButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Supplier ID", "Name", "Contact Info", "PO IDs with Status", "Payment Status"
            }
        ));
        jScrollPane1.setViewportView(supplierTable);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("View Supplier Payment Status");

        paymentHistoryButton.setText("Payment History");
        paymentHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentHistoryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(closeButton)
                        .addGap(374, 374, 374)
                        .addComponent(paymentHistoryButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(182, 182, 182))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(paymentHistoryButton))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    closeButton.addActionListener(e -> dispose());
    }//GEN-LAST:event_closeButtonActionPerformed

    private void paymentHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentHistoryButtonActionPerformed
         JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Payment History As"); // Set dialog title
    fileChooser.setSelectedFile(new java.io.File("Payment_History.pdf")); // Default file name

    int userSelection = fileChooser.showSaveDialog(this);
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

            // Add Title
            com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph("Payment History Summary");
            title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(title);
            document.add(new com.itextpdf.text.Paragraph(" ")); // Add space

            // Fetch Paid Payments
            List<Payment> payments = Payment.readPaymentsFromFile();
            List<Payment> paidPayments = payments.stream()
                    .filter(payment -> "Paid".equalsIgnoreCase(payment.getPaymentStatus()))
                    .collect(Collectors.toList());

            // Add Table Header
            document.add(new com.itextpdf.text.Paragraph("Paid Payments:"));
            document.add(new com.itextpdf.text.Paragraph(" ")); // Add space

            for (Payment payment : paidPayments) {
                document.add(new com.itextpdf.text.Paragraph("PO ID: " + payment.getPoID()));
                document.add(new com.itextpdf.text.Paragraph("Supplier ID: " + payment.getSupplierId()));
                document.add(new com.itextpdf.text.Paragraph("Item ID: " + payment.getItemCode()));
                document.add(new com.itextpdf.text.Paragraph("Total: $" + payment.getTotal()));
                document.add(new com.itextpdf.text.Paragraph("Payment Status: " + payment.getPaymentStatus()));
                document.add(new com.itextpdf.text.Paragraph("Delivery Status: " + payment.getDeliveryStatus()));
                document.add(new com.itextpdf.text.Paragraph(" "));
            }

            document.close();
            JOptionPane.showMessageDialog(this, "Payment history saved to: " + fileToSave.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to generate payment history: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Payment history generation canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }   // TODO add your handling code here:
    }//GEN-LAST:event_paymentHistoryButtonActionPerformed

    
    
    
    

    
    
private void loadSupplierData() {
    // Fetch all suppliers
    List<Supplier> suppliers = Supplier.readSuppliersFromFile();
    // Fetch all payments
    List<Payment> payments = Payment.readPaymentsFromFile();

    // Get the table's model
    DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
    model.setRowCount(0); // Clear existing rows

    // Add suppliers and their related payment details to the table
    for (Supplier supplier : suppliers) {
        // Get all payments related to this supplier
        List<Payment> relatedPayments = payments.stream()
                .filter(payment -> payment.getSupplierId().equalsIgnoreCase(supplier.getSupplierId()))
                .collect(Collectors.toList());

        // Generate PO IDs with Status
        String poIDsWithStatus = relatedPayments.stream()
                .map(payment -> payment.getPoID() + " (" + payment.getPaymentStatus() + ")")
                .collect(Collectors.joining(", "));

        // Determine Payment Status
        String paymentStatus;
        if (relatedPayments.isEmpty()) {
            paymentStatus = "No POs";
        } else if (relatedPayments.stream().allMatch(payment -> payment.getPaymentStatus().equalsIgnoreCase("Paid"))) {
            paymentStatus = "Completed";
        } else {
            paymentStatus = "In-Process";
        }

        // Add supplier data and related payments to the table
        model.addRow(new Object[]{
            supplier.getSupplierId(),
            supplier.getSupplierName(),
            supplier.getSupplierEmail(),
            poIDsWithStatus.isEmpty() ? "No POs" : poIDsWithStatus,
            paymentStatus
        });
    }
}

    
private List<Supplier> viewSupplierPaymentStatus() {
    // Read supplier data from file (assuming 'supplier.readSuppliersFromFile' exists)
    return Supplier.readSuppliersFromFile();
}



    public static void main(String[] args) {
        String poFile = FilePath.posPath;
        String stockFile = FilePath.itemPath;
        String supplierFile = FilePath.supplierPath;

        FinanceManager fm = new FinanceManager(poFile, stockFile, supplierFile);
        java.awt.EventQueue.invokeLater(() -> new SupplierPaymentStatusFrame(fm).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton paymentHistoryButton;
    private javax.swing.JTable supplierTable;
    // End of variables declaration//GEN-END:variables

//    private List<supplier> viewSupplierPaymentStatus() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    private void equalsIgnoreCase(String supplierID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}

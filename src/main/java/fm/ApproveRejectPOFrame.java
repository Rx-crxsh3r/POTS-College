package fm;

import Classes.FilePath;
import Classes.FinanceManager;
import Classes.purchaseOrder;
import javax.swing.JOptionPane;

public class ApproveRejectPOFrame extends javax.swing.JFrame {

    private FinanceManager fm; // Add a reference to FinanceManager

    // Constructor that accepts FinanceManager
    public ApproveRejectPOFrame(FinanceManager fm) {
        this.fm = fm;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        poIDTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("PO ID:");

        jLabel2.setText("Select Status:");

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Approved", "Rejected" }));

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Approve/Reject PO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addGap(52, 52, 52)
                        .addComponent(submitButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(poIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(poIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(submitButton))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
    String poID = poIDTextField.getText().trim();
    String status = (String) statusComboBox.getSelectedItem();

    // Validate input
    if (poID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a valid PO ID.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (status == null || status.equalsIgnoreCase("Select")) {
        JOptionPane.showMessageDialog(this, "Please select either 'Approved' or 'Rejected' as the status.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Ensure the status is only "Approved" or "Rejected"
    if (!status.equalsIgnoreCase("Approved") && !status.equalsIgnoreCase("Rejected")) {
        JOptionPane.showMessageDialog(this, "Invalid status selected. Only 'Approved' or 'Rejected' are allowed.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Update PO status
        FinanceManager.approveOrRejectPO(poID, status);
        JOptionPane.showMessageDialog(this, "PO status updated successfully to: " + status, "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    cancelButton.addActionListener(e -> dispose());
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Main method for testing purposes
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ApproveRejectPOFrame(new FinanceManager(
                FilePath.posPath,
                FilePath.itemPath,
                FilePath.supplierPath
            )).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField poIDTextField;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}

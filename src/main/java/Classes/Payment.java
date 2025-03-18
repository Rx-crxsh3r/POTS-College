package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Payment class inherits from purchaseOrder
// Inheritance is used to extend the functionality of purchaseOrder 
// to include additional fields and methods specific to payments.
public class Payment extends purchaseOrder {
    private String deliveryStatus; // Additional field for delivery status
    private String paymentStatus; // Additional field for payment status

    // Constructor
    // The constructor calls the parent class constructor using 'super' to initialize common fields
    // and initializes the additional fields specific to Payment.
    public Payment(String poID, String date, String supplierId, String itemCode, int quantityRequired, double total,
                   String paymentStatus, String deliveryStatus) {
        super(poID, date, supplierId, itemCode, quantityRequired, total, paymentStatus); // Call parent class constructor
        this.deliveryStatus = deliveryStatus; // Initialize delivery status
        this.paymentStatus = paymentStatus; // Initialize payment status
    }

    // Getters and Setters for additional fields
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Save payment details to a file
    // Demonstrates encapsulation by saving all payment details in a structured way
    public static void saveToFile(String filePath, Payment payment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(payment.toString()); // Use overridden toString to format data
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read payment details from a file
    // Reads all payment entries from the payment.txt file and returns them as a list of Payment objects
    public static List<Payment> readPaymentsFromFile() {
        String filePath = FilePath.paymentPath;
        List<Payment> payments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) { // Adjusted for new deliveryStatus field
                    String poID = data[0];
                    String date = data[1];
                    String supplierId = data[2];
                    String itemCode = data[3];
                    int quantityRequired = Integer.parseInt(data[4]);
                    double total = Double.parseDouble(data[5]);
                    String paymentStatus = data[6];
                    String deliveryStatus = data[7];
                    payments.add(new Payment(poID, date, supplierId, itemCode, quantityRequired, total, paymentStatus, deliveryStatus));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return payments;
    }

    // Override toString for saving to file
    // Combines inherited fields with new fields to provide a complete representation of a Payment
    @Override
    public String toString() {
        return getPoID() + "," + getDate() + "," + getSupplierId() + "," +
               getItemCode() + "," + getQuantityRequired() + "," + getTotal() + "," +
               paymentStatus + "," + deliveryStatus;
    }
}

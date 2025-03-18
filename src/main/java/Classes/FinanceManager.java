package Classes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager extends User{
    private static String poFile;
    private static String stockFile;
    private static String supplierFile;

    // Constructor to initialize file paths
    public FinanceManager(String poFile, String stockFile, String supplierFile) {
        this.poFile = poFile;
        this.stockFile = stockFile;
        this.supplierFile = supplierFile;
    }

    // Getter for poFile
    public String getPoFile() {
        return poFile;
    }

    // Getter for stockFile
    public String getStockFile() {
        return stockFile;
    }

    // Getter for supplierFile
    public static String getSupplierFile() {
        return supplierFile;
    }

    // Method to return a list of all POs
    public static List<purchaseOrder> viewAllPOs() {
        poFile = FilePath.posPath;
        return purchaseOrder.readPOsFromFile(poFile); // Returns the list of POs from the file
    }

    // Method to approve or reject a PO
    public static void approveOrRejectPO(String poID, String status) throws IOException {
        if (status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Rejected") || status.equalsIgnoreCase("Paid")) {
            purchaseOrder.updatePOStatus(poFile, poID, status);
        } else {
            System.out.println("Invalid status. Use 'Approved', 'Rejected', or 'Paid'.");
        }
    }

    // Method to check stock status of a specific item
    public static  List<item> checkStockStatus(String itemID) {
        stockFile = FilePath.itemPath;
        List<item> stockList = item.readStockFromFile(stockFile); // Get stock data from file
        List<item> result = new ArrayList<>();
        
        for (item item : stockList) {
            if (item.getItemCode().equalsIgnoreCase(itemID)) {
                result.add(item); // Add matching item to the result list
            }
        }

        return result; // Return the list of matching stock items
    }

    // Updated method to view all supplier payment statuses
    
}

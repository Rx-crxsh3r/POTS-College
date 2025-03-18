/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */

public class item extends BaseItem {
    public item() {
        super();
    }

    public item(String itemName) {
        super();
        this.itemName = itemName;
    }

    public item(String id, String supplierID) {
        super();
        this.itemCode = id;
        this.supplierId = supplierID;
    }

    public item(String itemID, String itemName, int quantity) {
        super();
        this.itemCode = itemID;
        this.itemName = itemName;
        this.quantityInStock = quantity;
    }

    public item(String itemCode, String itemName, String supplierId, String description, String category, 
                double price, int quantityInStock, int reorderLevel, String unit, String status) {
        super(itemCode, itemName, supplierId, description, category, price, quantityInStock, reorderLevel, unit, status);
    }

    public item(String itemCode, String itemName, String supplierId, String description, String category, double price,
                int quantityInStock, int reorderLevel, String unit, String dateAdded, String status) {
        super(itemCode, itemName, supplierId, description, category, price, quantityInStock, reorderLevel, unit, dateAdded, status);
    }
    
    public String getUnit() {
        return unitOfMeasurement;
    }

    

    public static Set<String> loadItemsAndCategories(String filePath, List<Classes.item> items) {
       Set<String> uniqueCategories = new HashSet<>();
       try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
           String line;
           while ((line = reader.readLine()) != null) {

               line = line.trim();
               if (line.isEmpty()) continue;

               String[] parts = line.split(",");  

               if (parts.length == 10 || parts.length == 11) {  
                   try {
                       String itemCode = parts[0];
                       String itemName = parts[1];
                       String supplierId = parts[2];
                       String description = parts[3];
                       String category = parts[4];  
                       double price = Double.parseDouble(parts[5]);
                       int quantityInStock = Integer.parseInt(parts[6]);
                       int reorderLevel = Integer.parseInt(parts[7]);
                       String unit = parts[8];
                       String status = parts[9];
                       String dateAdded = parts.length == 11 ? parts[9] : ""; 

                       Classes.item item = new Classes.item(itemCode, itemName, supplierId, description, category, price,
                                            quantityInStock, reorderLevel, unit, dateAdded, status);
                       items.add(item);

                       if (category != null && !category.isEmpty()) {
                           uniqueCategories.add(category);
                       }
                   } catch (NumberFormatException e) {
                   }
               } else {
                   JOptionPane.showMessageDialog(null,"Skipping malformed line (incorrect number of parts): " + line); 
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return uniqueCategories;
    }






    public static List<Classes.item> filterByCategory(List<Classes.item> items, String category) {
        List<Classes.item> filteredItems = new ArrayList<>();
        for (Classes.item item : items) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
    
    @Override
    public String toString() {
        return itemCode + "," + itemName + "," + quantityInStock;
    }

public static List<item> readStockFromFile(String filename) {
    List<item> stockList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            String[] details = line.split(",");
            if (details.length >= 10) { // Ensure the line has all necessary fields
                String itemCode = details[0];
                String itemName = details[1];
                String supplierId = details[2];
                String description = details[3];
                String category = details[4];
                double price = Double.parseDouble(details[5]);
                int quantityInStock = Integer.parseInt(details[6]);
                int reorderLevel = Integer.parseInt(details[7]);
                String unitOfMeasurement = details[8];
                String status = details[9];
                String dateAdded = details.length > 10 ? details[10] : "Not Available";

                // Create a new item object with all fields
                item item = new item(itemCode, itemName, supplierId, description, category, price,
                        quantityInStock, reorderLevel, unitOfMeasurement, dateAdded, status);
                stockList.add(item);
            } else {
                System.err.println("Malformed line in file: " + line);
            }
        }
    } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
    }
    return stockList;
}

public boolean saveToFile(String filePath) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
        String data = String.join(",",
                itemCode, itemName, supplierId, description, category,
                String.valueOf(price), String.valueOf(quantityInStock),
                String.valueOf(reorderLevel), unitOfMeasurement, dateAdded, status);
        bw.write(data);
        bw.newLine();
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean updateFile(String filePath) {
    StringBuilder fileContent = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        boolean itemUpdated = false;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals(itemCode)) {  // Check if this is the item to update
                // Update the item data
                String updatedData = String.join(",", itemCode, itemName, supplierId, description, category,
                        String.valueOf(price), String.valueOf(quantityInStock),
                        String.valueOf(reorderLevel), unitOfMeasurement, dateAdded, status);
                fileContent.append(updatedData).append("\n");
                itemUpdated = true;
            } else {
                // Keep the original data if it's not the item to update
                fileContent.append(line).append("\n");
            }
        }

        // If the item was found and updated, overwrite the file
        if (itemUpdated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(fileContent.toString());
            }
            return true;
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return false;
}
    public static boolean deleteItemFromFile(String itemCode) {
        String filePath = FilePath.itemPath;
        StringBuilder fileContent = new StringBuilder();
        boolean itemDeleted = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(itemCode)) {
                    fileContent.append(line).append("\n");  // Append items that are not the one to delete
                } else {
                    itemDeleted = true;  // Mark item as deleted if it matches the itemCode
                }
            }

            // If the item was found and deleted, overwrite the file
            if (itemDeleted) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(fileContent.toString());
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;  // Return false if the item was not found
        
    }
    public static boolean updateStockInFile(String filePath, String itemCode, int quantityReceived) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean itemFound = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(itemCode)) {
                    // Item found, update the stock level
                    int currentStock = Integer.parseInt(data[6].trim()); // Assuming data[6] is the stock quantity
                    int newStockLevel = currentStock + quantityReceived;
                    data[6] = String.valueOf(newStockLevel); // Update the stock in the data array
                    itemFound = true;
                }

                // Append the updated line (or unchanged) to the file content
                fileContent.append(String.join(",", data));
                fileContent.append(System.lineSeparator()); // Ensure new line after each record
            }

            if (itemFound) {
                // Write the updated content back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(fileContent.toString());
                }

                return true; // Successfully updated
            } else {
                JOptionPane.showMessageDialog(null, "Item with code " + itemCode + " not found.");
                return false;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating stock: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static List<String> checkLowStock(String filePath) {
        List<String> lowStockItems = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 11) { // Ensure the line has the expected number of columns
                    // Trim spaces from data elements
                    String itemCode = data[0].trim();
                    String itemName = data[1].trim();
                    String quantityInStockStr = data[6].trim();
                    String reorderLevelStr = data[7].trim();

                    try {
                        int quantityInStock = Integer.parseInt(quantityInStockStr);
                        int reorderLevel = Integer.parseInt(reorderLevelStr);

                        // Debugging: print the values
                        System.out.println("Item: " + itemName + ", Quantity In Stock: " + quantityInStock + ", Reorder Level: " + reorderLevel);

                        // If quantity in stock is less than or equal to reorder level, add to low stock list
                        if (quantityInStock <= reorderLevel) {
                            lowStockItems.add(itemName + " (Code: " + itemCode + ")");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing numbers: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lowStockItems;
    }
    
    public static String generateNextItemID(String filePath) {
        String nextlasItemId = "ITM001"; // Default ID if the file is empty

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String lastLine = null, currentLine;

            // Read through the file to find the last line
            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }

            if (lastLine != null) {
                String[] data = lastLine.split(",");
                if (data.length > 0) {
                    String lasItemId = data[0].trim();
                    if (lasItemId.startsWith("ITM")) {
                        // Extract the numeric part of the last ID
                        int lastNumericId = Integer.parseInt(lasItemId.substring(3));
                        // Increment and format the new ID
                        nextlasItemId = String.format("ITM%03d", lastNumericId + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextlasItemId;
    }
    
    
    

    
    public static boolean updateStockAndDeliveryStatus(String itemsFilePath, String paymentsFilePath, String itemCode) throws IOException {
        boolean paymentFound = false;

        // Read and update payments file
        List<String> updatedPayments = new ArrayList<>();
        try (BufferedReader paymentReader = new BufferedReader(new FileReader(paymentsFilePath))) {
            String line;
            while ((line = paymentReader.readLine()) != null) {
                String[] paymentData = line.split(",");
                if (paymentData.length > 2 && paymentData[3].trim().equals(itemCode) && 
                        "In Progress".equalsIgnoreCase(paymentData[7].trim())) {
                    int boughtStock = Integer.parseInt(paymentData[4].trim());
                    paymentData[7] = "Arrived"; // Update delivery status
                    paymentFound = true;

                    // Update stock in the items file
                    updateItemStock(itemsFilePath, itemCode, boughtStock);
                }
                updatedPayments.add(String.join(",", paymentData));
            }
        }

        // Write updated payments back to file
        try (BufferedWriter paymentWriter = new BufferedWriter(new FileWriter(paymentsFilePath))) {
            for (String updatedPayment : updatedPayments) {
                paymentWriter.write(updatedPayment);
                paymentWriter.newLine();
            }
        }

        return paymentFound;
    }

    public static void updateItemStock(String itemsFilePath, String itemCode, int boughtStock) throws IOException {
        List<String> updatedItems = new ArrayList<>();
        try (BufferedReader itemReader = new BufferedReader(new FileReader(itemsFilePath))) {
            String line;
            while ((line = itemReader.readLine()) != null) {
                String[] itemData = line.split(",");
                if (itemData[0].trim().equals(itemCode)) {
                    int currentStock = Integer.parseInt(itemData[6].trim());
                    currentStock += boughtStock; // Add bought stock to current stock
                    itemData[6] = String.valueOf(currentStock); // Update stock level
                }
                updatedItems.add(String.join(",", itemData));
            }
        }

        // Write updated items back to file
        try (BufferedWriter itemWriter = new BufferedWriter(new FileWriter(itemsFilePath))) {
            for (String updatedItem : updatedItems) {
                itemWriter.write(updatedItem);
                itemWriter.newLine();
            }
        }
    }
   
    }
    


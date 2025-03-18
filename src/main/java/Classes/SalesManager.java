/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SalesManager extends User{
    String filePath;
    private List<SalesEntry> salesList;

    public SalesManager(String filePath) {
        this.filePath = filePath;
        salesList = new ArrayList<>();
        loadSalesData();
    }

    // Default constructor
    public SalesManager() {
        salesList = new ArrayList<>();
        loadSalesData();
    }

    public void addSalesEntry(SalesEntry entry) {
        salesList.add(entry);
    }

    public void editSalesEntry(int index, SalesEntry updatedEntry) {
        if (index >= 0 && index < salesList.size()) {
            salesList.set(index, updatedEntry);
        }
    }

    public void deleteSalesEntry(int index) {
        if (index >= 0 && index < salesList.size()) {
            salesList.remove(index);
        }
    }

    public List<SalesEntry> getSalesEntries() {
        return salesList;
    }

    public boolean saveSalesData(String itemCode, int quantitySold) {
        File inputFile = new File(FilePath.itemPath);
        File tempFile = new File(Paths.get(System.getProperty("user.dir"), "src", "main", "java", "TextFiles", "tempItem.txt").toString());

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean isUpdated = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(itemCode)) {
                    int quantityInStock = Integer.parseInt(data[6]);
                    int newQuantity = quantityInStock - quantitySold;

                    if (newQuantity < 0) {
                        JOptionPane.showMessageDialog(null, 
                            "Not enough stock for item " + itemCode, 
                            "Stock Error", 
                            JOptionPane.ERROR_MESSAGE);
                        return false;
                    }

                    // Update stock quantity
                    data[6] = String.valueOf(newQuantity);
                    isUpdated = true;
                }

                // Write the updated or original line to the temp file
                writer.write(String.join(",", data));
                writer.newLine();
            }

            if (!isUpdated) {
                JOptionPane.showMessageDialog(null, 
                    "Item code " + itemCode + " not found in item.txt", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                "An error occurred while updating stock: " + e.getMessage(), 
                "File Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Replace original file with the updated temp file
        try {
            Files.deleteIfExists(inputFile.toPath());
            Files.move(tempFile.toPath(), inputFile.toPath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Error replacing original file: " + e.getMessage(), 
                "File Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    
    public String getItemCategory(String itemCode) {
    try (BufferedReader reader = new BufferedReader(new FileReader(FilePath.itemPath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",\\s*");
            if (parts[0].equals(itemCode)) {
                return parts[4].trim();
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error reading item file: " + e.getMessage());
    }
    return "Unknown";
    }
    
    public String getItemName(String itemCode) {
    try (BufferedReader reader = new BufferedReader(new FileReader(FilePath.itemPath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", ");
            if (parts[0].equals(itemCode)) {
                return parts[1];
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error reading item file: " + e.getMessage());
    }
    return null;
    }



    // Get the unit price for an item code
    public double getUnitPrice(String itemCode) {
        try (BufferedReader readItem = new BufferedReader(new FileReader(FilePath.itemPath))) {
            String line;
            while ((line = readItem.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(itemCode)) {
                    return Double.parseDouble(parts[5]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error reading item file: " + e.getMessage());
        }
        return -1;
    }
    
    public boolean saveSalesEntriesToFile(List<SalesEntry> salesEntries) {
        File salesFile = new File(FilePath.salesPath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(salesFile, true))) { // 'true' appends to the file
            for (SalesEntry entry : salesEntries) {
                String entryString = entry.toString();  
                writer.write(entryString);  
                writer.newLine();  
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<SalesEntry> loadSalesEntriesFromFile() {
        List<SalesEntry> salesEntries = new ArrayList<>();
        File salesFile = new File(FilePath.salesPath);

        try (BufferedReader reader = new BufferedReader(new FileReader(salesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                SalesEntry entry = SalesEntry.fromString(line);
                salesEntries.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return salesEntries;
    }


    private void loadSalesData() {
        try (BufferedReader Entry = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = Entry.readLine()) != null) {
                // Handle invalid data gracefully (try-catch in case of malformed lines)
                try {
                    salesList.add(SalesEntry.fromString(line));
                } catch (Exception e) {
                    System.out.println("Skipping invalid sales entry: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"No existing sales data found or unable to read sales file");
        }
    }
}

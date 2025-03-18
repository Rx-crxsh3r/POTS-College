/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmed
 */
public class Request extends item{

    
    private String requestID;
    protected int quantityRequired;
    private String requestedBy;
    private String dateOfrequest;
    private String status;
    private static final String ITEM_FILE =  FilePath.itemPath;
    private static final String REQUISITION_FILE = FilePath.requestPath;
    
    
    public Request(){
        
    }
     public Request(String id){
        this.requestID = id;
    }
      public Request(String itemID, String supplierID, int quantityRequired, String dateOfRequest) {
        super(itemID,supplierID); // Set fields inherited from item
        this.quantityRequired = quantityRequired;
        this.dateOfrequest= dateOfRequest;
        
    }
      public Request(String itemID, String supplierID, int quantityRequired, String dateOfRequest, String role) {
        super(itemID,supplierID); // Set fields inherited from item
        this.quantityRequired = quantityRequired;
        this.dateOfrequest= dateOfRequest;
        this.requestedBy = role;
        
    }
      
      

    
     public String generateNextId() {
        String nextPOId = "REQ001"; // Default ID if the file is empty
        String filePath = FilePath.requestPath;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String lastLine = null, currentLine;

            // Read through the file to find the last line
            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }

            if (lastLine != null) {
                String[] data = lastLine.split(",");
                if (data.length > 0) {
                    String lastPOId = data[0].trim();
                    if (lastPOId.startsWith("REQ")) {
                        // Extract the numeric part of the last ID
                        int lastNumericId = Integer.parseInt(lastPOId.substring(3));
                        // Increment and format the new ID
                        nextPOId = String.format("REQ%03d", lastNumericId + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextPOId;
    }

    public DefaultTableModel display(){
        String filePath = FilePath.requestPath;
        // Define column names
        String[] columnNames = {"RequestID","ItemID","SupplierID","QuantityRequired","RequestedBy","Date","Status"};
        // Create a table model with column names but no data yet
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        try{
        FileReader filereader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(filereader);
        String line;
        while((line = reader.readLine()) != null){
            String[] rowData = line.split(",");
            if(rowData[6].equals("Pending")){
                String[] selectedData = { 
                    rowData[0],  // requestID
                    rowData[1],  // itemID
                    rowData[2],  // supplierID
                    rowData[3],  // quantityRequierd
                    rowData[4],  // requestedBy
                    rowData[5],  // Date
                    rowData[6]// status
                };
             // Add the row to the table model
                model.addRow(selectedData);
            }
            
                
               
            
        }
        
        }
        catch(IOException e){
            e.printStackTrace();
        }
       return model; 
    }
    
    

    
    public boolean approveRequest() {
        String filePath = FilePath.requestPath;
        List<String> fileContent = new ArrayList<>();
        String newStatus = "Approved";
        boolean done = false;

        // Read file line by line and store each line in fileContent list
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");

                // Check if this is the row to modify based on requestID
                if (rowData[0].equals(requestID)) {
                    rowData[6] = newStatus;  // Update the 'status' column to 'Approved'
                    rowData[7] = "true";
                    done = true;

                    // Recreate the modified line
                    line = String.join(",", rowData);
                }

                fileContent.add(line);  // Add the (modified or unmodified) line to fileContent
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Return false if an error occurs during reading
        }

        // Write the modified content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : fileContent) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Return false if an error occurs during writing
        }

        return done;
    }
   public boolean rejectRequest() {
        String filePath = FilePath.requestPath;
        List<String> fileContent = new ArrayList<>();
        String newStatus = "Rejected";
        boolean done = false;

        // Read file line by line and store each line in fileContent list
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");

                // Check if this is the row to modify based on requestID
                if (rowData[0].equals(requestID)) {
                    rowData[6] = newStatus;  // Update the 'status' column to 'Approved'
                    done = true;

                    // Recreate the modified line
                    line = String.join(",", rowData);
                }

                fileContent.add(line);  // Add the (modified or unmodified) line to fileContent
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Return false if an error occurs during reading
        }

        // Write the modified content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : fileContent) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Return false if an error occurs during writing
        }

        return done;
    }
       public List<item> loadItems() {
        List<item> itemsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 11) {
                    item item = new item(
                        parts[0],  // itemCode
                        parts[1],  // itemName
                        parts[2],  // supplierId
                        parts[3],  // description
                        parts[4],  // category
                        Double.parseDouble(parts[5]),  // price
                        Integer.parseInt(parts[6]),  // quantityInStock
                        Integer.parseInt(parts[7]),  // reorderLevel
                        parts[8],  // unit
                        parts[9],  // date
                        parts[10]  // status
                    );
                    itemsList.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemsList;
    }

    public void create(){
        
        String role = requestedBy;
        
        String filePath = FilePath.requestPath;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            if(role.equals("Purchase Manager")){
                String nwline = String.format("%s,%s,%s,%d,%s,%s,Approved,false",generateNextId(),itemCode,supplierId,quantityRequired,requestedBy,dateOfrequest);
                writer.write(nwline); // Write the new content
                writer.newLine(); // Add a new line
                JOptionPane.showMessageDialog(null,"Added succsfuly");
            }else{
                JOptionPane.showMessageDialog(null,"Something happend!!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    



    public void create(String itemCode, String supplierId, int quantityRequired, String requestedBy) {
        item itemToRequest = null;
        for (item item : loadItems()) {
            if (item.getItemCode().equals(itemCode)) {
                itemToRequest = item;
                break;
            }
        }

        if (itemToRequest != null) {
            if (itemToRequest.getQuantityInStock() < itemToRequest.getReorderLevel()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(REQUISITION_FILE, true))) {
                    String requestId = generateNextId();
                    String date = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());
                    String status = "Pending";
                    String convertedToPO = "false";

                    writer.write(requestId + "," + itemCode + "," + supplierId + "," + quantityRequired + "," + requestedBy + "," + date + "," + status + "," + convertedToPO);
                    writer.newLine();
                    System.out.println("Requisition created successfully!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Cannot request this item as the quantity in stock is above the reorder level.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }


    public List<item> filterItemsBelowReorderLevel(List<item> items) {
        List<item> filteredItems = new ArrayList<>();
        for (item item : items) {
            if (item.getQuantityInStock() < item.getReorderLevel()) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
}

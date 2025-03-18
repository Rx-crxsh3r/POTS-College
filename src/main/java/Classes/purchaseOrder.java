/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class purchaseOrder extends Request {
    private String poID;
    private double total;
    private String payment_status;
    private String date;
    
 
    public purchaseOrder(){
    }
    
    public purchaseOrder(String poid,String date,String supplierID, String itemID,int quantity){
        
        this.poID = poid;
        this.date = date;
        super.itemCode = itemID;
        this.supplierId = supplierID;
        super.quantityRequired = quantity;
        
        
        
    
    }
     public purchaseOrder(String poID, String date, String supplierID, String itemID, int quantity, double totalAmount, String status) {
        this.poID = poID;
        this.date = date;
        super.itemCode = itemID;
        super.supplierId = supplierID;
        super.quantityRequired = quantity;
        this.total = totalAmount;
        this.payment_status = status;
        
    }
    
   
     
     

    public purchaseOrder(String poID) {
        this.poID = poID;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    /**
     *
     * @return
     */
    @Override
    public String getSupplierId() {
        return supplierId;
    }

    

 
    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public void setId(String id) {
        this.itemCode = id;
    }

    public void setSupplierID(String supplierID) {
        this.supplierId = supplierID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotal(double total) {
        this.total = total;
    }
     public String getPoID() { return poID; }

    public String getDate() {
        return date;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    public String getItemCode() {
        return itemCode;
    }

    public double getTotal() {
        return total;
    }

    public String getPayment_status() {
        return payment_status;
    }
     
     
    
    @Override
    public String toString() {
        return poID + "," + date + "," + supplierId + "," + itemCode + "," + quantityRequired + "," + total + "," + payment_status;
    }
    
    @Override
     public  String generateNextId() {
        String nextPOId = "PO001"; // Default ID if the file is empty
        String filePath =FilePath.posPath;
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
                    if (lastPOId.startsWith("PO")) {
                        // Extract the numeric part of the last ID
                        int lastNumericId = Integer.parseInt(lastPOId.substring(3));
                        // Increment and format the new ID
                        nextPOId = String.format("PO%03d", lastNumericId + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextPOId;
    }
        
    
    @Override
     public DefaultTableModel display(){
        String filePath =FilePath.posPath;
        
        // Define column names
        String[] columnNames = {"POId","Date","supplierID","itemID","Quantity","Total","Status"};
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
                    rowData[0],  
                    rowData[1],  
                    rowData[2],  
                    rowData[3],  
                    rowData[4],  
                    rowData[5],
                    rowData[6]
                   
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
     public DefaultTableModel displayAll(){
        String filePath =FilePath.posPath;
        // Define column names
        String[] columnNames = {"POId","Date","supplierID","itemID","Quantity","Total","Status"};
        // Create a table model with column names but no data yet
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        try{
        FileReader filereader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(filereader);
        String line;
        while((line = reader.readLine()) != null){
            String[] rowData = line.split(",");
            
                String[] selectedData = { 
                    rowData[0],  
                    rowData[1],  
                    rowData[2],  
                    rowData[3],  
                    rowData[4],  
                    rowData[5],
                    rowData[6]
                   
                };
             // Add the row to the table model
                model.addRow(selectedData);
            
            
            
        }
        
        }
        catch(IOException e){
            e.printStackTrace();
        }
       return model; 
    }
    public int edit() {
    List<String> fileContent = new ArrayList<>();
    String filePath = FilePath.posPath;
    boolean isUpdated = false; // To track if an update was made

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;

        // Read all lines from the file
        while ((line = reader.readLine()) != null) {
            String[] rowData = line.split(",");

            // Check if the row matches the ID to be updated
            if (rowData[0].equals(poID)) {
              
                // Validate supplierID and itemID in the items file
                String itemsFilePath = FilePath.itemPath;
                try (BufferedReader itemsReader = new BufferedReader(new FileReader(itemsFilePath))) {
                    String itemsLine;
                    boolean isValid = false;

                    while ((itemsLine = itemsReader.readLine()) != null) {
                        String[] itemData = itemsLine.split(",");
                        if (itemData[0].equals(itemCode) && itemData[2].equals(supplierId)) {
                            rowData[2] = supplierId; // Update supplierID
                           
                            rowData[3] = itemCode;
                            rowData[1] = date; // Update date
                            rowData[4] = String.valueOf(quantityRequired); // Update quantity
                            rowData[5] = String.valueOf(Double.parseDouble(itemData[5]) * quantityRequired) ;
                            

                            isValid = true;
                            break;
                        }
                    }

                    if (!isValid) {
                        return 0; // ItemID and SupplierID validation failed
                    }
                }

                isUpdated = true; // Mark as updated
            }

            // Rebuild the line and add it to the content list
            fileContent.add(String.join(",", rowData));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Overwrite the file with the updated content
    if (isUpdated) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : fileContent) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1; // Successfully updated
    }

    return 0; // No update made
}
    
    
    @Override
public void create() {
    String requestsFilePath = FilePath.requestPath;
    String itemsFilePath = FilePath.itemPath;
    String poFilePath = FilePath.posPath;

    List<String> updatedRequests = new ArrayList<>();

    try (BufferedReader requestReader = new BufferedReader(new FileReader(requestsFilePath))) {
        String line;
        boolean isUpdated = false;

        while ((line = requestReader.readLine()) != null) {
            String[] rowData = line.split(",");

            if (rowData.length > 7 && rowData[6].equals("Approved") && rowData[7].equals("false")) {
                setId(rowData[1]);
                setSupplierID(rowData[2]);
                setQuantityRequired(Integer.parseInt(rowData[3]));
                setDate(rowData[5]);

                rowData[7] = "true"; // Mark as processed
                isUpdated = true;

                // Calculate total price
                try (BufferedReader itemsReader = new BufferedReader(new FileReader(itemsFilePath))) {
                    String itemsLine;

                    while ((itemsLine = itemsReader.readLine()) != null) {
                        String[] itemData = itemsLine.split(",");
                        if (itemData[0].equals(rowData[1]) && itemData[2].equals(rowData[2])) {
                            setTotal(quantityRequired * Double.parseDouble(itemData[5]));
                            break;
                        }
                    }
                }

                // Append PO entry
                try (BufferedWriter poWriter = new BufferedWriter(new FileWriter(poFilePath, true))) {
                    String poEntry = String.format("%s,%s,%s,%s,%d,%.2f,Pending",generateNextId(),date, supplierId, itemCode, quantityRequired, total);
                    poWriter.write(poEntry);
                    poWriter.newLine();
//                    poWriter.newLine();
                }
            }

            updatedRequests.add(String.join(",", rowData));
        }

        // Write back updated requests if necessary
        if (isUpdated) {
            try (BufferedWriter requestWriter = new BufferedWriter(new FileWriter(requestsFilePath))) {
                for (String updatedLine : updatedRequests) {
                    requestWriter.write(updatedLine);
                    requestWriter.newLine(); // Ensure proper formatting
                }
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}

public int delete() {
    String filePath = FilePath.posPath;
    
    boolean isDeleted = false;

    List<String> updatedLines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;

        // Read all lines from the file
        while ((line = reader.readLine()) != null) {
            String[] rowData = line.split(",");

            // Check if the current line matches the ID to be deleted
            if (rowData.length > 0 && rowData[0].equals(poID)) {
                isDeleted = true; // Mark that the ID was found and skipped
                continue; // Skip this line
            }

            updatedLines.add(line); // Keep other lines
        }
    } catch (IOException e) {
        e.printStackTrace();
        
    }

    // Write back the updated lines if an entry was deleted
    if (isDeleted) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        
    }
    return 1; // Return 1 to indicate success

    
}
public static List<purchaseOrder> readPOsFromFile(String filename) {
        filename = FilePath.posPath;
        List<purchaseOrder> poList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                purchaseOrder po = new purchaseOrder(details[0], details[1], details[2], details[3],
                               Integer.parseInt(details[4]), Double.parseDouble(details[5]), details[6]);
                poList.add(po);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the PO file.");
            e.printStackTrace();
            
        }
        return poList;
                
}

 // Method to update the status of a PO in the file
    public static void updatePOStatus(String filename, String poID, String newStatus) throws FileNotFoundException, IOException {
        filename = FilePath.posPath;
      List<purchaseOrder> poList = readPOsFromFile(filename);
        boolean updated = false;

        for (purchaseOrder po : poList) {
            if (po.getPoID().equals(poID)) {
                po.setPayment_status(newStatus);
                updated = true;
                break;
            }
        }

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (purchaseOrder po : poList) {
                    writer.write(po.toString());
                    writer.newLine();
                }
                System.out.println("PO status updated successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while updating the PO status.");
                e.printStackTrace();
            }
        } else {
            System.out.println("PO with ID " + poID + " not found.");
        }
    }
    }



package Classes;

import java.io.*;
import java.util.*;

public class Supplier extends BaseSupplier {

    // Constructors
    public Supplier(String supplierId, String supplierName, String supplierDescription, String supplierStatus, String supplierEmail) {
        super(supplierId, supplierName, supplierDescription, supplierStatus, supplierEmail);
    }

    public Supplier(String supplierID, String supplierName, String contactInfo, String paymentStatus) {
        super(supplierID, supplierName, contactInfo, paymentStatus);
    }

    public Supplier(String string, String string1, String string2) {
        super(string, string1, string2);
    }

    // Method to save supplier to file
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(supplierId + "," + supplierName + "," + supplierDescription + "," + supplierStatus + "," + supplierEmail);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing supplier in the file
    public static boolean updateSupplierInFile(String filePath, String supplierId, String name, String description, String status, String email) {
        File file = new File(filePath);
        boolean updated = false;

        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(supplierId)) {
                        lines.add(String.join(",", supplierId, name, description, status, email));
                        updated = true;
                    } else {
                        lines.add(line);
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // Method to delete a supplier from the file
    public static boolean deleteSupplierFromFile(String filePath, String supplierId) {
        StringBuilder fileContent = new StringBuilder();
        boolean supplierDeleted = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(supplierId)) {
                    fileContent.append(line).append("\n");
                } else {
                    supplierDeleted = true;
                }
            }

            if (supplierDeleted) {
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

    // Method to generate the next supplier ID
    public static String generateNextSupplierId(String filePath) {
        String nextSupplierId = "SUP001";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String lastLine = null, currentLine;

            while ((currentLine = br.readLine()) != null) {
                lastLine = currentLine;
            }

            if (lastLine != null) {
                String[] data = lastLine.split(",");
                if (data.length > 0) {
                    String lastSupplierId = data[0].trim();
                    if (lastSupplierId.startsWith("SUP")) {
                        int lastNumericId = Integer.parseInt(lastSupplierId.substring(3));
                        nextSupplierId = String.format("SUP%03d", lastNumericId + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextSupplierId;
    }

    // Method to read suppliers from a file
    public static List<Supplier> readSuppliersFromFile() {
        String supplierFile = FilePath.supplierPath;
        List<Supplier> suppliers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(supplierFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String supplierId = data[0].trim();
                    String supplierName = data[1].trim();
                    String supplierDescription = data[2].trim();
                    String supplierStatus = data[3].trim();
                    String supplierEmail = data[4].trim();
                    suppliers.add(new Supplier(supplierId, supplierName, supplierDescription, supplierStatus, supplierEmail));
                } else {
                    System.err.println("Invalid supplier line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}

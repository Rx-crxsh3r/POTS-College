/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author abdul
 */
public class SalesReportClass {
    private List<String[]> salesData;
    
    
    public SalesReportClass() {
        salesData = new ArrayList<>();
        loadSalesData();
    }

    
    
    private void loadSalesData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath.salesPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    salesData.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> generateReport(String itemCode, String year, String month) {
        List<String[]> filteredEntries = new ArrayList<>();

        for (String[] entry : salesData) {
            String[] dateParts = entry[4].split("-");
            String entryYear = dateParts[2];
            String entryMonth = dateParts[1];

            boolean matchesItemCode = itemCode == null || entry[0].equals(itemCode);
            boolean matchesYear = year == null || entryYear.equals(year);
            boolean matchesMonth = month == null || entryMonth.equals(month);

            if (matchesItemCode && matchesYear && matchesMonth) {
                filteredEntries.add(entry);
            }
        }
        return filteredEntries;
    }

    public Set<String> getItemCodes() {
        Set<String> itemCodes = new HashSet<>();
        for (String[] entry : salesData) {
            itemCodes.add(entry[0]);
        }
        return itemCodes;
    }

    public Set<String> getYears() {
        Set<String> years = new HashSet<>();
        for (String[] entry : salesData) {
            String year = entry[4].split("-")[2];
            years.add(year);
        }
        return years;
    }

    public Set<String> getMonths() {
        Set<String> months = new HashSet<>();
        for (String[] entry : salesData) {
            String month = entry[4].split("-")[1];
            months.add(month);
        }
        return months;
    }
    
    public void exportReportToPdf(List<String[]> reportData, String filePath) throws DocumentException, IOException {
        // Create a Document object
        Document document = new Document();
        
        // Create PdfWriter instance
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        
        // Open the document
        document.open();
        
        // Add a title
        document.add(new Paragraph("Sales Report"));
        document.add(new Paragraph("Generated on: " + java.time.LocalDate.now()));
        document.add(new Paragraph("                                              "));
        
        // Create a table with the number of columns equal to the report's data
        PdfPTable table = new PdfPTable(6); // Assuming 6 columns in the report (Item Code, Item Name, Category, Quantity, Date, Total Amount)
        
        // Add table headers
        table.addCell("Item Code");
        table.addCell("Item Name");
        table.addCell("Item Category");
        table.addCell("Quantity");
        table.addCell("Date");
        table.addCell("Total Amount");
        
        // Add data to the table
        for (String[] entry : reportData) {
            for (String cell : entry) {
                table.addCell(cell);
            }
        }
        
        // Add the table to the document
        document.add(table);
        
        // Close the document
        document.close();
    }

    public void exportReportToCsv(Vector<Vector> dataVector, String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

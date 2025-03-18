package Classes;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abdul
 */
public class SalesEntry {
    private String itemCode;
    private String itemName;
    private String itemCategory;
    private int quantity;
    private String date;
    private double unitPrice;  
    private double totalAmount;


    public SalesEntry(String itemCode, String itemName, String itemCategory, int quantity, String date, double unitPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.quantity = quantity;
        this.date = date;
        this.unitPrice = unitPrice;
        this.totalAmount = unitPrice * quantity; 
    }
    
        public SalesEntry(String itemCode, String itemName, int quantity, String date, double unitPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.date = date;
        this.unitPrice = unitPrice;
        this.totalAmount = unitPrice * quantity; 
    }

    
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemCategory() {
        return itemCategory;
    }
    
       public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.totalAmount = unitPrice * quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return itemCode + "," + itemName +","+ itemCategory + "," + quantity + "," + date + "," + totalAmount;
    }


    

    public static SalesEntry fromString(String data) {
        String[] parts = data.split(",\\s*"); 
        String itemCode = parts[0].trim();
        String itemName = parts[1].trim();
        String itemCategory = parts[2].trim();
        int quantity = Integer.parseInt(parts[3].trim());
        String date = parts[4].trim();
        double unitPrice = Double.parseDouble(parts[5].trim());

        return new SalesEntry(itemCode, itemName, itemCategory, quantity, date, unitPrice);
    }

    
}

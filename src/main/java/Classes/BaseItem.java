/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author thebl
 */
public class BaseItem extends User{
    
    protected String itemCode;
    protected String itemName;
    protected String supplierId;
    protected String description;
    protected String category;
    protected double price;
    protected int quantityInStock;
    protected int reorderLevel;
    protected String unitOfMeasurement;
    protected String status;
    protected String dateAdded;

    public BaseItem() {}

    public BaseItem(String itemCode, String itemName, String supplierId, String description, String category, 
                    double price, int quantityInStock, int reorderLevel, String unitOfMeasurement, String status) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.supplierId = supplierId;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.reorderLevel = reorderLevel;
        this.unitOfMeasurement = unitOfMeasurement;
        this.status = status;
    }

    public BaseItem(String itemCode, String itemName, String supplierId, String description, String category, 
                    double price, int quantityInStock, int reorderLevel, String unitOfMeasurement, String dateAdded, String status) {
        this(itemCode, itemName, supplierId, description, category, price, quantityInStock, reorderLevel, unitOfMeasurement, status);
        this.dateAdded = dateAdded;
    }

    // All existing getters, setters, and utility methods
    public String getItemCode() { return itemCode; }
    public String getItemName() { return itemName; }
    public String getSupplierId() { return supplierId; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantityInStock() { return quantityInStock; }
    public int getReorderLevel() { return reorderLevel; }
    public String getUnitOfMeasurement() { return unitOfMeasurement; }
    public String getStatus() { return status; }
    public String getDateAdded() { return dateAdded != null ? dateAdded : "Not Available"; }
}


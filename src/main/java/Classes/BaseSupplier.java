/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.*;
import java.util.*;

public class BaseSupplier extends User{
    protected String supplierId;
    protected String supplierName;
    protected String supplierDescription;
    protected String supplierStatus;
    protected String supplierEmail;
    protected String paymentStatus;

    // Constructor
    public BaseSupplier(String supplierId, String supplierName, String supplierDescription, String supplierStatus, String supplierEmail) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierDescription = supplierDescription;
        this.supplierStatus = supplierStatus;
        this.supplierEmail = supplierEmail;
    }

    public BaseSupplier(String supplierID, String supplierName, String contactInfo, String paymentStatus) {
        this.supplierId = supplierID;
        this.supplierName = supplierName;
        this.supplierEmail = contactInfo;
        this.paymentStatus = paymentStatus;
    }

    public BaseSupplier(String string, String string1, String string2) {
        this.supplierId = string;
        this.supplierName = string1;
        this.supplierEmail = string2;
    }

    // Getters and Setters
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierDescription() {
        return supplierDescription;
    }

    public void setSupplierDescription(String supplierDescription) {
        this.supplierDescription = supplierDescription;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return supplierId + "," + supplierName + "," + supplierEmail + "," + paymentStatus;
    }
}

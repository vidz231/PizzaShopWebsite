/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

import java.util.UUID;

/**
 *
 * @author TRUNG VI
 */
public class OrderDetails{

    private UUID orderID;
    private int productID;
    private double unitPrice;
    private int quantity;

    public OrderDetails() {
    }
    
    
    

    public OrderDetails(UUID orderID, int productID, double unitPrice, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void setOrderID(UUID orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderID=" + orderID + ", productID=" + productID + ", unitPrice=" + unitPrice + ", quantity=" + quantity + '}';
    }

}

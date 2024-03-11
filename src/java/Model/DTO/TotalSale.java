/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

import java.sql.Date;

/**
 *
 * @author TRUNG VI
 */
public class TotalSale {

    private int productID;
    private Date orderDate;
    private int totalQuantity;
    private double unitPrice;

    public TotalSale() {
    }

    public TotalSale(int productID, Date orderDate, int totalQuantity, double unitPrice) {
        this.productID = productID;
        this.orderDate = orderDate;
        this.totalQuantity = totalQuantity;
        this.unitPrice = unitPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getToltalSale() {
        return this.totalQuantity * this.unitPrice;
    }

    @Override
    public String toString() {
        return "TotalSale{" + "productID=" + productID + ", orderDate=" + orderDate + ", totalQuantity=" + totalQuantity + ", unitPrice=" + unitPrice + '}';
    }

}

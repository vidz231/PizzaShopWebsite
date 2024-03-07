/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

import java.io.Serializable;

/**
 *
 * @author TRUNG VI
 */
public class Order implements Serializable{
    private int orderID;
    private int customerID;
    private String orderDate;
    private String requireDate;
    private String shippedDate;
    private String freight;//don vi giao hang of(shoppe ,grab,gojek, bla bla)
    private String shipAddress;

    public Order() {
    }

    public Order(int orderID, int customerID, String orderDate, String requireDate, String shippedDate, String freight, String shipAddress) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.requireDate = requireDate;
        this.shippedDate = shippedDate;
        this.freight = freight;
        this.shipAddress = shipAddress;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(String requireDate) {
        this.requireDate = requireDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    @Override
    public String toString() {
        return "Order{" + "\norderID=" + orderID + ", \ncustomerID=" + customerID + ", \norderDate=" + orderDate + ", \nrequireDate=" + requireDate + ",\nshippedDate=" + shippedDate + ", \nfreight=" + freight + ", \nshipAddress=" + shipAddress + '}';
    }
    
    
    
    
}

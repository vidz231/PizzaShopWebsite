/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author TRUNG VI
 */
public class Order implements Serializable{
    private int orderID;
    private int customerID;
    private Date orderDate;
    private Date requireDate;
    private Date shippedDate;
    private String freight;//don vi giao hang of(shoppe ,grab,gojek, bla bla)
    private String shipAddress;

    public Order() {
    }

    public Order(int orderID, int customerID, Date orderDate, Date requireDate, Date shippedDate, String freight, String shipAddress) {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequireDate() {
        return requireDate;
    }

    public void setRequireDate(Date requireDate) {
        this.requireDate = requireDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
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

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
public class Supplier implements Serializable {

    private int supplierID;
    private String companyName;
    private String address;
    private long phone;

    public Supplier() {
    }

    public Supplier(int supplierID, String companyName, String address, long phone) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Supplier{" + "\nsupplierID=" + supplierID + ", \ncompanyName=" + companyName + ", \naddress=" + address + ", \nphone=" + phone + '}';
    }

}

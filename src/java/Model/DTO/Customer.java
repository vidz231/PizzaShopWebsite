/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

/**
 *
 * @author TRUNG VI
 */
public class Customer {
    private int id;
    private String password;
    private String contactName;
    private String address;
    private long Phone;

    public Customer() {
    }

    public Customer(int id, String password, String contactName, String address, long Phone) {
        this.id = id;
        this.password = password;
        this.contactName = contactName;
        this.address = address;
        this.Phone = Phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return Phone;
    }

    public void setPhone(long Phone) {
        this.Phone = Phone;
    }

    @Override
    public String toString() {
        return "Customer{" + "\nid=" + id + ", \npassword=" + password + ",\ncontactName=" + contactName + ", \n address=" + address + ",\n Phone=" + Phone + '}';
    }
    
    
    
    
    
}

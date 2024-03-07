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
public class User implements Serializable {

    private int accountID;
    private String username;
    private String fullName;
    private String password;
    private int type;

    public User() {
    }

    public User(int accountID, String username, String fullName, String password, int type) {
        this.accountID = accountID;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.type = type;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "\naccountID=" + accountID + ",\n username=" + username + ",\n fullName=" + fullName + ",\n password=" + password + ", \ntype=" + type + '}';
    }
    

}

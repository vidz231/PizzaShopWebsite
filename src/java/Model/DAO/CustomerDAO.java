/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DBUtils;
import Model.DTO.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author TRUNG VI
 */
public class CustomerDAO {

    //<editor-fold defaultstate="collapse" desc="view All Customer method">
    public HashMap<Integer, Customer> viewAllCustomer() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        HashMap<Integer, Customer> customerList = new HashMap<>();
        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT CustomerID, Password, ContactName, Address, Phone FROM Customers";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CustomerID");
                String password = rs.getString("Password");
                String contactName = rs.getString("ContactName");
                String address = rs.getString("Address");
                long phone = rs.getLong("Phone");
                Customer customer = new Customer(id, password, contactName, address, phone);
                customerList.put(id, customer);
            }
        } catch (Exception e) {
            System.out.println("error at view all customer: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return customerList.isEmpty() ? null : customerList;
    }//end  method
    //</editor-fold>
    //<editor-fold defaultstate="collapse" desc="create Customer Method">

    public boolean createCustomer(Customer customer) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtils.getConnection();
            String sql = "INSERT INTO Customers(CustomerID,[Password], ContactName, [Address], Phone) "
                    + " VALUES(?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, customer.getId());
            preStm.setString(2, customer.getPassword());
            preStm.setString(3, customer.getContactName());
            preStm.setString(4, customer.getAddress());
            preStm.setLong(5, customer.getPhone());
            return preStm.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("error at create Customer: " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return false;
    }//end create user method
//    </editor-fold>
    //<editor-fold defaultstate="collapse" desc="update Customer method">

    public boolean updateCustomer(Customer updatedCustomer) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "UPDATE Customers "
                    + "SET Password= ?, ContactName= ?, Address= ?, Phone= ? "
                    + "WHERE CustomerID= ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, updatedCustomer.getPassword());
            preStm.setString(2, updatedCustomer.getContactName());
            preStm.setString(3, updatedCustomer.getAddress());
            preStm.setLong(4, updatedCustomer.getPhone());
            preStm.setInt(5, updatedCustomer.getId());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at updateCustomer method: " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return false;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapse" desc="delete Customer method">
    public boolean deleteCustomer(int id) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "DELETE FROM Customers"
                    + "WHERE CustomerID =?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, id);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at delete Customer:   " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return false;
    }//end delete Customer method
//    </editor-fold>
    //<editor-fold defaultstate="collapse" desc="get customer by id">

    public Customer getUserByID(int id) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT CustomerID, Password, ContactName, Address, Phone FROM Customers WHERE CustomerID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String password = rs.getString("Password");
                String contactName = rs.getString("ContactName");
                String address = rs.getString("Address");
                long phone = rs.getLong("Phone");
                customer = new Customer(id, password, contactName, address, phone);
            }
        } catch (Exception e) {
            System.out.println("error at get user by id: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return customer;
    }
//</editor-fold>
}

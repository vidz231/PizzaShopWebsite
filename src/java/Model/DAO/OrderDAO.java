/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DBUtils;
import Model.DTO.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author TRUNG VI
 */
public class OrderDAO {

    //<editor-fold defaultstate="collapse" desc="view All Order method">
    public List<Order> viewAllOrder() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();

        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress FROM Orders";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();

            while (rs.next()) {
                UUID orderId = UUID.fromString(rs.getString("OrderID"));
                int customerId = rs.getInt("CustomerID");
                Date orderDate = rs.getDate("OrderDate");
                Date requiredDate = rs.getDate("RequiredDate");
                Date shippedDate = rs.getDate("ShippedDate");
                String freight = rs.getString("Freight");
                String shipAddress = rs.getString("ShipAddress");

                Order order = new Order(orderId, customerId, orderDate, requiredDate, shippedDate, freight, shipAddress);
                orderList.add(order);
            }
        } catch (Exception e) {
            System.out.println("Error at viewAllOrder: " + e.getMessage());
            throw e; // Rethrow the exception to be handled elsewhere
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preStm != null) {
                    preStm.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
        return orderList;
    }
//end view all order method
    //</editor-fold>
    //<editor-fold defaultstate="collapse" desc="create Order">

    public boolean createOrder(Order order) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtils.getConnection();
            String sql = "INSERT INTO Orders(OrderID,CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress) VALUES (?,?, ?, ?, ?, ?, ?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, order.getOrderID().toString());
            preStm.setInt(2, order.getCustomerID());
            preStm.setDate(3, order.getOrderDate());
            preStm.setDate(4, order.getRequireDate());
            preStm.setDate(5, order.getShippedDate());
            preStm.setString(6, order.getFreight());
            preStm.setString(7, order.getShipAddress());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error at createOrder: " + e.getMessage());
        } finally {
            try {
                if (preStm != null) {
                    preStm.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
        return false;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapse" desc="update Order method">

    public boolean updateOrder(Order updatedOrder) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "UPDATE Orders "
                    + "SET CustomerID=?, OrderDate=?, RequiredDate=?, ShippedDate=?, Freight=?, ShipAddress=? "
                    + "WHERE OrderID=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, updatedOrder.getCustomerID());
            preStm.setDate(2, updatedOrder.getOrderDate());
            preStm.setDate(3, updatedOrder.getRequireDate());
            preStm.setDate(4, updatedOrder.getShippedDate());
            preStm.setString(5, updatedOrder.getFreight());
            preStm.setString(6, updatedOrder.getShipAddress());
            preStm.setString(7, updatedOrder.getOrderID().toString());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error at updateOrder method: " + e.getMessage());
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
    //<editor-fold defaultstate="collapse" desc="delete Order method">
    public boolean deleteOrder(UUID id) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "BEGIN TRANSACTION; "
                    + "DELETE FROM OrderDetails WHERE OrderID = ?; "
                    + "DELETE FROM Orders WHERE OrderID = ?; "
                    + "COMMIT; ";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, id.toString());
            preStm.setString(2, id.toString());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at delete Order:   " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return false;
    }//end delete Order method
//    </editor-fold>

}

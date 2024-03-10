/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DBUtils;
import Model.DTO.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author TRUNG VI
 */
public class OrderDetailsDAO {
    //<editor-fold defaultstate="collapse" desc="view All OrderDetails method">

    public List<OrderDetails> viewAllOrderDetails() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<OrderDetails> orderDetailList = new ArrayList<>();

        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT OrderID, ProductID, UnitPrice, Quantity FROM OrderDetails";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();

            while (rs.next()) {
                UUID orderId = UUID.fromString(rs.getString("OrderID")) ;
                int productId = rs.getInt("ProductID");
                double unitPrice = rs.getDouble("UnitPrice");
                int quantity = rs.getInt("Quantity");

                OrderDetails orderDetail = new OrderDetails(orderId, productId, unitPrice, quantity);
                orderDetailList.add(orderDetail);
            }
        } catch (Exception e) {
            System.out.println("Error at viewAllOrderDetails: " + e.getMessage());
            throw e;
        } finally {
            closeResources(cnn, preStm, rs);
        }
        return orderDetailList;
    }
//</editor-fold>

//<editor-fold defaultstate="collapse" desc="create OrderDetail">
    public boolean createOrderDetail(OrderDetails orderDetail) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtils.getConnection();
            String sql = "INSERT INTO OrderDetails(OrderID, ProductID, UnitPrice, Quantity) VALUES (?, ?, ?, ?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, orderDetail.getOrderID().toString());
            preStm.setInt(2, orderDetail.getProductID());
            preStm.setDouble(3, orderDetail.getUnitPrice());
            preStm.setInt(4, orderDetail.getQuantity());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error at createOrderDetail: " + e.getMessage());
        } finally {
            closeResources(cnn, preStm, null);
        }
        return false;
    }
//</editor-fold>

//<editor-fold defaultstate="collapse" desc="update OrderDetail method">
    public boolean updateOrderDetail(OrderDetails updatedOrderDetail) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "UPDATE OrderDetails "
                    + "SET ProductID=?, UnitPrice=?, Quantity=? "
                    + "WHERE OrderID=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, updatedOrderDetail.getProductID());
            preStm.setDouble(2, updatedOrderDetail.getUnitPrice());
            preStm.setInt(3, updatedOrderDetail.getQuantity());
            preStm.setString(4, updatedOrderDetail.getOrderID().toString());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error at updateOrderDetail method: " + e.getMessage());
        } finally {
            closeResources(cnn, preStm, null);
        }
        return false;
    }
//</editor-fold>

//<editor-fold defaultstate="collapse" desc="delete OrderDetail method">
    public boolean deleteOrderDetail(int orderId) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "DELETE FROM OrderDetails "
                    + "WHERE OrderID =?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, orderId);

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at delete OrderDetail:   " + e.getMessage());
        } finally {
            closeResources(cnn, preStm, null);
        }
        return false;
    }
//</editor-fold>

    private void closeResources(Connection cnn, PreparedStatement preStm, ResultSet rs) {
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

}

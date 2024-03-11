/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DBUtils;
import Model.DTO.TotalSale;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRUNG VI
 */
public class CustomQueryHandlerDAO {

    public List<TotalSale> getAllProduct() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<TotalSale> totalSaleList = new ArrayList<>();
        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT Products.ProductID ,Orders.OrderDate, OrderDetails.UnitPrice, SUM(OrderDetails.Quantity) as totalQuantity "
                    + "FROM OrderDetails "
                    + "INNER JOIN Orders ON OrderDetails.OrderID = Orders.OrderID "
                    + "INNER JOIN Products ON OrderDetails.ProductID = Products.ProductID "
                    + "GROUP BY OrderDetails.UnitPrice, Orders.OrderDate, Products.ProductID";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                Date orderDate = rs.getDate("OrderDate");
                double unitPrice = rs.getDouble("UnitPrice");
                int totalQuantity = rs.getInt("totalQuantity");
                TotalSale totalSale = new TotalSale(productID, orderDate, totalQuantity, unitPrice);
                totalSaleList.add(totalSale);
            }
        } catch (Exception e) {
            System.out.println("error at getAllProduct: " + e.getMessage());
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
        return totalSaleList.isEmpty() ? null : totalSaleList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DBUtils;
import Model.DTO.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRUNG VI
 */
public class OrderDAO {

    //<editor-fold defaultstate="collapse" desc="view All Product method">
    public List<Product> viewAllProduct() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();

        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage "
                    + "FROM Products";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                int supplierId = rs.getInt("SupplierID");
                int categoryId = rs.getInt("CategoryID");
                int quantityPerUnit = rs.getInt("QuantityPerUnit");
                float unitPrice = rs.getFloat("UnitPrice");
                String productImage = rs.getString("ProductImage");
                // Create a new Product object with retrieved data
                Product product = new Product(productId, productName, supplierId, categoryId, quantityPerUnit, unitPrice, productImage);
                // Add the Product object to the list
                productList.add(product);
            }
        } catch (Exception e) {
            System.out.println("Error at viewAllProduct: " + e.getMessage());
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
        return productList;
    }//end authe method
    //</editor-fold>

}

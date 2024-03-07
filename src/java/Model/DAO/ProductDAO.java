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
public class ProductDAO {

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
    //<editor-fold defaultstate="collapse" desc="create supplier Method ">

    public boolean createProduct(Product product) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtils.getConnection();
            String sql = "INSERT INTO Products(ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, product.getProductName());
            preStm.setInt(2, product.getSupplierID());
            preStm.setInt(3, product.getCategoryID());
            preStm.setInt(4, product.getQuantityPerUnit());
            preStm.setDouble(5, product.getUnitPrice());
            preStm.setString(6, product.getProductImage());

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error at createProduct: " + e.getMessage());
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
    }//end create user method
//    </editor-fold>

    //<editor-fold defaultstate="collapse" desc="update Product method">
  public boolean updateProduct(Product updatedProduct) throws SQLException {
    Connection cnn = null;
    PreparedStatement preStm = null;
    
    try {
        cnn = DBUtils.getConnection();
        String sql = "UPDATE Products "
                   + "SET ProductName = ?, SupplierID = ?, CategoryID = ?, QuantityPerUnit = ?, UnitPrice = ?, ProductImage = ? "
                   + "WHERE ProductID = ?";
        preStm = cnn.prepareStatement(sql);
        preStm.setString(1, updatedProduct.getProductName());
        preStm.setInt(2, updatedProduct.getSupplierID());
        preStm.setInt(3, updatedProduct.getCategoryID());
        preStm.setInt(4, updatedProduct.getQuantityPerUnit());
        preStm.setDouble(5, updatedProduct.getUnitPrice());
        preStm.setString(6, updatedProduct.getProductImage());
        preStm.setInt(7, updatedProduct.getProductID());
        
        return preStm.executeUpdate() > 0;
    } catch (Exception e) {
        System.out.println("Error at updateProduct method: " + e.getMessage());
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
    //<editor-fold defaultstate="collapse" desc="delete Product method">
    public boolean deleteProduct(int id) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "DELETE FROM Products"
                    + "WHERE ProductID =?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, id);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at delete Product:   " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return false;
    }//end delete Product method
//    </editor-fold>

}

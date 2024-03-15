/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DBUtils;
import Model.DTO.Category;
import Model.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author TRUNG VI
 */
public class CategoryDAO {

    //<editor-fold defaultstate="collapse" desc="view All Category method">
    public HashMap<Integer,Category> viewAllCategory() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        HashMap<Integer,Category> categoryList = new HashMap<>();
        Category category;
        int id;
        String categoryName;
        String description;
        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT CategoryID,CategoryName,Description "
                    + "FROM     Categories";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                categoryName = rs.getString(2);
                description = rs.getString(3);
                category = new Category(id, categoryName, description);
                categoryList.put(id,category);
            }
        } catch (Exception e) {
            System.out.println("error at view all category:     " + e.getMessage());
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
        if(!categoryList.isEmpty()){
            return categoryList;
        }
        return null;
    }//end authe method
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="create User Method a.k.a register user">
    public boolean createCategory(Category category) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtils.getConnection();
            String sql = " INSERT INTO Categories(CategoryName,Description) "
                    + "Values(?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, category.getCategoryName());
            preStm.setString(2, category.getDescription());
            return preStm.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("error at create Category:   " + e.getMessage());
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

    //<editor-fold defaultstate="collapse" desc="update Category method">
    public boolean updateCategory(Category updatedCategory) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "Update dbo.Categories"
                    + "SET CategoryName = ?,Description=?"
                    + "where CategoryID=?;";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, updatedCategory.getCategoryName());
            preStm.setString(2, updatedCategory.getDescription());
            preStm.setInt(3, updatedCategory.getCategoryID());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at udapteCategory method: " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return true;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapse" desc="delete Category method">
    public boolean deleteCategory(int id) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "DELETE FROM Categories "
                    + "WHERE CategoryID =?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, id);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at delete Category:   " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return false;
    }//end delete Category method
//    </editor-fold>

}

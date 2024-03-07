/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DBUtils;
import Model.DTO.Supplier;
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
public class SupplierDAO {

    //<editor-fold defaultstate="collapse" desc="view All Supplier method">
    public List<Supplier> viewAllSupplier() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<Supplier> supplierList = new ArrayList<>();
        Supplier supplier;
        int id;
        String CompanyName;
        String address;
        long phone;
        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT SupplierID,CompanyName,Address,Phone"
                    + "FROM     Suppliers";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                CompanyName = rs.getString(2);
                address = rs.getString(3);
                phone = rs.getLong(4);
                supplier = new Supplier(id, CompanyName, address, phone);
                supplierList.add(supplier);
            }
        } catch (Exception e) {
            System.out.println("error at view all supplier:     " + e.getMessage());
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

        return null;
    }//end authe method
    //</editor-fold>

    //<editor-fold defaultstate="collapse" desc="create supplier Method ">
    public boolean createSupplier(Supplier supplier) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtils.getConnection();
            String sql = " INSERT INTO Suppliers(CompanyName,Address,Phone) "
                    + "Values(?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, supplier.getCompanyName());
            preStm.setString(2, supplier.getAddress());
            preStm.setLong(3, supplier.getPhone());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at create Supplier:   " + e.getMessage());
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

    //<editor-fold defaultstate="collapse" desc="update Supplier method">
    public boolean updateSupplier(Supplier updatedSupplier) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "Update dbo.Suppliers"
                    + "SET CompanyName = ?,Address=?,Phone=?"
                    + "where SupplierID=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, updatedSupplier.getCompanyName());
            preStm.setString(2, updatedSupplier.getAddress());
            preStm.setLong(3, updatedSupplier.getPhone());
            preStm.setInt(4, updatedSupplier.getSupplierID());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at udapteSupplier method: " + e.getMessage());
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
    //<editor-fold defaultstate="collapse" desc="delete Supplier method">
    public boolean deleteSupplier(int id) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "DELETE FROM Suppliers"
                    + "WHERE SupplierID =?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, id);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at delete Supplier:   " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return false;
    }//end delete Supplier method
//    </editor-fold>

}

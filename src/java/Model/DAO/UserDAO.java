/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DBUtils;
import Model.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TRUNG VI
 */
public class UserDAO {

    //<editor-fold defaultstate="collapse" desc="sign in method">
    public User authenticate(String username, String password) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        int accountID;
        String newUsername;
        String fullName;
        String newPassword;
        int type;

        try {
            cnn = DBUtils.getConnection();
            String sql = "SELECT AccountID, UserName, Password, FullName, Type"
                    + "FROM     Account"
                    + "WHERE  (UserName = ?) AND (Password = ?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);

            rs = preStm.executeQuery();
            while (rs.next()) {
                accountID = rs.getInt(1);
                newUsername = rs.getString(2);
                fullName = rs.getString(4);
                newPassword = "*****";
                type = rs.getInt(5);
                if (!fullName.isEmpty()) {
                    return new User(accountID, username, fullName, password, type);
                }
            }
        } catch (Exception e) {
            System.out.println("error at athenticate" + e.getMessage());
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

    //<editor-fold defaultstate="collapse" desc="create User Method a.k.a register user">
    public boolean createUser(User user) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtils.getConnection();
            String sql = " INSERT INTO Account(UserName,[Password],FullName,[Type] "
                    + "Values(?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getUsername());
            preStm.setString(2, user.getPassword());
            preStm.setString(3, user.getFullName());
            preStm.setInt(4, user.getType());
            return preStm.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("error at create USer" + e.getMessage());
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
    
    //<editor-fold defaultstate="collapse" desc="update User method">
    public boolean updateUserMethod(User updatedUser) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "Update dbo.Account"
                    + "SET UserName = ?,Password=?,FullName=?"
                    + "where AccountID=?;";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, updatedUser.getUsername());
            preStm.setString(2, updatedUser.getPassword());
            preStm.setString(3, updatedUser.getFullName());
            preStm.setInt(4, updatedUser.getAccountID());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at udapteUserMethod: " + e.getMessage());
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
    
    //<editor-fold defaultstate="collapse" desc="delete user method">
    public boolean deleteUser(int id) throws SQLException {
        Connection cnn = null;
        PreparedStatement preStm = null;
        try {
            cnn = DBUtils.getConnection();
            String sql = "DELETE FROM Account"
                    + "WHERE AccountID =?";

            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, id);
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("error at delete user:   " + e.getMessage());
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return false;
    }//end delete user method
//    </editor-fold>

}

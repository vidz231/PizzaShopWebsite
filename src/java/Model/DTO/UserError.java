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
public class UserError implements Serializable {

    private int accountIDError;
    private String usernameError;
    private String fullNameError;
    private String passwordError;
    private String matchedPasswordError;

    public UserError() {
    }

    public UserError(int accountIDError, String usernameError, String fullNameError, String passwordError, String matchedPasswordError) {
        this.accountIDError = accountIDError;
        this.usernameError = usernameError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.matchedPasswordError = matchedPasswordError;
    }

    public String getMatchedPasswordError() {
        return matchedPasswordError;
    }

    public void setMatchedPasswordError(String matchedPasswordError) {
        this.matchedPasswordError = matchedPasswordError;
    }

    public int getAccountIDError() {
        return accountIDError;
    }

    public void setAccountIDError(int accountIDError) {
        this.accountIDError = accountIDError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    @Override
    public String toString() {
        return "UserError{" + "accountIDError=" + accountIDError + ", usernameError=" + usernameError + ", fullNameError=" + fullNameError + ", passwordError=" + passwordError + '}';
    }

}

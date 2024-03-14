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
public class ProductError implements Serializable {

    private String productNameError;
    private String quantityPerUnitError;
    private String unitPriceError;
    private String productImageError;

    public ProductError() {
    }

    public ProductError(String productNameError, String quantityPerUnitError, String UnitPriceError, String productImageError) {
        this.productNameError = productNameError;
        this.quantityPerUnitError = quantityPerUnitError;
        this.unitPriceError = UnitPriceError;
        this.productImageError = productImageError;
    }

    
    
    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getQuantityPerUnitError() {
        return quantityPerUnitError;
    }

    public void setQuantityPerUnitError(String quantityPerUnitError) {
        this.quantityPerUnitError = quantityPerUnitError;
    }

    public String getUnitPriceError() {
        return unitPriceError;
    }

    public void setUnitPriceError(String UnitPriceError) {
        this.unitPriceError = UnitPriceError;
    }

    public String getProductImageError() {
        return productImageError;
    }

    public void setProductImageError(String productImageError) {
        this.productImageError = productImageError;
    }
    
    
}

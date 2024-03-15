/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DTO;

/**
 *
 * @author TRUNG VI
 */
public class CategoryError {

    private String categoryNameError;
    private String descriptionError;

    public CategoryError() {
    }

    public CategoryError(String categoryNameError, String descriptionError) {
        this.categoryNameError = categoryNameError;
        this.descriptionError = descriptionError;
    }

    public String getCategoryNameError() {
        return categoryNameError;
    }

    public void setCategoryNameError(String categoryNameError) {
        this.categoryNameError = categoryNameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    @Override
    public String toString() {
        return "CategoryError{" + "categoryNameError=" + categoryNameError + ", descriptionError=" + descriptionError + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.DTO.Product;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author TRUNG VI
 */
public class Utility {

    public static HashMap<Integer, Product> getProductMap(List<Product> productList) {
        HashMap<Integer, Product> productMap = new HashMap<>();

        // Populate the map
        for (Product product : productList) {
            productMap.put(product.getProductID(), product);
        }

        return productMap;
    }
}

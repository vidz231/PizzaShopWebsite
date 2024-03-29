/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Product;

import Model.DAO.ProductDAO;
import Model.DTO.Category;
import Model.DTO.Product;
import Model.DTO.ProductError;
import Model.DTO.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/CreateProductServlet"})
public class CreateProductServlet extends HttpServlet {

    private final String createProductPage = "createProduct.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = createProductPage;
        String message;
        HashMap<Integer, Supplier> supplierList;
        HashMap<Integer, Category> categoryList;
        List<Product> productList;
        boolean isError = false;
        ProductError productError = new ProductError();
        try {
            categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
            supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
            request.setAttribute("supplierList", supplierList);
            request.setAttribute("categoryList", categoryList);
            if (request.getMethod().equalsIgnoreCase("GET")) {
                url = createProductPage;
                System.out.println("processing first time load for view create product");
            } else {
                Product p;
                String productName = request.getParameter("productName");
                if (!productName.matches("^[A-Z].*$")) {
                    isError = true;
                    productError.setProductNameError("Product name must start with a capital letter");
                }
                int supplierID = Integer.parseInt(request.getParameter("supplierID"));
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                int quantityPerUnit = Integer.parseInt(request.getParameter("quantityPerUnit"));
                if (quantityPerUnit <= 0) {
                    isError = true;
                    productError.setQuantityPerUnitError("Quantity must be greater than 0");
                }
                float unitPrice = Float.parseFloat(request.getParameter("UnitPrice"));
                if (unitPrice < 0) {
                    isError = true;
                    productError.setUnitPriceError("Unit price must greater than 0");
                }
                String productImage = request.getParameter("productImage");
                if (!productImage.contains("cdn") || !productImage.contains("https") || !productImage.contains("http")) {
                    isError = true;
                    productError.setProductImageError("please enter a valid image url");
                }
                p = new Product(0, productName, supplierID, categoryID, quantityPerUnit, unitPrice, productImage);

                if (isError == false) {
                    ProductDAO productDao = new ProductDAO();
                    if (productDao.createProduct(p)) {
                        message = "product created succesfully";
                        request.setAttribute("message", message);
                    }
                } else {
                    request.setAttribute("productError", productError);
                    request.setAttribute("isError", isError);
                    request.setAttribute("product", p);

                }

            }

        } catch (Exception e) {
            log("error at create product: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

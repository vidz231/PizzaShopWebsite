/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Product;

import Model.DAO.ProductDAO;
import Model.DTO.Category;
import Model.DTO.Product;
import Model.DTO.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    private final String updateProductPage = "editProduct.jsp";

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
        String url = updateProductPage;
        HashMap<Integer, Supplier> supplierList;
        HashMap<Integer, Category> categoryList;
        List<Product> productList;
        String message = "error updating. Pls double check your field!";
        try {
            int id = Integer.parseInt(request.getParameter("productId"));
            supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
            productList = (List<Product>) request.getAttribute("productList");
            categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
            request.setAttribute("supplierList", supplierList);
            request.setAttribute("categoryList", categoryList);
            if (request.getMethod().equalsIgnoreCase("GET")) {
                Product resultProduct = productList.stream()
                        .filter(product -> product.getProductID() == id)
                        .findFirst()
                        .get();
                System.out.println("processing first load");
                request.setAttribute("product", resultProduct);
            } else {
                String productName = request.getParameter("productName");
                int supplierID = Integer.parseInt(request.getParameter("supplierID"));
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                int quantityPerUnit = Integer.parseInt(request.getParameter("quantityPerUnit"));
                float unitPrice = Float.parseFloat(request.getParameter("UnitPrice"));
                String productImage = request.getParameter("productImage");
                ProductDAO productDao = new ProductDAO();
                System.out.println("prrocessing form submission");
                // Create a new product with the form data
                Product newProduct = new Product(id, productName, supplierID, categoryID, quantityPerUnit, unitPrice, productImage);
                if (productDao.updateProduct(newProduct)) {
                    message = "updated succesfully";
                    request.setAttribute("product", newProduct);
                    request.setAttribute("message", message);
                }

            }

        } catch (Exception e) {
            log("error at updateProduct servlet" + e.getMessage());

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

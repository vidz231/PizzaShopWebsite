/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Product;

import Model.DAO.CategoryDAO;
import Model.DAO.ProductDAO;
import Model.DAO.SupplierDAO;
import Model.DTO.Category;
import Model.DTO.Product;
import Model.DTO.Supplier;
import Model.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    private final String createProductServlet = "CreateProductServlet";
    private final String updateProductServlet = "UpdateProductServlet";
    private final String deleteProductServlet = "DeleteProductServlet";
    private final String viewProductServlet = "ViewProductServlet";

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
        String url = viewProductServlet;
        HashMap<Integer, Supplier> supplierList;
        HashMap<Integer, Category> categoryList;
        List<Product> productList;

        try {
            String action = request.getParameter("action").toLowerCase();
            SupplierDAO supplierDao = new SupplierDAO();
            CategoryDAO categoryDao = new CategoryDAO();
            ProductDAO productDAO = new ProductDAO();
            supplierList = supplierDao.viewAllSupplier();
            productList = productDAO.viewAllProduct();
            categoryList = categoryDao.viewAllCategory();
            if (!productList.isEmpty()) {
                request.setAttribute("productList", productList);
            }
            request.setAttribute("supplierList", supplierList);
            request.setAttribute("categoryList", categoryList);
            if (action.equals("view")) {
                url = viewProductServlet;
            } else if (action.equals("update")) {
//                System.out.println("processing update product");
                url = updateProductServlet;
            } else if (action.equals("delete")) {
                url = deleteProductServlet;
            } else if (action.equals("create")) {
                url = createProductServlet;
            }

        } catch (Exception e) {
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

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
import java.util.stream.Collectors;
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
@WebServlet(name = "ViewProductServlet", urlPatterns = {"/ViewProductServlet"})
public class ViewProductServlet extends HttpServlet {

    private final String landingPage = "landingPage.jsp";
    private final String adminDashBoard = "adminDashBoard.jsp";

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
        String url = landingPage;
        HashMap<Integer, Supplier> supplierList;
        HashMap<Integer, Category> categoryList;
        List<Product> productList;
        HttpSession session = request.getSession();
        try {
            User user = (User) session.getAttribute("user");

            supplierList = (HashMap<Integer, Supplier>) request.getAttribute("supplierList");
            productList = (List<Product>) request.getAttribute("productList");
            categoryList = (HashMap<Integer, Category>) request.getAttribute("categoryList");
//            System.out.println(productList.toString());
//            System.out.println(supplierList.toString());
//            System.out.println(categoryList.toString());
            if (!productList.isEmpty()) {
                request.setAttribute("productList", productList);
            }
            if (user != null && user.getType() == 0) {
                url = adminDashBoard;
                request.setAttribute("supplierList", supplierList);
                request.setAttribute("categoryList", categoryList);
                if (!request.getParameter("searchParam").isEmpty()) {
                    String searchParam = request.getParameter("searchParam");
                    productList = productList.stream().filter(p -> String.valueOf(p.getProductID()).contains(searchParam)
                            || p.getProductName().toLowerCase().startsWith(searchParam.toLowerCase())
                            || p.getProductName().toLowerCase().contains(searchParam.toLowerCase())
                            || String.valueOf(p.getUnitPrice()).contains(searchParam))
                            .collect(Collectors.toList());
                    request.setAttribute("productList", productList);
                    request.setAttribute("searchParam", searchParam);
                }
            } else {
                if (request.getParameter("searchParam") != null) {
                    String searchParam = request.getParameter("searchParam");
                    String searchMinPrice = request.getParameter("searchMinPrice");
                    productList = productList.stream().filter(p -> String.valueOf(p.getProductID()).contains(searchParam)
                            || p.getProductName().toLowerCase().startsWith(searchParam.toLowerCase())
                            || p.getProductName().toLowerCase().contains(searchParam.toLowerCase())
                            || String.valueOf(p.getUnitPrice()).contains(searchParam))
                            .collect(Collectors.toList());
                    request.setAttribute("productList", productList);
                    request.setAttribute("searchParam", searchParam);
                    request.setAttribute("searchminPrice", searchMinPrice);
                }
                if (request.getParameter("searchMinPrice") != null) {
                    int searchMinPrice = Integer.parseInt(request.getParameter("searchMinPrice"));
                    productList = productList.stream().filter(p -> p.getUnitPrice() >= searchMinPrice)
                            .collect(Collectors.toList());
                    request.setAttribute("productList", productList);
                    request.setAttribute("searchMinPrice", searchMinPrice);
                }
                if (request.getParameter("filterByCategory") != null) {
                    int filterByCategory = Integer.parseInt(request.getParameter("filterByCategory"));
                    productList = productList.stream()
                            .filter(p -> p.getCategoryID() == filterByCategory)
                            .collect(Collectors.toList());
                    request.setAttribute("productList", productList);
                    request.setAttribute("filterByCategory", filterByCategory);
                }
                request.setAttribute("productList", productList);
                request.setAttribute("categoryList", categoryList);
                url = landingPage;
            }

        } catch (Exception e) {
            log("error at view product: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            System.out.println("view product method completed");
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

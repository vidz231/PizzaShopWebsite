/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Category;

import Model.DAO.CategoryDAO;
import Model.DTO.Category;
import Model.DTO.CategoryError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "CreateCategoryServlet", urlPatterns = {"/CreateCategoryServlet"})
public class CreateCategoryServlet extends HttpServlet {

    private final String createCategoryPage = "createCategory.jsp";

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
        String url = createCategoryPage;
        boolean isError = false;
        CategoryDAO categoryDao = new CategoryDAO();
        CategoryError categoryError = new CategoryError();
        String message;
        try {
            if (!request.getMethod().equalsIgnoreCase("GET")) {
                String categoryName = request.getParameter("categoryName");
                if (!categoryName.matches("^[A-Z][a-zA-Z]{6,18}$")) {
                    isError = true;
                    categoryError.setCategoryNameError("Name must start with a capital letter and have lenght 6-18 characters");
                }
                String categoryDescription = request.getParameter("categoryDescription");
                if (categoryDescription == null) {
                    isError = true;
                    categoryError.setDescriptionError("description cannot be empty");
                }
                if (isError == false) {
                    Category category = new Category(0, categoryName, categoryDescription);

                    if (categoryDao.createCategory(category)) {
                        message = "category created succesfully!";
                    } else {
                        message = "error at creating category";
                    }
                    request.setAttribute("message", message);
                } else {
                    request.setAttribute("isError", isError);
                    request.setAttribute("categoryError", categoryError);
//                    request.setAttribute("category", category);
                }
            }
        } catch (SQLException e) {
            log("Error at create category servlet:  " + e.getMessage());
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

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
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "UpdateCategoryServlet", urlPatterns = {"/UpdateCategoryServlet"})
public class UpdateCategoryServlet extends HttpServlet {

    private final String updateCategoryPage = "editCategory.jsp";

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
        String url = updateCategoryPage;
        boolean isError = false;
        CategoryDAO categoryDao = new CategoryDAO();
        CategoryError categoryError = new CategoryError();
        String message;
        try {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            HashMap<Integer, Category> categoryMap = (HashMap<Integer, Category>) request.getAttribute("categoryList");
            Category updatedCategory = categoryMap.get(categoryId);
            if (!request.getMethod().equalsIgnoreCase("GET")) {
                String updatedCategoryName = request.getParameter("categoryName");
                if (!updatedCategoryName.matches("^[A-Z][a-zA-Z]{6,18}$")) {
                    isError = true;
                    categoryError.setCategoryNameError("Name must start with a capital letter and have lenght 6-18 characters");
                }
                String updatedDescription = request.getParameter("categoryDescription");
                if (updatedDescription.isEmpty()) {
                    isError = true;
                    categoryError.setCategoryNameError("description cannot be empty");
                }
                if (isError == false) {
                    updatedCategory = new Category(categoryId, updatedCategoryName, updatedDescription);
                    updatedCategory.setCategoryName(updatedCategoryName);
                    updatedCategory.setDescription(updatedDescription);
                    if (categoryDao.updateCategory(updatedCategory)) {
                        message = "category updated succesfully";
                    } else {
                        message = "error updating category pls check the console log for details";
                    }
                    request.setAttribute("message", message);
                    request.setAttribute("category", updatedCategory);

                } else {
                    request.setAttribute("categoryError", categoryError);
                    request.setAttribute("isError", isError);
                    request.setAttribute("category", updatedCategory);
                }
            } else {
                request.setAttribute("category", updatedCategory);
            }
        } catch (Exception e) {
            log("error at update category Servlet: " + e.getMessage());
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

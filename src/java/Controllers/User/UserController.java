/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.User;

import Model.DAO.UserDAO;
import Model.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private final String loginServlet = "LoginServlet";
    private final String registerServlet = "RegisterServlet";
    private final String viewUserServlet = "ViewUserServlet";
    private final String updateUserServlet = "UpdateUserServlet";
    private final String deleteUserServlet = "DeleteUserServlet";
    private final String signoutServlet = "SignOutServlet";
    private final String createUserServlet = "CreateUserServlet";

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
        String action;
        String url = loginServlet;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        try {
            action = request.getParameter("action").toLowerCase();
            if (action.equals("signin")) {
                url = loginServlet;
            } else if (action.equals("register")) {
                url = registerServlet;
            } else if (action.equals("signout")) {
                url = signoutServlet;
            }
            if (user != null) {
                if (user != null && user.getType() == 0) {
                    UserDAO userDao = new UserDAO();
                    List<User> userList = userDao.viewAllUser();
                    request.setAttribute("userList", userList);
                }
                if (action.equals("view")) {
                    url = viewUserServlet;
                } else if (action.equals("update")) {
                    url = updateUserServlet;
                } else if (action.equals("delete")) {
                    url = deleteUserServlet;
                } else if (action.equals("create")) {
                    url = createUserServlet;
                }

            }

        } catch (Exception e) {
            log("error at usercontroller   " + e.getMessage());
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

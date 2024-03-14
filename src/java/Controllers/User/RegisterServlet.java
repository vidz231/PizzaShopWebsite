/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.User;

import Model.DAO.UserDAO;
import Model.DTO.User;
import Model.DTO.UserError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String registerPage = "register.jsp";
    private final String loginPage = "login.jsp";

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

        User user;
        String url = registerPage;
        String message = "Error register! double check your field";
        boolean isError = false;
        UserError userError = new UserError();
        try {

            if (request.getMethod().equalsIgnoreCase("GET")) {
                url = registerPage;
            } else {
                String username = request.getParameter("txtUserName");
                if (!username.matches("^.{6,18}$")) {
                    isError = true;
                    userError.setUsernameError("Your username input is invalid. Please ensure your username is between 6 and 18 characters long. Let's try again.");
                }
                String password = request.getParameter("txtPassword");
                if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
                    isError = true;
                    userError.setPasswordError("Invalid password. It must contain at least one digit, one lowercase letter, one uppercase letter, one special character (@#$%^&+=), no whitespace, and be at least 8 characters long.");
                }
                String confirmPassword = request.getParameter("txtConfirmPassword");
                if (!password.equals(confirmPassword)) {
                    isError = true;
                    userError.setMatchedPasswordError("confirm password doesn't match.");
                }
                String fullName = request.getParameter("txtFullName");
                if (!fullName.matches("^[A-Z].{0,14}$")) {
                    isError = true;
                    userError.setMatchedPasswordError("Invalid full name. It should start with an uppercase letter and not exceed 15 characters.");
                }
                user = new User(0, username, fullName, password, 1);
                if (isError == false) {
                    UserDAO userDao = new UserDAO();
                    if (userDao.createUser(user)) {
                        message = "user created Successfully";
                    } else {
                        message = "error register user";
                    }
                } else {
                    request.setAttribute("isError", isError);
                    request.setAttribute("userError", userError);
                    request.setAttribute("user", user);
                }
                request.setAttribute("message", message);
            }

        } catch (Exception e) {
            log("error at register:  " + e.getMessage());
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

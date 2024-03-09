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
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {

    private final String updateUserServlet = "UpdateUserSerlvet";
    private final String updateUserPage = "editUser.jsp";

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
        String url;
        String message;
        List<User> userList;
        User user;
        try {
            int id = Integer.parseInt(request.getParameter("userId"));
            if (!request.getMethod().equalsIgnoreCase("GET")) {
                System.out.println("processing post user edit");
                int accountID = Integer.parseInt(request.getParameter("userId"));
                String username = request.getParameter("username");
                String fullName = request.getParameter("fullName");
                String password = request.getParameter("password");
                int type = Integer.parseInt(request.getParameter("type"));
                user = new User();
                user.setAccountID(accountID);
                user.setUsername(username);
                user.setFullName(fullName);
                user.setPassword(password);
                user.setType(type);
                UserDAO userDao = new UserDAO();
                if (userDao.updateUserMethod(user)) {
                    message = "user updated succesfully!";
                    request.setAttribute("message", message);
                    request.setAttribute("user", user);

                } else {
                    request.setAttribute("user", user);
                    message = "error updating user,double check your field and try again.";
                    request.setAttribute("message", message);
                }
            } else {
                userList = (List<User>) request.getAttribute("userList");
                user = userList.stream().filter(filterUser -> filterUser.getAccountID() == id).findFirst().get();
                request.setAttribute("user", user);
            }
            
        } catch (Exception e) {
            log("error at update User" + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(updateUserPage);
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

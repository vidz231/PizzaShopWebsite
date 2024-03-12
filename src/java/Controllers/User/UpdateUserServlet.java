/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.User;

import Model.DAO.CustomerDAO;
import Model.DAO.UserDAO;
import Model.DTO.Customer;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {

    private final String updateUserPage = "editUser.jsp";
    private final String updateProfilePage = "editProfile.jsp";

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
        String url = updateUserPage;
        String message;
        List<User> userList;
        User user;
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        String actionType;
        try {
            if (sessionUser.getType() == 0) {
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
            } else if (sessionUser.getType() == 1) {
                url = updateProfilePage;

                if (request.getMethod().equalsIgnoreCase("GET")) {
                    request.setAttribute("user", sessionUser);
                } else {
                    int accountID = Integer.parseInt(request.getParameter("userId"));
                    String username = request.getParameter("username");
                    String fullName = request.getParameter("fullName");
                    String password = request.getParameter("password");
                    long phone = Long.parseLong(request.getParameter("phone"));
                    String address = request.getParameter("shipAddress");

                    if (request.getParameter("phone") != null && request.getParameter("shipAddress") != null) {
                        CustomerDAO customerDao = new CustomerDAO();
                        Customer customer = new Customer(accountID, password, fullName, address, phone);
                        if (customerDao.getUserByID(accountID) == null) {
                            if (customerDao.createCustomer(customer)) {
                                message = "update profile succesfully";
                                request.setAttribute("message", message);
                            }
                        } else {
                            if (customerDao.updateCustomer(customer)) {
                                System.out.println("customer updated succesfully");
                            } else {
                                System.out.println("error updateing customer");
                            }
                        }
                        session.setAttribute("customer", customer);
                    }
//                int type = Integer.parseInt(request.getParameter("type"));
                    user = new User(accountID, username, fullName, password, 1);
                    UserDAO userDao = new UserDAO();
                    if (userDao.updateUserMethod(user)) {
                        message = "user updated succesfully!";
                        request.setAttribute("message", message);
                        request.setAttribute("user", user);
                        session.setAttribute("user", user);
                        actionType = request.getParameter("actionType");
                        if (actionType == null) {
                            url = updateProfilePage;
                        } else if (actionType.equals("update-bill")) {
                            url = "updateBillingAddress.jsp";
                        }
                    } else {
                        request.setAttribute("user", user);
                        message = "error updating user,double check your field and try again.";
                        request.setAttribute("message", message);
                    }

                }

            }

        } catch (Exception e) {
            log("error at update User" + e.getMessage());
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

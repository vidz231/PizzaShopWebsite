/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Order;

import Model.DAO.OrderDAO;
import Model.DTO.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "UpdateOrderServlet", urlPatterns = {"/UpdateOrderServlet"})
public class UpdateOrderServlet extends HttpServlet {

    private final String editOrderPage = "editOrder.jsp";

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
        String url = editOrderPage;
        String message;
        int orderID;
        int customerID;
        Date orderDate;
        Date requireDate;
        Date shippedDate;
        String freight;
        String shipAddress;
        Order order;
        List<Order> orderList;
        try {
            if (request.getMethod().equalsIgnoreCase("GET")) {
                int paramId = Integer.parseInt(request.getParameter("orderId"));
                orderList = (List<Order>) request.getAttribute("orderList");
                order = orderList.stream()
                        .filter(ord -> ord.getOrderID() == paramId)
                        .findFirst().get();
                request.setAttribute("order", order);
            } else {
                orderID = Integer.parseInt(request.getParameter("orderID"));
                customerID = Integer.parseInt(request.getParameter("customerID"));
                orderDate = Date.valueOf(request.getParameter("orderDate"));
                requireDate = Date.valueOf(request.getParameter("requireDate"));
                shippedDate = Date.valueOf(request.getParameter("shippedDate"));
                freight = request.getParameter("freight");
                shipAddress = request.getParameter("shipAddress");
                order = new Order(orderID, customerID, orderDate, requireDate, shippedDate, freight, shipAddress);
                OrderDAO orderDao = new OrderDAO();
                request.setAttribute("order", order);
                if (orderDao.updateOrder(order)) {
                    message = "order updated successfully";
                    request.setAttribute("message", message);
                } else {
                    message = "error updating order";
                    request.setAttribute("message", message);

                }

            }
        } catch (Exception e) {
            log("errror at update order servlet:   " + e.getMessage());
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

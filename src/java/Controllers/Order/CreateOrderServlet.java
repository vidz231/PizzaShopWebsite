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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/CreateOrderServlet"})
public class CreateOrderServlet extends HttpServlet {

    private final String createOrderPage = "createOrder.jsp";

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
        String url = createOrderPage;
        String message;
        int orderID;
        int customerID;
        Date orderDate;
        Date requireDate;
        Date shippedDate;
        String freight;//don vi giao hang of(shoppe ,grab,gojek, bla bla)
        String shipAddress;
        Order order;
        try {
            orderID = 0;
            customerID = Integer.parseInt(request.getParameter("customerID"));
            orderDate = Date.valueOf(request.getParameter("orderDate"));
            requireDate = Date.valueOf(request.getParameter("requireDate"));
            shippedDate = Date.valueOf(request.getParameter("shippedDate"));
            freight = request.getParameter("freight");
            shipAddress = request.getParameter("shipAddress");
            order = new Order(orderID, customerID, orderDate, requireDate, shippedDate, freight, shipAddress);
            OrderDAO orderDAO = new OrderDAO();
            request.setAttribute("order", order);
            if (orderDAO.createOrder(order)) {
                message = "order created successfully";
                request.setAttribute("message", message);
            } else {
                message = "error creating order,pls double check your field and try again";
                request.setAttribute("message", message);
            }
        } catch (Exception e) {
            log("error at create or servlet: " + e.getMessage());
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

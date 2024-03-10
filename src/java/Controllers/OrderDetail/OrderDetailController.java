/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.OrderDetail;

import Model.DAO.OrderDetailsDAO;
import Model.DTO.OrderDetails;
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
@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetailController"})
public class OrderDetailController extends HttpServlet {

    private final String viewOrderDetailServlet = "ViewOrderDetailServlet";
    private final String updateOrderDetailServlet = "UpdateOrderDetailServlet";
    private final String createOrderDetailServlet = "CreateOrderDetailServlet";
    private final String deleteOrderDetailServlet = "DeleteOrderDetailServlet";

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
        String action = request.getParameter("action").toLowerCase();
        String url = viewOrderDetailServlet;
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        List<OrderDetails> orderDetailList;
        HttpSession session = request.getSession();
        try {
            orderDetailList = orderDetailsDAO.viewAllOrderDetails();
            System.out.println(orderDetailList.toString());
            if (!orderDetailList.isEmpty()) {
                request.setAttribute("orderDetailList", orderDetailList);
            }
            if (action.equals("view")) {
                url = viewOrderDetailServlet;
            } else if (action.equals("update")) {
                url = updateOrderDetailServlet;
            } else if (action.equals("delete")) {
                url = deleteOrderDetailServlet;
            } else if (action.equals("create")) {
                url = createOrderDetailServlet;
            }
        } catch (Exception e) {
            log("error at order detail controller: " + e.getMessage());
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

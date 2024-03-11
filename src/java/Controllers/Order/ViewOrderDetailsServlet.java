/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Order;

import Model.DAO.OrderDAO;
import Model.DAO.OrderDetailsDAO;
import Model.DAO.ProductDAO;
import Model.DTO.Customer;
import Model.DTO.Order;
import Model.DTO.OrderDetails;
import Model.DTO.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
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
@WebServlet(name = "ViewOrderDetailsServlet", urlPatterns = {"/ViewOrderDetailsServlet"})
public class ViewOrderDetailsServlet extends HttpServlet {

    private final String detailsOrderPage = "viewOrderDetails.jsp";
    private final String orderController = "OrderController";

    public HashMap<Integer, Product> getProductMap(List<Product> productList) {
        HashMap<Integer, Product> productMap = new HashMap<>();

        // Populate the map
        for (Product product : productList) {
            productMap.put(product.getProductID(), product);
        }

        return productMap;
    }

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
        HttpSession session = request.getSession();
        String message;
        String url = detailsOrderPage;
        Customer customer;
        HashMap<Integer, Product> productMap = new HashMap<Integer, Product>();
        Order order;
        int totalItem =0;
        double subtotal =0.0;
        try {
            OrderDetailsDAO orderDetailsDao = new OrderDetailsDAO();
            ProductDAO productDao = new ProductDAO();
            productMap = getProductMap(productDao.viewAllProduct());
            OrderDAO orderDAO = new OrderDAO();
            customer = (Customer) session.getAttribute("customer");
            UUID orderID = UUID.fromString(request.getParameter("orderId"));
            List<OrderDetails> orderDetailsList = orderDetailsDao.viewAllOrderDetails();
            List<Order> orderList = orderDAO.viewAllOrder();
            if (orderDetailsList.isEmpty()) {
                url = orderController + "?action=view";
            } else {
                url = detailsOrderPage;
                order = orderList.stream().filter(o -> o.getOrderID().equals(orderID))
                        .findFirst()
                        .get();
                orderDetailsList = orderDetailsList.stream()
                        .filter(od -> od.getOrderID().equals(orderID))
                        .collect(Collectors.toList());
                for(OrderDetails item: orderDetailsList){
                    totalItem += item.getQuantity();
                    subtotal += item.getQuantity()*item.getUnitPrice();
                }
                request.setAttribute("orderDetails", orderDetailsList);
                request.setAttribute("productMap", productMap);
                request.setAttribute("orderObject", order);
                request.setAttribute("totalItem", totalItem);
                request.setAttribute("subtotal", subtotal);
            }

        } catch (Exception e) {
            log("error at order details servlet: " + e.getMessage());
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

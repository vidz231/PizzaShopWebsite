/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Order;

import Model.DAO.OrderDAO;
import Model.DAO.OrderDetailsDAO;
import Model.DTO.CartItem;
import Model.DTO.Customer;
import Model.DTO.Order;
import Model.DTO.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/CreateOrderServlet"})
public class CreateOrderServlet extends HttpServlet {

    private final String createOrderPage = "viewCart.jsp";
    private final String updateBillingAddressPage = "updateBillingAddress.jsp";
    private final String viewCartPage = "CartController?action=viewCart.jsp";

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
        UUID orderID;
        int customerID;
        Date orderDate;
        Date requireDate;
        Date shippedDate;
        String freight;//don vi giao hang of(shoppe ,grab,gojek, bla bla)
        String shipAddress;
        Order order;
        HttpSession session = request.getSession();
        HashMap<Integer, CartItem> cartMap;
        List<String> freightList = new ArrayList<>();
        freightList.add("grab");
        freightList.add("shoppe");
        freightList.add("giao hang nhanh");

        Random random = new Random();
        try {
            cartMap = (HashMap<Integer, CartItem>) session.getAttribute("Cart");
            if (cartMap.isEmpty()) {
                url = viewCartPage;
                message = "no item detected in cart";
            } else {

                OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
                OrderDetails orderDetails;
                Customer customer = (Customer) session.getAttribute("customer");
                if (customer == null) {
                    url = updateBillingAddressPage;
                } else {

                    orderID = UUID.randomUUID();
                    LocalDate orderDateLocal = LocalDate.now();
                    LocalDate requireDateLocal = orderDateLocal.plus(15, ChronoUnit.DAYS);
                    LocalDate shippedDateLocal = orderDateLocal.plus(ThreadLocalRandom.current().nextInt(1, 31), ChronoUnit.DAYS);
                    orderDate = Date.valueOf(orderDateLocal);
                    requireDate = Date.valueOf(requireDateLocal);
                    shippedDate = Date.valueOf(shippedDateLocal);
                    int freightChoice = random.nextInt(3) + 1;
                    freight = freightList.get(freightChoice - 1);
                    shipAddress = customer.getAddress();
                    order = new Order(orderID, customer.getId(), orderDate, requireDate, shippedDate, freight, shipAddress);

                    OrderDAO orderDAO = new OrderDAO();
                    request.setAttribute("order", order);
                    if (orderDAO.createOrder(order)) {
                        message = "order created successfully";
                        for (Map.Entry<Integer, CartItem> entry : cartMap.entrySet()) {
                            CartItem value = entry.getValue();
                            orderDetails = new OrderDetails(orderID, value.getItemId(), value.getUnitPrice(), value.getQuantity());
                            orderDetailsDAO.createOrderDetail(orderDetails);
                        }
                        session.removeAttribute("Cart");
                        session.removeAttribute("itemCount");
                        session.removeAttribute("subTotal");

                        request.setAttribute("message", message);
                    } else {
                        message = "error creating order,pls double check your field and try again";
                        request.setAttribute("message", message);
                    }
                }
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

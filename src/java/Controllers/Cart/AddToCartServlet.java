/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cart;

import Model.DTO.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String landingPage = "ProductController?action=view";

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
        HashMap<Integer, CartItem> cart;
        String url = landingPage;
        String message;
        int itemId;
        String itemName;
        int quantity;
        double unitPrice;
        CartItem cartItem;
        try {
            if (!request.getParameter("filterByCategory").isEmpty()) {
                int filterByCategory = Integer.parseInt(request.getParameter("filterByCategory"));
                url = landingPage + "&filterByCatgory=" + String.valueOf(filterByCategory);
            }
            itemId = Integer.parseInt(request.getParameter("itemId"));
            itemName = request.getParameter("itemName");
            quantity = Integer.parseInt(request.getParameter("quantity"));
            unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
            cartItem = new CartItem(itemId, itemName, quantity, unitPrice);
            if (session.getAttribute("Cart") == null) {
                cart = new HashMap<>();
                cart.put(itemId, cartItem);
                session.setAttribute("Cart", cart);
                message = "item added to cart";
                request.setAttribute("message", message);
            } else {
                cart = (HashMap<Integer, CartItem>) session.getAttribute("Cart");
                if (cart.get(itemId) != null) {
                    cart.get(itemId).setQuantity(cart.get(itemId).getQuantity() + 1);
                    message = "cart increase successfully";
                    request.setAttribute("message", message);
                } else {
                    cart.put(itemId, cartItem);
                    message = "item added to cart";
                    request.setAttribute("message", message);
                }
                session.setAttribute("Cart", cart);
            }
        } catch (Exception e) {
            log("error at add to cart servlet: " + e.getMessage());
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

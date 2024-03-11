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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "ViewCartServlet", urlPatterns = {"/ViewCartServlet"})
public class ViewCartServlet extends HttpServlet {

    private final String viewCartPage = "viewCart.jsp";

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
        String url = viewCartPage;
        String message;
        HashMap<Integer, CartItem> cartMap;
        List<CartItem> cartList = null;
        HttpSession session = request.getSession();
        int itemCount = 0;
        Double subTotal = 0.0;
        HashMap<Integer, CartItem> cart = null;
        Cookie cookieCart = null;
        try {
            cartMap = (HashMap<Integer, CartItem>) session.getAttribute("Cart");
            if (cartMap == null) {
                cookieCart = CartUtils.getCookieByName(request, "Cart");
                if (cookieCart != null) {
                    //get Cart from cookies
                    cart = CartUtils.getCartFromCookie(cookieCart);
                    if (cart != null) {
                        cartList = new ArrayList<CartItem>(cart.values());
                        session.setAttribute("Cart", cart);
                    }
                    for (CartItem ct : cartList) {
                        subTotal += ct.getUnitPrice() * ct.getQuantity();
                        itemCount += ct.getQuantity();
                    }
                }
            } else {
                cartList = new ArrayList<>(cartMap.values());
                for (CartItem ct : cartList) {
                    subTotal += ct.getUnitPrice() * ct.getQuantity();
                    itemCount += ct.getQuantity();
                }
            }

            session.setAttribute("subTotal", subTotal);
            session.setAttribute("itemCount", itemCount);
            request.setAttribute("cartList", cartList);
        } catch (Exception e) {
            log("error at view cart Servlet : " + e.getMessage());
        } finally {
            request.getRequestDispatcher(viewCartPage).forward(request, response);
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

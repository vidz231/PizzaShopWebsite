/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cart;

import Model.DTO.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/UpdateCartServlet"})
public class UpdateCartServlet extends HttpServlet {

    private final String viewCartPage = "CartController?action=view";

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
        String updateOps;
        int itemID;
        HttpSession session = request.getSession();
        HashMap<Integer, CartItem> cartMap;
        try {
            updateOps = request.getParameter("updateOps");
            cartMap = (HashMap<Integer, CartItem>) session.getAttribute("Cart");
            itemID = Integer.parseInt(request.getParameter("itemId"));
            System.out.println(updateOps);
            if (cartMap != null) {
                switch (updateOps) {
                    case "+":
                        cartMap.get(itemID).setQuantity(cartMap.get(itemID).getQuantity() + 1);
                        break;
                    case "-":
                        cartMap.get(itemID).setQuantity(cartMap.get(itemID).getQuantity() - 1);
                        if (cartMap.get(itemID).getQuantity() <= 0) {
                            cartMap.remove(itemID);
                        }
                        break;
                    default:
                        log("no param passed");
                        break;
                }
                if (cartMap.isEmpty()) {
                    session.removeAttribute("Cart");
                } else {
                    session.setAttribute("Cart", cartMap);
                }
            }

        } catch (Exception e) {
            log("error at update cart servlet :     " + e.getMessage());
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

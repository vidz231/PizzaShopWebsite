/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Order;

import Model.DAO.CustomQueryHandlerDAO;
import Model.DAO.OrderDetailsDAO;
import Model.DAO.ProductDAO;
import Model.DTO.Order;
import Model.DTO.OrderDetails;
import Model.DTO.Product;
import Model.DTO.TotalSale;
import Utils.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRUNG VI
 */
@WebServlet(name = "ViewSaleStatsServlet", urlPatterns = {"/ViewSaleStatsServlet"})
public class ViewSaleStatsServlet extends HttpServlet {

    private final String saleStatsPage = "saleStats.jsp";

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
        Date startDate;
        Date endDate;
        LocalDate localDate = LocalDate.now();
        List<OrderDetails> orderDetailsList;
        List<OrderDetails> resultOrderDetailsList = new ArrayList<>();

        try {
            ProductDAO productDao = new ProductDAO();
            HashMap<Integer, Product> productMap = Utility.getProductMap(productDao.viewAllProduct());
            CustomQueryHandlerDAO customQueryDao = new CustomQueryHandlerDAO();
            List<TotalSale> totalSaleList = customQueryDao.getAllProduct();
            if (request.getMethod().equalsIgnoreCase("GET")) {
                startDate = Date.valueOf(localDate);
                endDate = Date.valueOf(localDate);
            } else {
                startDate = Date.valueOf(request.getParameter("startDate"));
                endDate = Date.valueOf(request.getParameter("endDate"));
            }
            totalSaleList = totalSaleList.stream()
                    .filter(ts -> ts.getOrderDate().getTime() <= endDate.getTime()
                    && ts.getOrderDate().getTime() >= startDate.getTime())
                    .collect(Collectors.toList());
            totalSaleList.sort((o1, o2) -> o2.getOrderDate().compareTo(o1.getOrderDate()));
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("productMap", productMap);
            request.setAttribute("totalSaleList", totalSaleList);
        } catch (Exception e) {
            log("error at sale stats servlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(saleStatsPage).forward(request, response);
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

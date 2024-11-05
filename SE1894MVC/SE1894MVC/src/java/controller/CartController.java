/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;
import model.DAOCart;

/**
 *
 * @author 84968
 * 
 * 
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartURL"})
public class CartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCart dao = new DAOCart();
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service.equals("addToCart")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Cart newcart = dao.getCart(pid);
                //check pid
                if (session.getAttribute(pid + "") == null) {// first time 
                    newcart.setQuantity(1);
                    session.setAttribute(pid + "", newcart);
                } else {
                    Cart oldCart = (Cart) session.getAttribute(pid + "");
                    oldCart.setQuantity(oldCart.getQuantity() + 1);
                    session.setAttribute(pid + "", oldCart);
                }
                response.sendRedirect("ProductURL");

            }

            if (service.equals("showCart")) {
                Vector<Cart> vector = new Vector<>();
                // lay cot key
                Enumeration<String> enu = session.getAttributeNames();

                while (enu.hasMoreElements()) {
                    String pid = enu.nextElement();// pid ~ key
                    Cart cart = (Cart) session.getAttribute(pid + "");
                    vector.add(cart);

                }
                request.setAttribute("vectorCart", vector);
                request.getRequestDispatcher("JSP/showCart.jsp").forward(request, response);

            }

            if (service.equals("removeCart")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Cart cart = (Cart) session.getAttribute(pid + "");

                if (cart != null) {
                    if (cart.getQuantity() > 1) {
                        cart.setQuantity(cart.getQuantity() - 1);
                        session.setAttribute(pid + "", cart); 
                    } else {                       
                        session.removeAttribute(pid + "");
                    }
                }                
                response.sendRedirect("CartURL?service=showCart");
            }

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

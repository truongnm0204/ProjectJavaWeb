/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Bill;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOBill;

/**
 *
 * @author 84968
 */
@WebServlet(name = "BillController", urlPatterns = {"/BillURL"})
public class BillController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            DAOBill dao = new DAOBill();
            String service = request.getParameter("service");
            String id = request.getParameter("id");
            if (service.equals("searchOrderDetailByID")) {
                String sql = "select c.OrderID,c.OrderDate,c.RequiredDate,c.ShippedDate,\n"
                        + "d.ContactName,d.Address,d.Phone,d.Fax,\n"
                        + "a.ProductID,a.ProductName,b.UnitPrice,b.Quantity,b.Discount\n"
                        + "from Products a join [Order Details] b\n"
                        + "	on a.ProductID = b.ProductID join Orders c on c.OrderID\n"
                        + "	= b.OrderID join Customers d\n"
                        + "	on d.CustomerID = c.CustomerID where b.OrderID ="+id;

                Vector<Bill>vector =dao.getBill(sql);
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listBill.jsp");
                request.setAttribute("DataBill", vector);
                dispath.forward(request, response);

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

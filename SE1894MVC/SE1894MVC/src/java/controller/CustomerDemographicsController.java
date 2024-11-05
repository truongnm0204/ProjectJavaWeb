/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CustomerCustomerDemo;
import entity.CustomerDemographics;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomerCustomerDemo;
import model.DAOCustomerDemographics;

/**
 *
 * @author 84968
 */
@WebServlet(name = "CustomerDemographicsController", urlPatterns = {"/CustomerDemographicsURL"})
public class CustomerDemographicsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DAOCustomerDemographics dao = new DAOCustomerDemographics();

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");

            if (service == null) {
                service = "listAll";
            }

            if (service.equals("insertCuDemoGra")) {
                String CustomerTypeID = request.getParameter("CustomerTypeID");
                String CustomerDesc = request.getParameter("CustomerDesc");

              CustomerDemographics  cus = new CustomerDemographics(CustomerTypeID, CustomerDesc);

                int n = dao.addCustomerDemographics(cus);
                response.sendRedirect("CustomerDemographicsURL");
            }

            if (service.equals("listAll")) {
                String submit = request.getParameter("service");
                String sql = "select * from CustomerDemographics";
                if (submit != null) {
                    String CustomerTypeID = request.getParameter("name");
                    sql = "select * from CustomerDemographics where CustomerTypeID like '%" + CustomerTypeID + "%'";
                }
                Vector<CustomerDemographics> vector = dao.getCus(sql);
                request.setAttribute("dataCusDemoGra", vector);
                request.getRequestDispatcher("JSP/listCustomerDemoGra.jsp").forward(request, response);

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

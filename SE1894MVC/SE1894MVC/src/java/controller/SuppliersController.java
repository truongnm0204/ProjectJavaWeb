/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Suppliers;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import model.DAOSuppliers;

/**
 *
 * @author PC
 */
@WebServlet(name = "SuppliersController", urlPatterns = {"/SuppliersURL"})
public class SuppliersController extends HttpServlet {

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
        DAOSuppliers dao = new DAOSuppliers();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllSuppliers";
            }
            if (service.equals("deleteSuppliers")) {
                dao.removeSuppliers(Integer.parseInt(request.getParameter("sid")));
                response.sendRedirect("SuppliersURL?service=listAllSuppliers");
            }
            if (service.equals("insertSuppliers")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/JSP/insertSuppliers.jsp").forward(request, response);
                } else {
                    //int SupplierID = Integer.parseInt(request.getParameter("SupplierID"));
                    String CompanyName = request.getParameter("CompanyName");
                    String ContactName = request.getParameter("ContactName");
                    String ContactTitle = request.getParameter("ContactTitle");
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String Phone = request.getParameter("Phone");
                    String Fax = request.getParameter("Fax");
                    String HomePage = request.getParameter("HomePage");
                    if (CompanyName.equals("")) {
                        out.print("CompanyName empty");
                    }
                    Suppliers sp = new Suppliers(0, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage);
                    int n = dao.addSuppliers(sp);
                    response.sendRedirect("SuppliersURL");
                }
            }
            if (service.equals("updateSuppliers")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int sid = Integer.parseInt(request.getParameter("sid"));
                    Vector<Suppliers> vector = dao.getSuppliers("select * from Suppliers where SupplierID=" + sid);
                    //request.getRequestDispatcher("/JSP/updateSuppliers.jsp").forward(request, response);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateSuppliers.jsp");
                } else {
                    int SupplierID = Integer.parseInt(request.getParameter("SupplierID"));
                    String CompanyName = request.getParameter("CompanyName");
                    String ContactName = request.getParameter("ContactName");
                    String ContactTitle = request.getParameter("ContactTitle");
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String Phone = request.getParameter("Phone");
                    String Fax = request.getParameter("Fax");
                    String HomePage = request.getParameter("HomePage");
                    if (CompanyName.equals("")) {
                        out.print("CompanyName empty");
                    }
                    Suppliers sp = new Suppliers(SupplierID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage);
                    int n = dao.updateSuppliers(sp);
                    response.sendRedirect("SuppliersURL");
                }
            }
            if (service.equals("listAllSuppliers")) {
                String submit = request.getParameter("submit");
                String sql = "select * from Suppliers";
                if (submit != null) {
                    String sname = request.getParameter("sname");
                    sql = "select * from Suppliers where CompanyName like '%" + sname + "%'";
                }
                Vector<Suppliers> vector = dao.getSuppliers(sql);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listSuppliers.jsp");
                request.setAttribute("dataSuppliers", vector);
                request.setAttribute("titleTable", "Suppliers Manage");
                dispath.forward(request, response);
            }
        }
    }

    protected void dispath(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {

        request.getRequestDispatcher(page).forward(request, response);

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

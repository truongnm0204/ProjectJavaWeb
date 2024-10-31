/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCustomer;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerURL"})
public class CustomerController extends HttpServlet {

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
        HttpSession session = request.getSession(true);

        DAOCustomer dao = new DAOCustomer();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllCustomer";
            }
            if (service.equals("deleteCustomer")) {
                dao.removeCustomer(request.getParameter("cid"));
                response.sendRedirect("CustomerURL?service=listAllCustomer");
            }
            if (service.equals("logoutCustomer")) {
                session.invalidate();
                response.sendRedirect("CustomerURL");
            }
            if (service.equals("loginCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/JSP/loginCustomer.jsp").forward(request, response);
                } else {
                    Customer customer = dao.login(request.getParameter("username"),
                            request.getParameter("password"));
                    if (customer == null) {
                        request.setAttribute("error", "login failed");
                        request.getRequestDispatcher("/JSP/loginCustomer.jsp").forward(request, response);
                    } else {
                        session.setAttribute("customer", customer);
                        //request.getRequestDispatcher("/JSP/loginCustomer.jsp").forward(request, response);
                        response.sendRedirect("CustomerURL");
                    }
                }
            }
            if (service.equals("listAllCustomer")) {
                if (service.equals("deleteCustomer")) {
                    dao.removeCustomer(request.getParameter("cid"));
                    response.sendRedirect("CustomerURL?service=listAllCustomer");
                }
            }
            if (service.equals("insertCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    dispath(request, response, "/JSP/insertCustomer.jsp");
                } else {
                    String CustomerID = request.getParameter("CustomerID");
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
                    if (CustomerID.equals("")) {
                        out.print("CustomerID empty");
                    }
                    Customer cs = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                    int n = dao.addCustomer(cs);
                    response.sendRedirect("CustomerURL");
                }
            }
            if (service.equals("updateCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String cid = request.getParameter("cid");
                    Vector<Customer> vector = dao.getCustomers("select * from Customers where CustomerID='" + cid + "'");
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateCustomer.jsp");
                } else {
                    String CustomerID = request.getParameter("CustomerID");
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
                    if (CustomerID.equals("")) {
                        out.print("CustomerID empty");
                    }
                    Customer cs = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                    int n = dao.updateCustomer(cs);
                    response.sendRedirect("CustomerURL");
                }
            }
            if (service.equals("listAllCustomer")) {
                String submit = request.getParameter("submit");
                String sql = "select * from Customers";
                if (submit != null) {
                    String cid = request.getParameter("cid");
                    sql = "select * from Customers where CustomerID like'%" + cid + "%'";
                }
                Vector<Customer> vector = dao.getCustomers(sql);
                request.setAttribute("dataCustomer", vector);
                request.setAttribute("titleTable", "Customer Manage");
                dispath(request, response, "/JSP/listCustomer.jsp");
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

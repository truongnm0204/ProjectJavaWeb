/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Order;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOOrder;

/**
 *
 * @author PC
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderURL"})
public class OrderController extends HttpServlet {

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
        DAOOrder dao = new DAOOrder();

        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllOrder";
        }
        if (service.equals("deleteOrder")) {
            dao.removeOrder(Integer.parseInt(request.getParameter("oid")));
            response.sendRedirect("OrderURL?service=listAllOrder");
        }
        if (service.equals("insertOrder")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                ResultSet rsEmploy = dao.getData("Select EmployeeID, LastName, FirstName from Employees");
                ResultSet rsCus = dao.getData("Select CustomerID, CompanyName from Customers");
                ResultSet rsShip = dao.getData("Select ShipperID, CompanyName from Shippers");
                request.setAttribute("rsEmploy", rsEmploy);
                request.setAttribute("rsCus", rsCus);
                request.setAttribute("rsShip", rsShip);
                request.getRequestDispatcher("/JSP/insertOrder.jsp").forward(request, response);
//                dispath(request, response, "/JSP/insertOrder.jsp");
            } else {
                //int OrderID = Integer.parseInt(request.getParameter("OrderID"));
                String CustomerID = request.getParameter("CustomerID");
                int EmployeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                String OrderDate = request.getParameter("OrderDate");
                String RequiredDate = request.getParameter("RequiredDate");
                String ShippedDate = request.getParameter("ShippedDate");
                int ShipVia = Integer.parseInt(request.getParameter("ShipVia"));
                double Freight = Double.parseDouble(request.getParameter("Freight"));
                String ShipName = request.getParameter("ShipName");
                String ShipAddress = request.getParameter("ShipAddress");
                String ShipCity = request.getParameter("ShipCity");
                String ShipRegion = request.getParameter("ShipRegion");
                String ShipPostalCode = request.getParameter("ShipPostalCode");
                String ShipCountry = request.getParameter("ShipCountry");
                if (CustomerID.equals("")) {
//                    out.print("CustomerID empty");
                }
                Order ord = new Order(0, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                int n = dao.addOrder(ord);
                response.sendRedirect("OrderURL");
            }
        }
        if (service.equals("updateOrder")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int oid = Integer.parseInt(request.getParameter("oid"));
                Vector<Order> vector = dao.getOrder("select * from Orders where OrderID=" + oid);
                ResultSet rsEmploy = dao.getData("Select EmployeeID, LastName, FirstName from Employees");
                ResultSet rsCus = dao.getData("Select CustomerID, CompanyName from Customers");
                ResultSet rsShip = dao.getData("Select ShipperID, CompanyName from Shippers");
                request.setAttribute("rsEmploy", rsEmploy);
                request.setAttribute("rsCus", rsCus);
                request.setAttribute("vector", vector);
                request.setAttribute("rsShip", rsShip);
                dispath(request, response, "/JSP/updateOrder.jsp");
            } else {
                int OrderID = Integer.parseInt(request.getParameter("OrderID"));
                String CustomerID = request.getParameter("CustomerID");
                int EmployeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                String OrderDate = request.getParameter("OrderDate");
                String RequiredDate = request.getParameter("RequiredDate");
                String ShippedDate = request.getParameter("ShippedDate");
                int ShipVia = Integer.parseInt(request.getParameter("ShipVia"));
                double Freight = Double.parseDouble(request.getParameter("Freight"));
                String ShipName = request.getParameter("ShipName");
                String ShipAddress = request.getParameter("ShipAddress");
                String ShipCity = request.getParameter("ShipCity");
                String ShipRegion = request.getParameter("ShipRegion");
                String ShipPostalCode = request.getParameter("ShipPostalCode");
                String ShipCountry = request.getParameter("ShipCountry");
                if (request.getParameter("OrderID") == null) {
//                    out.print("OrderID empty");
                }
                Order ord = new Order(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                int n = dao.updateOrder(ord);
                response.sendRedirect("OrderURL");
            }
        }
        if (service.equals("listAllOrder")) {
            String submit = request.getParameter("submit");
            String sql = "select * from Orders";
            if (submit != null) {
                String oid = request.getParameter("oid");
                sql = "select * from Orders where OrderID like '%" + oid + "%'";
            }
            Vector<Order> vector = dao.getOrder(sql);
            RequestDispatcher dispath
                    = request.getRequestDispatcher("/JSP/listOrder.jsp");
            //set data of view
            request.setAttribute("dataOrder", vector);
            request.setAttribute("titleTable", "Order Manage");
            dispath.forward(request, response);
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

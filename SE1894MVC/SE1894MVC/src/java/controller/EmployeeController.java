/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOEmployee;

/**
 *
 * @author PC
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeURL"})
public class EmployeeController extends HttpServlet {

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
        DAOEmployee dao = new DAOEmployee();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllEmployee";
            }
            if (service.equals("logoutEmployee")) {
                session.invalidate();
                response.sendRedirect("EmployeeURL");
            }
            if (service.equals("loginEmployee")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/JSP/loginEmployee.jsp").forward(request, response);
                } else {
                    Employee emp = dao.loginEmployee(request.getParameter("username"),
                            request.getParameter("password"));
                    if (emp == null) {
                        request.setAttribute("error", "login failed");
                        request.getRequestDispatcher("/JSP/loginEmployee.jsp").forward(request, response);
                    } else {
                        session.setAttribute("employee", emp);
                        //request.getRequestDispatcher("/JSP/loginEmployee.jsp").forward(request, response);
                        response.sendRedirect("EmployeeURL");
                    }
                }
            }
            if (service.equals("deleteEmployee")) {
                dao.removeEmployee(Integer.parseInt(request.getParameter("eid")));
                response.sendRedirect("EmployeeURL?service=listAllEmployee");
            }
            if (service.equals("insertEmployee")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsReportTo = dao.getData("Select EmployeeID,LastName,FirstName,Title,TitleOfCourtesy from Employees");
                    request.setAttribute("rsReportTo", rsReportTo);
                    request.getRequestDispatcher("/JSP/insertEmployee.jsp").forward(request, response);
                }
                String LastName = request.getParameter("LastName");
                String FirstName = request.getParameter("FirstName");
                String Title = request.getParameter("Title");
                String TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                String BirthDate = request.getParameter("BirthDate");
                String HireDate = request.getParameter("HireDate");
                String Address = request.getParameter("Address");
                String City = request.getParameter("City");
                String Region = request.getParameter("Region");
                String PostalCode = request.getParameter("PostalCode");
                String Country = request.getParameter("Country");
                String HomePhone = request.getParameter("HomePhone");
                String Extension = request.getParameter("Extension");
                String Photo = request.getParameter("Photo");
                String Notes = request.getParameter("Notes");
                int ReportsTo = Integer.parseInt(request.getParameter("ReportsTo"));
                String PhotoPath = request.getParameter("PhotoPath");
                Employee ep = new Employee(0, LastName, FirstName, Title,
                        TitleOfCourtesy, BirthDate, HireDate, Address,
                        City, Region, PostalCode, Country,
                        HomePhone, Extension, Photo, Notes,
                        ReportsTo, PhotoPath);

                int n = dao.addEmployee(ep);

                response.sendRedirect("EmployeeURL");
            }
            if (service.equals("updateEmployee")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int eid = Integer.parseInt(request.getParameter("eid"));
                    Vector<Employee> vector = dao.getEmployees("select * from Employees where EmployeeID=" + eid);
                    ResultSet rsReportTo = dao.getData("Select EmployeeID,LastName,FirstName,Title,TitleOfCourtesy from Employees");
                    request.setAttribute("rsReportTo", rsReportTo);
                    request.setAttribute("vector", vector);
                    request.getRequestDispatcher("/JSP/updateEmployee.jsp").forward(request, response);
                } else {
                    String LastName = request.getParameter("LastName");
                    String FirstName = request.getParameter("FirstName");
                    String Title = request.getParameter("Title");
                    String TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                    String BirthDate = request.getParameter("BirthDate");
                    String HireDate = request.getParameter("HireDate");
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String HomePhone = request.getParameter("HomePhone");
                    String Extension = request.getParameter("Extension");
                    String Photo = request.getParameter("Photo");
                    String Notes = request.getParameter("Notes");
                    int ReportsTo = Integer.parseInt(request.getParameter("ReportsTo"));
                    int EmployeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                    String PhotoPath = request.getParameter("PhotoPath");
                    Employee ep = new Employee(EmployeeID, LastName, FirstName, Title,
                            TitleOfCourtesy, BirthDate, HireDate, Address,
                            City, Region, PostalCode, Country,
                            HomePhone, Extension, Photo, Notes,
                            ReportsTo, PhotoPath);

                    int n = dao.updateEmployee(ep);

                    response.sendRedirect("EmployeeURL");
                }
            }
            if (service.equals("listAllEmployee")) {
                String submit = request.getParameter("submit");
                String sql = "select * from Employees";
                if (submit != null) {
                    String epid = request.getParameter("epid");
                    sql = "select * from Employees where EmployeeID like '%" + epid + "%'";
                }
                Vector<Employee> vector = dao.getEmployees(sql);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listEmployee.jsp");
                request.setAttribute("dataEmployee", vector);
                request.setAttribute("titleTable", "Employee Manage");
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

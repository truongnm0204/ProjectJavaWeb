/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import entity.Product;
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
import java.util.List;
import model.DAOOrderDetails;

/**
 *
 * @author PC
 */
@WebServlet(name = "OrdersDetailController", urlPatterns = {"/OrderDetailsURL"})
public class OrdersDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        DAOOrderDetails dao = new DAOOrderDetails();
        //kiem tra xem co list product trong session hay ko()
        HttpSession session = request.getSession();
        List<OrderDetails> listOrderDetails = (List<OrderDetails>) session.getAttribute("listOrderDetails");
        if (listOrderDetails == null) {
            listOrderDetails = dao.findAll();
        }
        // set du lieu vao attribute 
        session.setAttribute("listOrderDetails", listOrderDetails);
        //gui di den list
        if (action.equals("insertOrderDetails")) {
            request.getRequestDispatcher("/JSP/insertOrderDetails.jsp").forward(request, response);

        }
        request.getRequestDispatcher("/JSP/listOrderDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        List<OrderDetails> listOrderDetails;
        switch (action) {
            case "search":
                listOrderDetails = searchOrderDetails(request, response);
                break;
            case "insertOrderDeatils":
                listOrderDetails = insertOrderDetails(request, response);
                break;
            default:
                throw new AssertionError();
        }
        HttpSession session = request.getSession();
        session.setAttribute("listOrderDetails", listOrderDetails);
        response.sendRedirect("OrderDetailsURL");
    }

    private List<OrderDetails> searchOrderDetails(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("oid");
        DAOOrderDetails dao = new DAOOrderDetails();
        List<OrderDetails> list = dao.searchByOrderID(search);
        return list;
    }

    private List<OrderDetails> insertOrderDetails(HttpServletRequest request, HttpServletResponse response) {
        int OrderOID = Integer.parseInt(request.getParameter("oid"));
        int ProductID = Integer.parseInt(request.getParameter("pid"));
        Double UnitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        int Quantity = Integer.parseInt(request.getParameter("quantity"));
        Double Discount = Double.parseDouble(request.getParameter("discount"));
        OrderDetails od = new OrderDetails(OrderOID, ProductID, UnitPrice , Quantity, Discount);
        DAOOrderDetails dao = new DAOOrderDetails();
        dao.insert(od);
        return dao.findAll();   
    }

}

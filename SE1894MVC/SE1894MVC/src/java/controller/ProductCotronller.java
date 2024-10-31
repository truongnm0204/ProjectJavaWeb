/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductCotronller", urlPatterns = {"/ProductURL"})
public class ProductCotronller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOProduct dao = new DAOProduct();
        try (PrintWriter out = response.getWriter()) {
            //display all products
            // service ?
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            if (service.equals("deleteProduct")) {
                dao.removeProduct(Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("ProductURL?service=listAll");
            }
           if (service.equals("updateProduct")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    int pid=Integer.parseInt(request.getParameter("pid"));
                    Vector<Product> vector=
                            dao.getProducts("select * from Products where ProductID="+pid);
                    ResultSet rsSup = dao.getData("SELECT SupplierID,CompanyName  FROM Suppliers");
                    ResultSet rsCate = dao.getData("SELECT CategoryID,CategoryName FROM Categories");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/updateProduct.jsp");
                } else {
                    //if (submit != null) {
                    String ProductName = request.getParameter("ProductName");
                    String SupplierID = request.getParameter("SupplierID");
                    String CategoryID = request.getParameter("CategoryID");
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String UnitsInStock = request.getParameter("UnitsInStock");
                    String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                    String ReorderLevel = request.getParameter("ReorderLevel");
                    String Discontinued = request.getParameter("Discontinued");
                    //check data - validate
                    if (ProductName.equals("")) {
                        out.print("productName empty");
                    }
                    // convert
                    int SupplierId = Integer.parseInt(SupplierID);
                    int CategoryId = Integer.parseInt(CategoryID);
                    int UnitsInStocK = Integer.parseInt(UnitsInStock);
                    int UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder);
                    int ReorderLeveL = Integer.parseInt(ReorderLevel);
                    boolean DiscontinueD = (Integer.parseInt(Discontinued) == 1 ? true : false);
                    double UnitPricE = Double.parseDouble(UnitPrice);
                    int ProductID = 
                            Integer.parseInt(request.getParameter("ProductID"));
                    Product pro = new Product(ProductID, ProductName,
                            SupplierId, CategoryId,
                            QuantityPerUnit, UnitPricE,
                            UnitsInStocK, UnitsOnOrdeR,
                            ReorderLeveL, DiscontinueD);
                    int n = dao.updateProduct(pro);
//               response.sendRedirect("ProductURL?service=listAll");
                    response.sendRedirect("ProductURL");
                }

            }
            if (service.equals("insertProduct")) {
                //get data
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsSup = dao.getData("SELECT SupplierID,CompanyName  FROM Suppliers");
                    ResultSet rsCate = dao.getData("SELECT CategoryID,CategoryName FROM Categories");
                    request.setAttribute("rsSup", rsSup);
                    request.setAttribute("rsCate", rsCate);
                    dispath(request, response, "/JSP/insertProduct.jsp");
                } else {
                    //if (submit != null) {
                    String ProductName = request.getParameter("ProductName");
                    String SupplierID = request.getParameter("SupplierID");
                    String CategoryID = request.getParameter("CategoryID");
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    String UnitPrice = request.getParameter("UnitPrice");
                    String UnitsInStock = request.getParameter("UnitsInStock");
                    String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                    String ReorderLevel = request.getParameter("ReorderLevel");
                    String Discontinued = request.getParameter("Discontinued");
                    //check data - validate
                    if (ProductName.equals("")) {
                        out.print("productName empty");
                    }
                    // convert
                    int SupplierId = Integer.parseInt(SupplierID);
                    int CategoryId = Integer.parseInt(CategoryID);
                    int UnitsInStocK = Integer.parseInt(UnitsInStock);
                    int UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder);
                    int ReorderLeveL = Integer.parseInt(ReorderLevel);
                    boolean DiscontinueD = (Integer.parseInt(Discontinued) == 1 ? true : false);
                    double UnitPricE = Double.parseDouble(UnitPrice);

                    Product pro = new Product(0, ProductName,
                            SupplierId, CategoryId,
                            QuantityPerUnit, UnitPricE,
                            UnitsInStocK, UnitsOnOrdeR,
                            ReorderLeveL, DiscontinueD);
                    int n = dao.addProduct(pro);
//               response.sendRedirect("ProductURL?service=listAll");
                    response.sendRedirect("ProductURL");
                }
            }

            if (service.equals("listAll")) { //request, service
                //call model   
                String submit = request.getParameter("submit");
                String sql = "select * from Products";
                if (submit != null) {  //khong phai search, hien toan bo
                    String pname = request.getParameter("pname");
                    sql = "select * from Products where ProductName like '%" + pname + "%'";
                }
                Vector<Product> vector = dao.getProducts(sql);
                //select view:jsp   
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/JSP/listProduct.jsp");
                //set data for view
                request.setAttribute("dataProduct", vector);
                request.setAttribute("titleTable", "Product Manage");
                //run
                dispath.forward(request, response);

            }
        }
    }
    protected void dispath(HttpServletRequest request, 
            HttpServletResponse response,String page)
            throws ServletException, IOException {
//         RequestDispatcher dis
//                        = request.getRequestDispatcher(page);
//         dis.forward(request, response);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import model.DAOCategories;

/**
 *
 * @author PC
 */
@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesURL"})
public class CategoriesController extends HttpServlet {

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

        DAOCategories dao = new DAOCategories();

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllCategories";
            }//hello :)) 
            if (service.equals("deleteCategories")) {
                dao.removeCategories(Integer.parseInt(request.getParameter("cID")));
                response.sendRedirect("CategoriesURL");
            }

            if (service.equals("insertCategories")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    dispath(request, response, "/JSP/InsertCategories.jsp");
                } else {
                    int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                    String categoryName = request.getParameter("categoryName");
                    String description = request.getParameter("description");
                    String picture = request.getParameter("picture"); // Still a String

                    if (categoryName.equals("")) {
                        out.print("Category name is empty");
                    } else {
                        Categories categories = new Categories(categoryID, categoryName, description, picture);
                        int n = dao.insertCategories(categories);
                        response.sendRedirect("CategoriesURL");
                    }
                }
            }

            if (service.equals("updateCategories")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String cid = request.getParameter("cID");
                    int ciD = Integer.parseInt(cid);
                    Vector<Categories> vector = dao.getCategory("select * from Categories where CategoryID=" + ciD);//true
                    request.setAttribute("vector", vector);
                    dispath(request, response, "/JSP/UpdateCategories.jsp");
                } else {
                    String categoryID = request.getParameter("categoryID");
                    String categoryName = request.getParameter("categoryName");
                    String description = request.getParameter("description");
                    String picture = request.getParameter("picture");
                    int categoryId = Integer.parseInt(categoryID);
                    Categories categories = new Categories(categoryId, categoryName, description, picture);
                    int n = dao.updateCategories(categories);
                    response.sendRedirect("CategoriesURL");
                }
            }
            if (service.equals("listAllCategories")) {
                String submit = request.getParameter("submit");
                String sql = "select * from Categories";
                if (submit != null) {  //khong phai search, hien toan bo
                    String cID = request.getParameter("cID");
                    sql = "select * from Categories where CategoryID =" + cID;
                }
                Vector<Categories> vector = dao.getCategory(sql);
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("/JSP/listCategories.jsp");
                request.setAttribute("dataCategories", vector);
                request.setAttribute("titleTable", "Categories Manage");
                dispatcher.forward(request, response);
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

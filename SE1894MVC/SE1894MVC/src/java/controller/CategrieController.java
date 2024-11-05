/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categorie;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategorie;
import java.sql.ResultSet;
import jakarta.servlet.http.HttpSession;
/**
 *
 * @author 84968
 */
@WebServlet(name = "CategrieController", urlPatterns = {"/CategrieURL"})
public class CategrieController extends HttpServlet {

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
        DAOCategorie dao = new DAOCategorie();
        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {

            Vector<Categorie> vector = dao.getCategorie("select*from Categories");

            String service = request.getParameter("service");
            if (service == null) {
                service = "listAll";
            }
            
            if(service.equals("deleteCategrie")){
                dao.removeCategorie(Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("CategrieURL?service=listAll");
            }
            
            //------------------------------------
            if(service.equals("loginCategorie")){
                String submit = request.getParameter("submit");
                if(submit == null){
                    //request.setAttribute("error", "Login success!!");
                    request.getRequestDispatcher("JSP/loginCategorie.jsp").forward(request, response);
                   
                }else{
                    Categorie cate = dao.login(request.getParameter("username"), request.getParameter("password"));
                    if(cate == null){
                        request.setAttribute("error", "Login Failed!!");
                        request.getRequestDispatcher("JSP/loginCategorie.jsp").forward(request, response);
                        
                    }else{
                      
                        session.setAttribute("categorie", cate);
                        response.sendRedirect("CategrieURL");
                    }
                }
            }
            
            if(service.equals("logoutCategorie")){
                session.invalidate();
               // response.sendRedirect("index.html");
               request.getRequestDispatcher("index.html").forward(request, response);
               
                       
            }
            
            
            
            if (service.equals("updateCategori")) {
                String submit = request.getParameter("submit");
                if(submit == null){
                    
                    int pid =  Integer.parseInt(request.getParameter("pid"));
                    vector = dao.getCategorie("select * from Categories where CategoryID ="+pid);
                    request.setAttribute("vector", vector);
                    
                    request.getRequestDispatcher("JSP/updateCategorie.jsp").forward(request, response);
                    
                }
               else{

                    String CategoryID = request.getParameter("CategoryID");
                    String CategoryName = request.getParameter("CategoryName");
                    String Description = request.getParameter("Description");
                    String Picture = request.getParameter("Picture");
                    String Discontinue = request.getParameter("Discontinue");

                    if (CategoryID.equals("")) {
                        out.print("Categori is Empty!");
                    }

                    int CategoryId = Integer.parseInt(CategoryID);
                    boolean DiscontinueD = (Integer.parseInt(Discontinue) == 1 ? true : false);
                    
                    Categorie cate = new Categorie(CategoryId, CategoryName, Description, Picture,DiscontinueD);
                    int n = dao.updateCategorie(cate);
                    response.sendRedirect("CategrieURL");
                }
            }

            //----------------------------------------
            
            
            if (service.equals("insertCategori")) {
                String submit = request.getParameter("submit");
                if(submit == null){
                    request.getRequestDispatcher("JSP/insertCategorie.jsp")
                    .forward(request, response);
                }
               else{

                    String CategoryID = request.getParameter("CategoryID");
                    String CategoryName = request.getParameter("CategoryName");
                    String Description = request.getParameter("Description");
                    String Picture = request.getParameter("Picture");

                    if (CategoryID.equals("")) {
                        out.print("Categori is Empty!");
                    }

                    int CategoryId = Integer.parseInt(CategoryID);

                    Categorie cate = new Categorie(CategoryId, CategoryName, Description, Picture);
                    int n = dao.addCategorie(cate);
                    response.sendRedirect("CategrieURL");
                }
            }
            //--------------------------------
            if (service.equals("listAll")) {
                String submit = request.getParameter("submit");

                String sql = "select * from Categories";
                if (submit != null) {
                    String pname = request.getParameter("pname");
                    sql = "select * from Categories where CategoryID like '%" + pname + "%'";
                }

                vector = dao.getCategorie(sql);
                RequestDispatcher dispath = request.getRequestDispatcher("JSP/listCategorie.jsp");
                request.setAttribute("DataCategorie", vector);
                dispath.forward(request, response);

            }
        }
    }

    protected void dispath(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher(page);
        dis.forward(request, response);
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

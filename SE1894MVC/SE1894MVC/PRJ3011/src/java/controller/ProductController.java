/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;

@WebServlet(name = "ProductController", urlPatterns = {"/ProductURL"})
public class ProductController extends HttpServlet {

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
        DAOProduct dao = new DAOProduct();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            display all Products

            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllProducts";
            }
            if(service.equals("deleteProduct")){
                String pid=request.getParameter("pid");
                int n=dao.removeProduct(Integer.parseInt(pid));
                response.sendRedirect("ProductURL?service=listAllProducts");
            }
            if(service.equals("insertProduct")){
                //get data
                String product_id=request.getParameter("product_id");
                String product_name=request.getParameter("product_name");
                String model_year=request.getParameter("model_year");
                String list_price=request.getParameter("list_price");
                String brand_name=request.getParameter("brand_name");
                String category_name=request.getParameter("category_name");
                //check data- validate
                if(product_id.equals("")){
                    out.print("product_id is empty");
                }
               // convert
               int product_iD=Integer.parseInt(product_id);
               int model_yeaR=Integer.parseInt(model_year);
               double list_pricE=Double.parseDouble(list_price);
               //
               Product pro=new Product(product_iD, product_name, 
                       model_yeaR, list_pricE, 
                       brand_name, category_name);
               int n=dao.addProduct(pro);
               response.sendRedirect("ProductURL?service=listAllProducts");
                        
            }

            if (service.equals("listAllProducts")) {
               // check submit
                String sql="select * from Products";
                String submit=request.getParameter("submit");
                if(submit!=null){
                    String pname=request.getParameter("pname");
                    sql="select * from Products where product_name like '%"+pname+"%'";
                }
                Vector<Product> vector = dao.getProducts(sql);
                out.print("""
                      <form action="ProductURL" method="get">
                          <p>Search by Name:<input type="text" name="pname" id="">
                              <input type="submit" value="Search" name="submit">
                              <input type="reset" value="Clear">
                              <input type="hidden" name="service" value="listAllProducts">
                          </p>
                      </form>""");
                out.print("""
                      <p><a href="HTML/insertProduct.html">insertProduct</a></p>""");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductController</title>");
                out.println("</head>");
                out.println("<body>");
                out.print("""
                      <table border=1>
                          <caption>Product List</caption>
                          <tr>
                              <th>product_id</th>
                              <th>product_name</th>
                              <th>model_year</th>
                              <th>list_price</th>
                              <th>brand_name</th>
                              <th>category_name</th>
                              <th>update</th>
                              <th>delete</th>
                          </tr>""");
                for (Product product : vector) {
                    out.print("<tr>\n"
                            + "        <td>" + product.getProduct_id() + "</td>\n"
                            + "        <td>" + product.getProduct_name() + "</td>\n"
                            + "        <td>" + product.getModel_year() + "</td>\n"
                            + "        <td>" + product.getList_price() + "</td>\n"
                            + "        <td>" + product.getBrand_name() + "</td>\n"
                            + "        <td>" + product.getCategory_name() + "</td>\n"
                            + "        <td></td>\n"
                            + "        <td><a href=\"ProductURL?service=deleteProduct&pid="+product.getProduct_id()+"\">delete</a> </td>\n"
                            + "    </tr>");
                }
                out.print("</table>");

                //out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
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

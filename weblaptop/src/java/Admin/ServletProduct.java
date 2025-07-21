/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Admin;

import dal.CategorieDAO;
import dal.ProductDAO;
import dal.RoleDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Vector;
import models.Categories;
import models.Products;

/**
 *
 * @author THIS PC
 */
@WebServlet(name="ServletProduct", urlPatterns={"/ServletProduct"})
public class ServletProduct extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO dao = new ProductDAO();
        RoleDAO rdao = new RoleDAO();
        UserDAO udao = new UserDAO();
        CategorieDAO cDAO = new CategorieDAO();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        
        if (service == null) {
            service = "listProduct";
        }
        
        if (service.equals("editProduct")) {
            int pID = Integer.parseInt(request.getParameter("pid"));
            Products product = dao.searchProduct(pID);
            Vector<Categories> listc = cDAO.getAllCategorie("SELECT * FROM [dbo].[tblCategories]");
            Vector<Products> listP = dao.getAllProduct("SELECT * FROM [dbo].[tblProducts]");

            request.setAttribute("editProduct", product);
            request.setAttribute("listc", listc);
            request.setAttribute("listP", listP);
            request.getRequestDispatcher("jsp/Products.jsp").forward(request, response);
        }
        
        if (service.equals("updateProduct")) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String productName = request.getParameter("productName"),
                   image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("price"));
            String processor = request.getParameter("processor"),
                   graphique = request.getParameter("graphique"),
                   computerScreen = request.getParameter("computerScreen"),
                   RAM = request.getParameter("RAM"),
                   memory = request.getParameter("memory"),
                   operatingSystem = request.getParameter("operatingSystem"),
                   color = request.getParameter("color");
            int quantity = Integer.parseInt(request.getParameter("quantity")),
                categoryID = Integer.parseInt(request.getParameter("categoryID"));
            Date importDate = Date.valueOf(request.getParameter("importDate")),
                 usingDate = Date.valueOf(request.getParameter("usingDate"));
            int status = Integer.parseInt(request.getParameter("status"));
            Products p = new Products(productID, productName, image, price, processor, graphique,
                    computerScreen, RAM, memory, operatingSystem, color, quantity, categoryID,
                    importDate, usingDate, status);
            dao.updateProduct(p);
            response.sendRedirect("ServletProduct");
        }
        
        if (service.equals("addProduct")) {
            String productName = request.getParameter("productName"),
                        image = request.getParameter("image");
                double price = Double.parseDouble(request.getParameter("price"));
                String processor = request.getParameter("processor"),
                        graphique = request.getParameter("graphique"),
                        computerScreen = request.getParameter("computerScreen"),
                        RAM = request.getParameter("RAM"),
                        memory = request.getParameter("memory"),
                        operatingSystem = request.getParameter("operatingSystem"),
                        color = request.getParameter("color");
                int quantity = Integer.parseInt(request.getParameter("quantity")),
                        categoryID = Integer.parseInt(request.getParameter("categoryID"));
                Date importDate = Date.valueOf(request.getParameter("importDate")),
                        usingDate = Date.valueOf(request.getParameter("usingDate"));
                int status = Integer.parseInt(request.getParameter("status"));
                Products p = new Products(productName, image, price, processor, graphique,
                        computerScreen, RAM, memory, operatingSystem, color, quantity, categoryID
                        , importDate, usingDate, status); 
                
                dao.insertProduct(p);                               
                response.sendRedirect("ServletProduct");
        }
        
        if (service.equals("deleteProduct")) {
            int pID = Integer.parseInt(request.getParameter("pid"));
            dao.deleteProduct(pID);
            response.sendRedirect("ServletProduct");
        }
        
        if (service.equals("listProduct")) {
            String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblProducts]";
            String submit = request.getParameter("submit");
            String Pname = request.getParameter("productName");
            if (Pname == null) {
                Pname = "";
            }
            //call models
            Vector<Products> list;
            if (submit == null) {
                list = dao.getAllProduct(SQL);
            } else {
                list = dao.getAllProduct("SELECT *\n"
                        + "  FROM [dbo].[tblProducts]\n"
                        + "  where productName like N'%" + Pname + "%'");
            }
            
            //add
            Vector<Categories> listc = cDAO.getAllCategorie("SELECT *\n"
                        + "  FROM [dbo].[tblCategories]");

            //set data for view
            request.setAttribute("listc", listc);
            request.setAttribute("listP", list);
            request.setAttribute("productName", Pname);
            //select viewre

            request.getRequestDispatcher("jsp/Products.jsp").forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

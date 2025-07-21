/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CategorieDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import models.Categories;
import models.Products;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "ServletCategorieJSP", urlPatterns = {"/ServletCategorieJSP"})
public class ServletCategorieJSP extends HttpServlet {

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
        CategorieDAO dao = new CategorieDAO();
        ProductDAO pdao = new ProductDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listCategorie";
        }

        if (service.equals("typecategory")) {
            String sqlc = "SELECT *\n"
                    + "  FROM [dbo].[tblCategories]";

            int cID = Integer.parseInt(request.getParameter("cid"));
            Vector<Products> list = pdao.getProductByCID(cID);
            Vector<Categories> listc = dao.getAllCategorie(sqlc);
            Vector<Products> last = pdao.getLast();

            request.setAttribute("listCC", listc);
            request.setAttribute("last", last);
            request.setAttribute("listP", list);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }

        if (service.equals("EditCategorie")) {
            int ucid = Integer.parseInt(request.getParameter("ucid"));
            Categories c = dao.searchCategorie(ucid);
            String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblCategories]";
            Vector<Categories> list = dao.getAllCategorie(SQL);
            //set data for view
            request.setAttribute("data", list);
            request.setAttribute("i", c);
            request.getRequestDispatcher("jsp/Categories.jsp").
                    forward(request, response);
        }

        if (service.equals("updateCategorie")) {
                int cID = Integer.parseInt(request.getParameter("categoryID"));
                String categoryName = request.getParameter("categoryName"),
                        describe = request.getParameter("describe");
                Categories c = new Categories(cID, categoryName, describe);
                dao.updateCategorie(c);

                response.sendRedirect("ServletCategorieJSP");
        }

        if (service.equals("addCategorie")) {
            String categoryName = request.getParameter("categoryName");
            String describe = request.getParameter("describe");
            Categories c = new Categories(categoryName, describe);

            dao.insertCategorie(c);
            response.sendRedirect("ServletCategorieJSP");

        }

        if (service.equals("deleteCategorie")) {
            int cID = Integer.parseInt(request.getParameter("cid"));
            int n = dao.deleteCategorie(cID);
            if (n == 1) {
                String SQL = "SELECT *\n"
                        + "  FROM [dbo].[tblCategories]";
                Vector<Categories> list = dao.getAllCategorie(SQL);

                request.setAttribute("check", "Cannot delete this Categorie!");
                request.setAttribute("checkid", cID);
                request.setAttribute("data", list);
                request.getRequestDispatcher("jsp/Categories.jsp").forward(request, response);
                return;
            }
            response.sendRedirect("ServletCategorieJSP");
        }

        if (service.equals("listCategorie")) {
            String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblCategories]";
            String submit = request.getParameter("submit");
            String Cname = request.getParameter("categoryName");
            if (Cname == null) {
                Cname = "";
            }
            Vector<Categories> list;
            if (submit == null) {
                list = dao.getAllCategorie(SQL);
            } else {
                list = dao.getAllCategorie("SELECT *\n"
                        + "  FROM [dbo].[tblCategories]\n"
                        + "  where categoryName like N'%" + Cname + "%'");
            }

            request.setAttribute("data", list);
            request.setAttribute("categoryName", Cname);
            request.setAttribute("pageTile", "Categorie Manager");
            request.setAttribute("tableTile", "List of Categorie");
            request.getRequestDispatcher("jsp/Categories.jsp").forward(request, response);
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

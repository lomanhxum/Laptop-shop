/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CategorieDAO;
import dal.ProductDAO;
import dal.RoleDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Enumeration;
import java.util.Vector;
import models.Categories;
import models.Products;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "ServletProductJ_SP", urlPatterns = {"/ServletProductJSP"}, initParams = {
    @WebInitParam(name = "URL", value = "URMDemo"),
    @WebInitParam(name = "userName", value = "sa"),
    @WebInitParam(name = "pass", value = "123")})
public class ServletProductJSP extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO dao = new ProductDAO();
        RoleDAO rdao = new RoleDAO();
        UserDAO udao = new UserDAO();
        CategorieDAO cDAO = new CategorieDAO();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        String sqlc = "SELECT *\n"
                + "  FROM [dbo].[tblCategories]";
        if (service == null) {
            service = "listProduct";
        }

        if (service.equals("DescribeProduct")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            Products p = dao.searchProduct(pid);
            Vector<Categories> listc = cDAO.getAllCategorie(sqlc);
            Vector<Products> last = dao.getLast();

            request.setAttribute("DescribeP", p);
            request.setAttribute("listCC", listc);
            request.setAttribute("last", last);
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
        }

        if (service.equals("checkRoleId")) {
            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (key.equals("userID")) {
                    String uid = (String) session.getAttribute(key);
                    int roleID = udao.SearchRoleID(uid);
                    if (roleID == 1) {
                        response.sendRedirect("login.html");
                        return;
                    } else {
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

                        //set data for view
                        request.setAttribute("data", list);
                        request.setAttribute("productName", Pname);
                        request.setAttribute("pageTile", "Product Manager");
                        request.setAttribute("tableTile", "List of Product");
                        //select viewre
                        request.getRequestDispatcher("jsp/ProductCustomer.jsp").forward(request, response);
                    }
                }
            }
        }

        if (service.equals("updatepoduct")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int upid = Integer.parseInt(request.getParameter("upid"));
                Products p = dao.searchProduct(upid);
                Vector<Categories> vector = cDAO.getAllCategorie("SELECT *\n"
                        + "  FROM [dbo].[tblCategories]");
                //set data for view
                request.setAttribute("vector", vector);
                request.setAttribute("i", p);
                request.getRequestDispatcher("jsp/UpdateProduct.jsp").
                        forward(request, response);
            } else {
                /*private int productID;
    private String productName, image;
    private double price;
    private String processor, graphique, computerScreen, RAM, memory, operatingSystem, color;
    private int quantity, categoryID, SupplierID;
    private Date importDate, usingDate;
    private int status;*/
                int pID = Integer.parseInt(request.getParameter("productID"));
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
                Products p = new Products(pID, productName, image, price, processor, graphique,
                        computerScreen, RAM, memory, operatingSystem, color, quantity, categoryID
                        , importDate, usingDate, status);
                dao.updateProduct(p);

                response.sendRedirect("ServletProductJSP");
            }
        }

        if (service.equals("deleteProduct")) {
            int pID = Integer.parseInt(request.getParameter("pid"));
            dao.deleteProduct(pID);
            response.sendRedirect("ServletProductJSP");
        }

        if (service.equals("addProduct")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                //call model
                Vector<Categories> list = cDAO.getAllCategorie("SELECT *\n"
                        + "  FROM [dbo].[tblCategories]");
                //set data for view
                request.setAttribute("list", list);
                request.getRequestDispatcher("jsp/addProduct.jsp").forward(request, response);
            } else {
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

                //set data for view
                /*String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblProducts]";
                Vector<Products> list = dao.getAllProduct(SQL);
                request.setAttribute("data", list);
                request.setAttribute("pageTile", "Product Manager");
                request.setAttribute("tableTile", "List of Product");
                //select viewre
                request.getRequestDispatcher("jsp/ProductJSP.jsp").forward(request, response);*/
                dao.insertProduct(p);
                response.sendRedirect("ServletProductJSP");
            }
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

            Vector<Categories> listc = cDAO.getAllCategorie(sqlc);
            Vector<Products>  last = dao.getLast();

            //set data for view
            request.setAttribute("listP", list);
            request.setAttribute("productName", Pname);
            request.setAttribute("listCC", listc);
            request.setAttribute("last", last);
            //select viewre

            request.getRequestDispatcher("product.jsp").forward(request, response);
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

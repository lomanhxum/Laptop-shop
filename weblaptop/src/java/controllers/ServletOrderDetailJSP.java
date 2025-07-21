/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import models.OrderDetails;
import models.Orders;
import models.Products;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "ServletOrderDetailJSP", urlPatterns = {"/ServletOrderDetailJSP"})
public class ServletOrderDetailJSP extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        OrderDetailDAO dao = new OrderDetailDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listOrderDetail";
        }

        if (service.equals("getProductPrice")) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            ProductDAO pDAO = new ProductDAO();
            Products product = pDAO.searchProduct(productID);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            String json = "{\"price\": " + (product != null ? product.getPrice() : 0) + "}";
            out.print(json);
            out.flush();
            return;
        }

        if (service.equals("EditOrderDetail")) {
            int Odid = Integer.parseInt(request.getParameter("Odid"));
            OrderDetails Od = dao.searchOrderDetail(Odid);
            OrderDAO oDAO = new OrderDAO();
            Vector<Orders> listo = oDAO.getAllOrder("SELECT *\n"
                    + "  FROM [dbo].[tblOrders]");

            ProductDAO pDAO = new ProductDAO();
            Vector<Products> listp = pDAO.getAllProduct("SELECT *\n"
                    + "  FROM [dbo].[tblProducts]");
            
            String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblOrderDetails]";
            Vector<OrderDetails> list = dao.getAllOrderDetail(SQL);
            
            request.setAttribute("data", list);
            request.setAttribute("i", Od);
            request.setAttribute("listo", listo);
            request.setAttribute("listp", listp);
            request.getRequestDispatcher("jsp/OrderDetails.jsp").forward(request, response);
        }

        if (service.equals("updateOrderDetail")) {
                int OdID = Integer.parseInt(request.getParameter("detailID"));
                Double price = Double.valueOf(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int productID = Integer.parseInt(request.getParameter("productID"));
                OrderDetails Od = new OrderDetails(OdID, price, quantity, orderID, productID);

                dao.updateOrderDetail(Od);
                response.sendRedirect("ServletOrderDetailJSP");
        }

        if (service.equals("addOrderDetail")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                OrderDAO oDAO = new OrderDAO();
                Vector<Orders> listo = oDAO.getAllOrder("SELECT *\n"
                        + "  FROM [dbo].[tblOrders]");

                ProductDAO pDAO = new ProductDAO();
                Vector<Products> listp = pDAO.getAllProduct("SELECT *\n"
                        + "  FROM [dbo].[tblProducts]");

                request.setAttribute("listo", listo);
                request.setAttribute("listp", listp);
                request.getRequestDispatcher("jsp/addOrderDetail.jsp").forward(request, response);
            } else {
                Double price = Double.valueOf(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int productID = Integer.parseInt(request.getParameter("productID"));
                OrderDetails Od = new OrderDetails(price, quantity, orderID, productID);

                dao.insertOrderDetail(Od);
                response.sendRedirect("ServletOrderDetailJSP");
            }
        }

        if (service.equals("deleteOrderDetail")) {
            int OdID = Integer.parseInt(request.getParameter("id"));
            dao.deleteOrderDetail(OdID);
            response.sendRedirect("ServletOrderDetailJSP");
        }

        if (service.equals("listOrderDetail")) {
            String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblOrderDetails]";
            String submit = request.getParameter("submit");
            String Odprice = request.getParameter("price");
            if (Odprice == null) {
                Odprice = "";
            }
            Vector<OrderDetails> list;
            if (submit == null) {
                list = dao.getAllOrderDetail(SQL);
            } else {
                list = dao.getAllOrderDetail("SELECT *\n"
                        + "  FROM [dbo].[tblOrderDetails]\n"
                        + "  where price like N'%" + Odprice + "%'");
            }
            //set data for view
            request.setAttribute("data", list);
            request.setAttribute("price", Odprice);
            //select viewre
            request.getRequestDispatcher("jsp/OrderDetails.jsp").forward(request, response);
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

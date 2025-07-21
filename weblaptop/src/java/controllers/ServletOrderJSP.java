/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.OrderDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Vector;
import models.Orders;
import models.Users;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "ServletOrderJSP", urlPatterns = {"/ServletOrderJSP"})
public class ServletOrderJSP extends HttpServlet {

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
        OrderDAO dao = new OrderDAO();
        UserDAO uDAO = new UserDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listOrder";
        }
        
        String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblOrders]";

        if (service.equals("getUserTotal")) {
            String userID = request.getParameter("userID");
            double total = dao.getUserOrderTotal(userID);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            String json = "{\"total\": " + total + "}";
            out.print(json);
            out.flush();
            return;
        }
        
        if (service.equals("editOrder")) {
            int orderID = Integer.parseInt(request.getParameter("id"));
            Orders order = dao.searchOrder(orderID);
            Vector<Users> listu = uDAO.getAllUser("SELECT * FROM [dbo].[tblUsers]");
            Vector<Orders> list = dao.getAllOrder(SQL);

            request.setAttribute("order", order);
            request.setAttribute("list", listu);
            request.setAttribute("data", list);
            request.getRequestDispatcher("jsp/Orders.jsp").forward(request, response);
        }

        if (service.equals("updateOrder")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                int oid = Integer.parseInt(request.getParameter("oid"));
                Orders o = dao.searchOrder(oid);

                Vector<Users> vector = uDAO.getAllUser("SELECT *\n"
                        + "  FROM [dbo].[tblUsers]");
                //set data for view
                request.setAttribute("vector", vector);
                request.setAttribute("i", o);
                request.getRequestDispatcher("jsp/UpdateOrder.jsp").
                        forward(request, response);
            } else {
                int oid = Integer.parseInt(request.getParameter("orderID"));
                Date orderDate = Date.valueOf(request.getParameter("orderDate"));
                Double total = Double.valueOf(request.getParameter("total"));
                String userID = request.getParameter("userID");
                int status = Integer.parseInt(request.getParameter("status"));
                Orders o = new Orders(oid, orderDate, total, userID, status);

                dao.updateOrder(o);
                response.sendRedirect("ServletOrderJSP");
            }
        }

        if (service.equals("addOrder")) {
                Date orderDate = Date.valueOf(request.getParameter("orderDate"));
                Double total = Double.valueOf(request.getParameter("total"));
                String userID = request.getParameter("userID");
                int status = Integer.parseInt(request.getParameter("status"));
                Orders o = new Orders(orderDate, total, userID, status);

                dao.insertOrder(o);
                response.sendRedirect("ServletOrderJSP");
        }

        if (service.equals("deleteOrder")) {
            int oID = Integer.parseInt(request.getParameter("id"));
            dao.deleteOrder(oID);
            response.sendRedirect("ServletOrderJSP");
        }

        if (service.equals("listOrder")) {
            
            String submit = request.getParameter("submit");
            String userID = request.getParameter("userID");
            if (userID == null) {
                userID = "";
            }
            Vector<Orders> list;
            if (submit == null) {
                list = dao.getAllOrder(SQL);
            } else {
                list = dao.getAllOrder("SELECT *\n"
                        + "  FROM [dbo].[tblOrders]\n"
                        + "  where userID like N'%" + userID + "%'");
            }

            Vector<Users> listu = uDAO.getAllUser("SELECT *\n"
                    + "  FROM [dbo].[tblUsers]");
            //set data for view
            request.setAttribute("list", listu);
            request.setAttribute("data", list);
            request.setAttribute("userID", userID);
            request.setAttribute("pageTile", "Order Manager");
            request.setAttribute("tableTile", "List of Order");
            //select viewre
            request.getRequestDispatcher("jsp/Orders.jsp").forward(request, response);
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

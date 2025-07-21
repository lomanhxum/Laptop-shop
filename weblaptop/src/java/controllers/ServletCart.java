/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CartDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;
import models.Cart;
import models.Orders;
import models.Products;
import models.Users;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "ServletCart", urlPatterns = {"/ServletCart"})
public class ServletCart extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        UserDAO Udao = new UserDAO();
        CartDAO dao = new CartDAO();
        ProductDAO Pdao = new ProductDAO();
        OrderDAO odao = new OrderDAO();
        Vector<Cart> vector = new Vector<>();

        if (service == null) {
            service = "showcart";
        }

        if (service.equals("Checkout")) {
            if (session.getAttribute("userID") == null) {
                response.sendRedirect("jsp/Login.jsp");
            } else {
                // Lấy thông tin từ form
                String email = request.getParameter("email");
                String total = request.getParameter("total");
                String receiverInfo = request.getParameter("receiverInfo");
                String address = request.getParameter("address");

                // Giả lập mã đơn hàng (bạn có thể tạo logic sinh mã đơn hàng thực tế)
                String orderId = "ORD" + System.currentTimeMillis();

                //update oder
                Double total1 = Double.valueOf(request.getParameter("total"));
                String userID = (String) session.getAttribute("userID");
                Orders o = new Orders(total1, userID);
                int newOrderID = dao.insertOrder(o);

                Enumeration enu = session.getAttributeNames();
                while (enu.hasMoreElements()) {
                    String key = (String) enu.nextElement();
                    if (!key.equals("userID") && !key.equals("roleID")) {
                        Cart cart = (Cart) session.getAttribute(key);
                        vector.add(cart);
                    }
                }

                dao.insertOrderDetail(newOrderID, vector);

                dao.sendEmail(email, orderId, total, receiverInfo, address);

                enu = session.getAttributeNames();
                while (enu.hasMoreElements()) {
                    String key = (String) enu.nextElement();
                    if (!key.equals("userID") && !key.equals("roleID")) {
                        session.removeAttribute(key);
                    }
                }

                response.sendRedirect("checkout-success.jsp");
            }
        }

        if (service.equals("removeall")) {
            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (!key.equals("userID")) {
                    session.removeAttribute(key);
                }
            }
            response.sendRedirect("ServletCart");
        }

        if (service.equals("removeitem")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            session.removeAttribute(pid + "");
            response.sendRedirect("ServletCart");
        }

        if (service.equals("addcart")) {
            try {
                int pid = Integer.parseInt(request.getParameter("productID"));
                Cart cart = dao.getCart(pid);
                Products p = Pdao.searchProduct(pid);
                if (session.getAttribute(pid + "") == null) {
                    cart.setQuantity(1);
                    session.setAttribute(pid + "", cart);
                } else {
                    Cart oldcart = (Cart) session.getAttribute(pid + "");
                    oldcart.setQuantity(oldcart.getQuantity() + 1);
                    session.setAttribute(pid + "", oldcart);
                }
                response.setContentType("text/plain");
                response.getWriter().write("success");
            } catch (Exception e) {
                response.setContentType("text/plain");
                response.getWriter().write("error");
            }
        }

        if (service.equals("increaseQuantity")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            Cart cart = (Cart) session.getAttribute(pid + "");
            if (cart != null) {
                cart.setQuantity(cart.getQuantity() + 1);
                session.setAttribute(pid + "", cart);
            }
            response.sendRedirect("ServletCart");
        }

        if (service.equals("decreaseQuantity")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            Cart cart = (Cart) session.getAttribute(pid + "");
            if (cart != null && cart.getQuantity() > 1) {
                cart.setQuantity(cart.getQuantity() - 1);
                session.setAttribute(pid + "", cart);
            } else if (cart != null && cart.getQuantity() == 1) {
                session.removeAttribute(pid + ""); // Xóa sản phẩm nếu quantity về 0
            }
            response.sendRedirect("ServletCart");
        }

        if (service.equals("showcart")) {
            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (!key.equals("userID") && !key.equals("roleID")) {
                    Cart cart = (Cart) session.getAttribute(key);
                    vector.add(cart);
                }
            }

            Users users = Udao.searchUser((String) session.getAttribute("userID"));

            double total = 0;
            for (Cart cart : vector) {
                total += cart.getPrice() * cart.getQuantity();
            }

            request.setAttribute("total", total);
            request.setAttribute("data", vector);
            request.setAttribute("users", users);
            request.setAttribute("pageTile", "Cart Manager");
            request.setAttribute("tableTile", "List of Cart");
            //select viewre
            request.getRequestDispatcher("shoping-cart.jsp").forward(request, response);
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

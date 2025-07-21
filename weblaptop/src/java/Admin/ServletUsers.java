/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

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
import java.util.Vector;
import models.Roles;
import models.Users;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {

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
        UserDAO dao = new UserDAO();
        RoleDAO uDAO = new RoleDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listUser";
        }

        String SQL = "SELECT *\n"
                + "  FROM [dbo].[tblUsers]";

        if (service.equals("addUser")) {
            Vector<Roles> vector = uDAO.getAllRole("SELECT *\n"
                    + "  FROM [dbo].[tblRoles]");

            Vector<Users> list = dao.getAllUser(SQL);

            String userID = request.getParameter("userID"),
                    fullName = request.getParameter("fullName"),
                    password = request.getParameter("password");
            int roleID = Integer.parseInt(request.getParameter("roleID"));
            String address = request.getParameter("address"),
                    phone = request.getParameter("phone"),
                    email = request.getParameter("email");
            boolean activate = Boolean.parseBoolean(request.getParameter("activate"));
            Users u = new Users(userID, fullName, password, roleID, address, phone, email, activate);

            Users Us = dao.searchUser(userID);
            if (Us != null) {
                String check = "userID already exists!";
                request.setAttribute("check", check);
                request.setAttribute("data", list);
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("jsp/Users.jsp").forward(request, response);
                return;
            }

            dao.insertUser(u);
            response.sendRedirect("ServletUsers");
        }

        if (service.equals("editUser")) {
            String userID = request.getParameter("id");
            Users user = dao.searchUser(userID);
            if (user == null) {
                System.out.println("User not found for userID: " + userID); // Debug
            } else {
                System.out.println("User found: " + user); // Debug
            }
            Vector<Roles> vector = uDAO.getAllRole("SELECT * FROM [dbo].[tblRoles]");
            Vector<Users> list = dao.getAllUser(SQL);

            request.setAttribute("editUser", user);
            request.setAttribute("vector", vector);
            request.setAttribute("data", list);
            request.getRequestDispatcher("jsp/Users.jsp").forward(request, response);
        }

        if (service.equals("updateUser")) {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            int roleID = Integer.parseInt(request.getParameter("roleID"));
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            boolean activate = Boolean.parseBoolean(request.getParameter("activate"));

            Users u = new Users(userID, fullName, password, roleID, address, phone, email, activate);
            dao.updateUsre(u);
            response.sendRedirect("ServletUsers?service=listUser");
        }

        if (service.equals("deleteUser")) {
            String uID = request.getParameter("id");
            dao.deletUser(uID);
            response.sendRedirect("ServletUsers");
        }

        if (service.equals("listUser")) {
            String submit = request.getParameter("submit");
            String Uname = request.getParameter("fullName");
            if (Uname == null) {
                Uname = "";
            }
            Vector<Users> list;
            if (submit == null) {
                list = dao.getAllUser(SQL);
            } else {
                list = dao.getAllUser("SELECT *\n"
                        + "  FROM [dbo].[tblUsers]\n"
                        + "  where fullName like N'%" + Uname + "%'");
            }

            Vector<Roles> vector = uDAO.getAllRole("SELECT *\n"
                    + "  FROM [dbo].[tblRoles]");

            request.setAttribute("vector", vector);
            request.setAttribute("data", list);
            request.setAttribute("fullName", Uname);
            //select viewre
            request.getRequestDispatcher("jsp/Users.jsp").forward(request, response);
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

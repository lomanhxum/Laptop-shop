/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

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
import java.util.Enumeration;
import java.util.Vector;
import java.util.regex.Pattern;
import models.Roles;
import models.Users;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "ServletUserJSP", urlPatterns = {"/ServletUserJSP"})
public class ServletUserJSP extends HttpServlet {

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
        String service = request.getParameter("service");
        if (service == null) {
            service = "listUser";
        }

        if (service.equals("logoutUser")) {
            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (key.equals("userID") || key.equals("roleID")) {
                    session.removeAttribute(key);
                }
            }
            response.sendRedirect("home");
        }

        if (service.equals("loginUser")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
            } else {
                String userID = request.getParameter("userID");
                String password = request.getParameter("password");
                int roleID = dao.SearchRoleID(userID);
                boolean check = dao.checkLogin(userID, password);
                if (check) {
                    session.setAttribute("userID", userID);
                    session.setAttribute("roleID", roleID);
                    request.getRequestDispatcher("home").forward(request, response);
                } else {
                    String message = "Wrong userID or password!";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
                }
            }
        }

        if (service.equals("RegisterUsers")) {
            String userID = request.getParameter("userID"),
                    fullName = request.getParameter("fullName"),
                    password = request.getParameter("password"),
                    confirmPassword = request.getParameter("ConfirmPassword");
            String address = request.getParameter("address"),
                    phone = request.getParameter("phone"),
                    email = request.getParameter("email");
            Users u = new Users(userID, fullName, password, 2, address, phone, email, true);
            String CheckUser = dao.CheckFormat(userID, fullName, password, confirmPassword,
                    2, address, phone, email, true);
            if (CheckUser.equals("Valid")) {
                Users Us = dao.searchUser(userID);
                if (Us != null) {
                    request.setAttribute("mess", "userID already exists!");
                    request.setAttribute("userID", userID);
                    request.setAttribute("fullName", fullName);
                    request.setAttribute("password", password);
                    request.setAttribute("confirmPassword", confirmPassword);
                    request.setAttribute("address", address);
                    request.setAttribute("phone", phone);
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("jsp/Register.jsp").forward(request, response);
                    return;
                }
            } else {
                request.setAttribute("CheckUser", CheckUser);
                request.setAttribute("userID", userID);
                request.setAttribute("fullName", fullName);
                request.setAttribute("password", password);
                request.setAttribute("confirmPassword", confirmPassword);
                request.setAttribute("address", address);
                request.setAttribute("phone", phone);
                request.setAttribute("email", email);
                request.getRequestDispatcher("jsp/Register.jsp").forward(request, response);
                return;
            }
            dao.insertUser(u);
            request.setAttribute("successful", "Registration successful");
            request.getRequestDispatcher("jsp/Register.jsp").forward(request, response);
        }

        if (service.equals("updateUser")) {
            String submit = request.getParameter("submit");
            RoleDAO uDAO = new RoleDAO();
            Vector<Roles> vector = uDAO.getAllRole("SELECT *\n"
                    + "  FROM [dbo].[tblRoles]");
            String Upid = request.getParameter("Uid");
            Users Us = dao.searchUser(Upid);
            if (submit == null) {
                //set data for view
                request.setAttribute("vector", vector);
                request.setAttribute("i", Us);
                request.getRequestDispatcher("jsp/UpdateUser.jsp").
                        forward(request, response);
            } else {
                String userID = request.getParameter("userID"),
                        fullName = request.getParameter("fullName"),
                        password = request.getParameter("password"),
                        confirmPassword = request.getParameter("ConfirmPassword");
                int roleID = Integer.parseInt(request.getParameter("roleID"));
                String address = request.getParameter("address"),
                        phone = request.getParameter("phone"),
                        email = request.getParameter("email");
                boolean activate = Boolean.parseBoolean(request.getParameter("activate"));
                Users u = new Users(userID, fullName, password, roleID, address,
                        phone, email, activate);
                String CheckUser = dao.CheckFormat(userID, fullName, password, confirmPassword, roleID, address, phone, email, activate);
                if (CheckUser.equals("Valid")) {
                    dao.updateUsre(u);
                    response.sendRedirect("ServletUserJSP");
                } else {
                    request.setAttribute("CheckUser", CheckUser);
                    request.setAttribute("vector", vector);
                    request.setAttribute("i", u);
                    request.getRequestDispatcher("jsp/UpdateUser.jsp").
                            forward(request, response);
                }

            }
        }

        if (service.equals("addUser")) {
            String submit = request.getParameter("submit");
            RoleDAO uDAO = new RoleDAO();
            Vector<Roles> vector = uDAO.getAllRole("SELECT *\n"
                    + "  FROM [dbo].[tblRoles]");
            if (submit == null) {
                //set data for view
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("jsp/addUser.jsp").forward(request, response);
            } else {
                String userID = request.getParameter("userID"),
                        fullName = request.getParameter("fullName"),
                        password = request.getParameter("password"),
                        confirmPassword = request.getParameter("ConfirmPassword");
                int roleID = Integer.parseInt(request.getParameter("roleID"));
                String address = request.getParameter("address"),
                        phone = request.getParameter("phone"),
                        email = request.getParameter("email");
                boolean activate = Boolean.parseBoolean(request.getParameter("activate"));
                Users u = new Users(userID, fullName, password, roleID, address, phone, email, activate);
                String CheckUser = dao.CheckFormat(userID, fullName, password, confirmPassword,
                        roleID, address, phone, email, activate);
                if (CheckUser.equals("Valid")) {
                    Users Us = dao.searchUser(userID);
                    if (Us != null) {
                        int n = 1;
                        request.setAttribute("check", n);
                        request.setAttribute("vector", vector);
                        request.getRequestDispatcher("jsp/addUser.jsp").forward(request, response);
                        return;
                    }
                } else {
                    request.setAttribute("vector", vector);
                    request.setAttribute("CheckUser", CheckUser);
                    request.getRequestDispatcher("jsp/addUser.jsp").forward(request, response);
                    return;
                }
                dao.insertUser(u);
                response.sendRedirect("ServletUserJSP");
            }
        }

        if (service.equals("deleteUser")) {
            String uID = request.getParameter("id");
            dao.deletUser(uID);
            response.sendRedirect("ServletUserJSP");
        }

        if (service.equals("listUser")) {
            String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblUsers]";
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

            request.setAttribute("data", list);
            request.setAttribute("fullName", Uname);
            request.setAttribute("pageTile", "User Manager");
            request.setAttribute("tableTile", "List of User");
            //select viewre
            request.getRequestDispatcher("jsp/UserJSP.jsp").forward(request, response);
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

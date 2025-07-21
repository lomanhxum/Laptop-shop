/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import models.Roles;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "ServletRoleJSP", urlPatterns = {"/ServletRoleJSP"})
public class ServletRoleJSP extends HttpServlet {

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
        RoleDAO dao = new RoleDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listRole";
        }

        if (service.equals("addRole")) {
            String roleName = request.getParameter("roleName");
            Roles r = new Roles(roleName);

            dao.insertRole(r);
            response.sendRedirect("ServletRoleJSP");
        }
        
        if (service.equals("EditRole")) {
            int Rid = Integer.parseInt(request.getParameter("Rid"));
                Roles r = dao.searchRole(Rid);
                String SQL = "SELECT *\n"
                        + "  FROM [dbo].[tblRoles]";
                
                Vector<Roles> list = dao.getAllRole(SQL);
                
                request.setAttribute("data", list);
                request.setAttribute("i", r);
                request.getRequestDispatcher("jsp/Roles.jsp").
                        forward(request, response);
        }

        if (service.equals("updateRole")) {
                int Rid = Integer.parseInt(request.getParameter("roleID"));
                String roleName = request.getParameter("roleName");
                Roles r = new Roles(Rid, roleName);
                
                dao.updateRole(r);
                response.sendRedirect("ServletRoleJSP");
        }

        if (service.equals("deleteRole")) {
            int rID = Integer.parseInt(request.getParameter("id"));
            int n = dao.deleteRole(rID);
            if (n == 0) {
                String SQL = "SELECT *\n"
                        + "  FROM [dbo].[tblRoles]";
                
                Vector<Roles> list = dao.getAllRole(SQL);
                
                String check = "Cannot delete this Role!";

                //set data for view
                request.setAttribute("check", check);
                request.setAttribute("data", list);
                //select viewre
                request.getRequestDispatcher("jsp/Roles.jsp").forward(request, response);
            }
            response.sendRedirect("ServletRoleJSP");
        }

        if (service.equals("listRole")) {
            String SQL = "SELECT *\n"
                    + "  FROM [dbo].[tblRoles]";
            String submit = request.getParameter("submit");
            String Rname = request.getParameter("roleName");
            if (Rname == null) {
                Rname = "";
            }
            Vector<Roles> list;
            if (submit == null) {
                list = dao.getAllRole(SQL);
            } else {
                list = dao.getAllRole("SELECT *\n"
                        + "  FROM [dbo].[tblRoles]\n"
                        + "  where roleName like N'%" + Rname + "%'");
            }

            //set data for view
            request.setAttribute("data", list);
            request.setAttribute("roleName", Rname);
            //select viewre
            request.getRequestDispatcher("jsp/Roles.jsp").forward(request, response);
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

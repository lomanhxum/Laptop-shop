/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import models.Roles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAO extends DBContext {

    public Vector<Roles> getAllRole(String sql) {
        Vector<Roles> listRole = new Vector<>();
        PreparedStatement ptm;
        try {
            ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Roles r = new Roles(rs.getInt(1),
                        rs.getString(2));
                listRole.add(r);
            }
        } catch (Exception e) {
        }
        return listRole;
    }

    public void insertRole(Roles r) {
        String sql = "INSERT INTO [dbo].[tblRoles]\n"
                + "           ([roleName])\n"
                + "     VALUES(?)";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, r.getRoleName());
            ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public Roles searchRole(int roleID) {
        String sql = "SELECT * FROM [dbo].[tblRoles]\n"
                + "  WHERE roleID =?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, roleID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Roles pro = new Roles(roleID,
                        rs.getString(2));
                return pro;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public void updateRole(Roles r) {
        String sql = "UPDATE [dbo].[tblRoles]\n"
                + "   SET [roleName] = ?\n"
                + " WHERE roleID =?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, r.getRoleName());
            ptm.setInt(2, r.getRoleID());
            ptm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int deleteRole(int roleID) {
        String sql = "DELETE FROM [dbo].[tblRoles]\n"
                + "      WHERE roleID = ?";
        int n = 1;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, roleID);

            ResultSet rs = getData("select * from tblUsers\n"
                    + "where roleID =" + roleID);
            
            if (rs.next()) {
                n = 0;
                return n;
            }

            ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[tblRoles]";
        RoleDAO pDAO = new RoleDAO();
        Vector<Roles> list = pDAO.getAllRole(sql);
        for (Roles r : list) {
            System.out.println(r);
        }
    }
}

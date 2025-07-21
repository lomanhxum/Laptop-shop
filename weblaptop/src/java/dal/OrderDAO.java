/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import java.sql.Date;
import models.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends DBContext {

    public Vector<Orders> getAllOrder(String sql) {
        Vector<Orders> lisOrder = new Vector<>();
        PreparedStatement ptm;
        try {
            ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Orders o = new Orders(rs.getInt(1),
                        rs.getDate(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5));
                lisOrder.add(o);
            }
        } catch (SQLException ex) {
        }
        return lisOrder;
    }

    public void insertOrder(Orders o) {
        String sql = "INSERT INTO tblOrders (orderDate, total, userID, status) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setDate(1, o.getOrderDate());
            ptm.setDouble(2, o.getTotal()); 
            ptm.setString(3, o.getUserID());
            ptm.setInt(4, o.getStatus());
            ptm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Orders searchOrder(int orderID) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[tblOrders]\n"
                + "  where orderID =?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, orderID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Orders o = new Orders(orderID,
                        rs.getDate(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getInt(5));
                return o;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public void updateOrder(Orders o) {
        String sql = "UPDATE [dbo].[tblOrders]\n"
                + "   SET [orderDate] = ?\n"
                + "      ,[total] = ?\n"
                + "      ,[userID] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE orderID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setDate(1, o.getOrderDate());
            ptm.setDouble(2, o.getTotal());
            ptm.setString(3, o.getUserID());
            ptm.setInt(4, o.getStatus());
            ptm.setInt(5, o.getOrderID());
            ptm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void changeStatus(int orderID, int status) {
        String sql = "UPDATE [dbo].[tblOrders]\n"
                + "   SET [status] = ?\n"
                + " WHERE orderID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, status);
            ptm.setInt(2, orderID);
            ptm.executeUpdate();
        } catch (Exception e) {
        }

    }

    public int deleteOrder(int orderID) {
        String sql = "DELETE FROM [dbo].[tblOrders]\n"
                + "      WHERE orderID = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, orderID);
            ResultSet rs = getData("SELECT *\n"
                    + "  FROM [dbo].[tblOrderDetails]\n"
                    + "  WHERE orderID=" + orderID);
            if (rs.next()) {
                changeStatus(orderID, 0);
                return n;
            }
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public double getUserOrderTotal(String userID) {
        double total = 0;
        String sql = "SELECT SUM(price * quantity) as total "
                + "FROM tblOrderDetails od "
                + "JOIN tblOrders o ON od.orderID = o.orderID "
                + "WHERE o.userID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public static void main(String[] args) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[tblOrders]";
        OrderDAO pDAO = new OrderDAO();
        Vector<Orders> list = pDAO.getAllOrder(sql);
        for (Orders p : list) {
            System.out.println(p);
        }

        Orders psearch = pDAO.searchOrder(1);
        if (psearch != null) {
            pDAO.updateOrder(new Orders(1, new Date(2025 - 1900, 1, 20),
                    123, "U002", 0));
        }
    }
}

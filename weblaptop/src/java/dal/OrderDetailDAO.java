/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import models.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Orders;

public class OrderDetailDAO extends DBContext {

    public Vector<OrderDetails> getAllOrderDetail(String sql) {
        Vector<OrderDetails> listOrderDetail = new Vector<>();
        PreparedStatement ptm;
        try {
            ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                OrderDetails ot = new OrderDetails(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5));
                listOrderDetail.add(ot);
            }
        } catch (SQLException ex) {
        }

        return listOrderDetail;
    }

    public void insertOrderDetail(OrderDetails Od) {
        String sql = "INSERT INTO [dbo].[tblOrderDetails]\n"
                + "           ([price]\n"
                + "           ,[quantity]\n"
                + "           ,[orderID]\n"
                + "           ,[productID])\n"
                + "     VALUES (?,?,?,?)";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setDouble(1, Od.getPrice());
            ptm.setInt(2, Od.getQuantity());
            ptm.setInt(3, Od.getOrderID());
            ptm.setInt(4, Od.getProductID());
            ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public OrderDetails searchOrderDetail(int detailID) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[tblOrderDetails]\n"
                + "  where detailID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, detailID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                OrderDetails Od = new OrderDetails(detailID,
                        rs.getDouble(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5));
                return Od;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public void updateOrderDetail(OrderDetails od) {
        String sql = "UPDATE [dbo].[tblOrderDetails]\n"
                + "   SET [price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[orderID] = ?\n"
                + "      ,[productID] = ?\n"
                + " WHERE detailID = ?";
        
        String sqlp = "UPDATE tblProducts SET quantity = quantity - ? WHERE productID = ?";
        
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setDouble(1, od.getPrice());
            ptm.setInt(2, od.getQuantity());
            ptm.setInt(3, od.getOrderID());
            ptm.setInt(4, od.getProductID());
            ptm.setInt(5, od.getDetailID());
            ptm.executeUpdate();

            // Cập nhật total trong tblOrders
            OrderDAO orderDAO = new OrderDAO();
            Orders order = orderDAO.searchOrder(od.getOrderID());
            if (order != null) {
                double newTotal = calculateOrderTotal(od.getOrderID());
                order.setTotal(newTotal);
                orderDAO.updateOrder(order);
            }
            
            PreparedStatement ptm1 = connection.prepareStatement(sqlp);
                ptm1.setInt(1, od.getQuantity());
                ptm1.setInt(2, od.getProductID());
                ptm1.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private double calculateOrderTotal(int orderID) {
        String sql = "SELECT SUM(price * quantity) as total FROM [dbo].[tblOrderDetails] WHERE orderID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, orderID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deleteOrderDetail(int detailID) {
        String sql = "DELETE FROM [dbo].[tblOrderDetails]\n"
                + "      WHERE detailID = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, detailID);

            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }
}

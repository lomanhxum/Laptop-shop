/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import models.Cart;
import models.OrderDetails;
import models.Orders;
import org.eclipse.jdt.internal.compiler.batch.Main;

/**
 *
 * @author THIS PC
 */
public class CartDAO extends DBContext {

    public Cart getCart(int productID) {
        String sql = "select productID, productName, image, price, quantity\n"
                + "from tblProducts\n"
                + "where productID =?";
        Cart cart = null;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, productID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                cart = new Cart(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        0);
            }
        } catch (Exception e) {

        }
        return cart;
    }

    public int insertOrder(Orders o) {
        String sql = "INSERT INTO tblOrders (orderDate, total, userID, status) "
                + "VALUES (GETDATE(), ?, ?, 1)";
        ResultSet rs = null;
        int newOrderID = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptm.setDouble(1, o.getTotal());
            ptm.setString(2, o.getUserID());
            ptm.executeUpdate();

            rs = ptm.getGeneratedKeys();
            if (rs.next()) {
                newOrderID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOrderID;
    }

    public void insertOrderDetail(int newOrderID, Vector<Cart> list) {
        String sql = "INSERT INTO tblOrderDetails (price, quantity, orderID, productID) VALUES (?, ?, ?, ?)";
        String sqlp = "UPDATE tblProducts SET quantity = quantity - ? WHERE productID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            for (Cart item : list) {
                ptm.setDouble(1, item.getPrice());
                ptm.setInt(2, item.getQuantity());
                ptm.setInt(3, newOrderID);
                ptm.setInt(4, item.getProductID());
                ptm.executeUpdate();

                PreparedStatement ptm1 = connection.prepareStatement(sqlp);
                ptm1.setInt(1, item.getQuantity());
                ptm1.setInt(2, item.getProductID());
                ptm1.executeUpdate();
            }
        } catch (Exception e) {
        }
        
    }

    public void sendEmail(String toEmail, String orderId, String total, String receiverInfo, String address) throws UnsupportedEncodingException {
        String fromEmail = "xumlmhe186012@fpt.edu.vn"; // Thay bằng email của bạn
        String password = "ebxe blkm hzrs teni"; // Thay bằng mật khẩu hoặc App Password

        // Cấu hình SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tạo session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            String subject = MimeUtility.encodeText("Xác nhận đơn hàng #" + orderId, "UTF-8", "B");
            message.setSubject(subject);

            // Nội dung email (HTML)
            String emailBody = "<h1>Cảm ơn bạn đã đặt hàng!</h1>"
                    + "<p><strong>Mã đơn hàng:</strong> " + orderId + "</p>"
                    + "<p><strong>Thông tin người nhận:</strong> " + receiverInfo + "</p>"
                    + "<p><strong>Địa chỉ giao hàng:</strong> " + address + "</p>"
                    + "<p><strong>Tổng tiền:</strong> $" + total + "</p>";
            message.setContent(emailBody, "text/html; charset=utf-8");

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi đến " + toEmail);

        } catch (MessagingException e) {
            System.out.println("Lỗi khi gửi email: " + e.getMessage());
        }
    }
}

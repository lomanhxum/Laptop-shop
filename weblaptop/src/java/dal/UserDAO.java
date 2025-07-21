/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import models.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class UserDAO extends DBContext {

    

    public Vector<Users> getAllUser(String sql) {
        Vector<Users> listUser = new Vector<>();
        PreparedStatement ptm;
        try {
            ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Users u = new Users(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8));
                listUser.add(u);
            }
        } catch (SQLException ex) {
        }
        return listUser;
    }

    public void insertUser(Users u) {
        String sql = "INSERT INTO [dbo].[tblUsers]\n"
                + "           ([userID]\n"
                + "           ,[fullName]\n"
                + "           ,[password]\n"
                + "           ,[roleID]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[activate])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, u.getUserID());
            ptm.setString(2, u.getFullName());
            ptm.setString(3, u.getPassword());
            ptm.setInt(4, u.getRoleID());
            ptm.setString(5, u.getAddress());
            ptm.setString(6, u.getPhone());
            ptm.setString(7, u.getEmail());
            ptm.setBoolean(8, u.isActivate());
            ptm.executeLargeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public Users searchUser(String usersID) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[tblUsers]\n"
                + "  WHERE userID =?";

        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, usersID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Users use = new Users(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8));
                return use;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateUsre(Users u) {
        String sql = "UPDATE [dbo].[tblUsers]\n"
                + "   SET [userID] = ?\n"
                + "      ,[fullName] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[roleID] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[activate] = ?\n"
                + " WHERE userID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, u.getUserID());
            ptm.setString(2, u.getFullName());
            ptm.setString(3, u.getPassword());
            ptm.setInt(4, u.getRoleID());
            ptm.setString(5, u.getAddress());
            ptm.setString(6, u.getPhone());
            ptm.setString(7, u.getEmail());
            ptm.setBoolean(8, u.isActivate());
            ptm.setString(9, u.getUserID());
            ptm.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void changeActivate(String userID, boolean activate) {
        String sql = "UPDATE [dbo].[tblUsers]\n"
                + "   SET [activate] = ?\n"
                + " WHERE userID =?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setBoolean(1, activate);
            ptm.setString(2, userID);
            ptm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int deletUser(String userID) {
        String sql = "DELETE FROM [dbo].[tblUsers]\n"
                + "      WHERE userID =?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, userID);
            ResultSet rs = getData("select * from tblOrderDetails od\n"
                    + "join tblOrders o on od.orderID = o.orderID\n"
                    + "join tblUsers u on o.userID = u.userID\n"
                    + "where u.userID ='" + userID + "'");
            if (rs.next()) {
                changeActivate(userID, false);
                return n;
            }
            ptm.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return n;
    }

    private static final String LOGIN = "SELECT *\n"
            + "  FROM [dbo].[tblUsers]\n"
            + "  WHERE userID=? AND password=?";

    public boolean checkLogin(String userID, String password) {
        boolean check = false;
        try {
            PreparedStatement ptm = connection.prepareStatement(LOGIN);
            ptm.setString(1, userID);
            ptm.setString(2, password);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return check;
    }

    public int SearchRoleID(String userID) {
        String sql = "select roleID\n"
                + "from tblUsers\n"
                + "where userID = ?";
        int roleID = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, userID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                roleID = rs.getInt("roleID");
            }
        } catch (Exception e) {
        }
        return roleID;
    }

    public String CheckFormat(String userID, String fullName, String password, String confirmPassword,
            int roleID, String address, String phone, String email, boolean activate) {
        // Kiểm tra userID (ví dụ: phải là chữ cái hoặc số, độ dài 4-20)
        if (userID == null || !Pattern.matches("^[a-zA-Z0-9]{3,20}$", userID)) {
            return "UserID must be 3-20 characters long and contain only letters or numbers!";
        }

        // Kiểm tra fullName 
        if (fullName == null || !Pattern.matches("^[\\p{L}\\s'-]{3,50}$", fullName)) {
            return "Full name must be 3-50 characters long and contain only letters, spaces, asdf, and hyphens!";
        }

        // Kiểm tra phone (ví dụ: định dạng số điện thoại Việt Nam 10-11 số, bắt đầu bằng 0)
        if (phone == null || !Pattern.matches("^0[0-9]{9,10}$", phone)) {
            return "Phone number must start with 0 and be 10-11 digits long!";
        }

        // Kiểm tra email (định dạng email chuẩn)
        //String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.endsWith("@gmail.com")) {
            return "Invalid email format.";
        }

        // Kiểm tra address (không rỗng, độ dài tối đa 100 ký tự)
        if (address == null || address.trim().isEmpty() || address.length() > 100) {
            return "Address cannot be empty and must be less than 100 characters!";
        }

        // Kiểm tra password (ít nhất 8 ký tự, chứa ít nhất 1 chữ cái in hoa, 1 chữ thường, 1 số, 1 ký tự đặc biệt)
        if (password == null || !Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", password)) {
            return "Password must be at least 8 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character (@$!%*?&).";
        }

        if (!password.equals(confirmPassword)) {
            return "Password and Confirm Password are not the same!";
        }
        // Nếu tất cả đều hợp lệ
        return "Valid";
    }

    public static void main(String[] args) {
        String sql = "SELECT [userID]\n"
                + "      ,[fullName]\n"
                + "      ,[password]\n"
                + "      ,[roleID]\n"
                + "      ,[address]\n"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[activate]\n"
                + "  FROM [dbo].[tblUsers]";
        UserDAO uDAO = new UserDAO();
        Vector<Users> listu = uDAO.getAllUser(sql);
        /*for(Users u : listu){
            System.out.println(u);
        } */
 /*Users use = new Users("xum","lo manh xum", "0122", 1,"son la",
                "097xxxx", "xum@gmail.com", true);
        uDAO.insertUser(use);
        listu = uDAO.getAllUser(sql);
        for (Users u : listu) {
            System.out.println(u);
        }*/

 /*Users uSearch = uDAO.searchUser("U002");
        if(uSearch != null){
            System.out.println("found at: " + uSearch.getUserID());
            uDAO.updateUsre(new Users("U002", "bala",
                    "123", 2, "tay nguyen",
                    "03xxxx", "bala@gmali.com", true));
            System.out.println("Updated");
            }else{
            System.out.println("Not Found!");
        }*/
        int n = uDAO.deletUser("U005");
        System.out.println(n);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import models.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategorieDAO extends DBContext {

    public Vector<Categories> getAllCategorie(String sql) {
        Vector<Categories> listCategorie = new Vector<>();
        PreparedStatement ptm;
        try {
            ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                listCategorie.add(c);
            }
        } catch (Exception e) {
        }
        return listCategorie;
    }

    public void insertCategorie(Categories c) {
        String sql = "INSERT INTO [dbo].[tblCategories]\n"
                + "           ([categoryName]\n"
                + "           ,[describe])\n"
                + "     VALUES (?,?)";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, c.getCategoryName());
            ptm.setString(2, c.getDescribe());
            ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public Categories searchCategorie(int categoryID) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[tblCategories]\n"
                + "  where categoryID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, categoryID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Categories cate = new Categories(categoryID,
                        rs.getString(2),
                        rs.getString(3));
                return cate;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public void updateCategorie(Categories c) {
        String sql = "UPDATE [dbo].[tblCategories]\n"
                + "   SET [categoryName] = ?\n"
                + "      ,[describe] = ?\n"
                + " WHERE categoryID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, c.getCategoryName());
            ptm.setString(2, c.getDescribe());
            ptm.setInt(3, c.getCategoryID());
            ptm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public int deleteCategorie(int categoryID) {
        String sql = "DELETE FROM [dbo].[tblCategories]\n"
                + "      WHERE categoryID = ?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, categoryID);
            ResultSet rs = getData("select * from tblOrderDetails od \n"
                    + "join tblProducts p on od.productID = p.productID\n"
                    + "join tblCategories ca on p.categoryID = ca.categoryID\n"
                    + "where ca.categoryID ="+categoryID);
            if (rs.next()) {
                n = 1;
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
                + "  FROM [dbo].[tblCategories]";
        CategorieDAO cDAO = new CategorieDAO();
        Vector<Categories> list = cDAO.getAllCategorie(sql);
        for (Categories c : list) {
            System.out.println(c);
        }

        //int n = cDAO.deleteCategorie("C006");
        Categories x = cDAO.searchCategorie(1);
        System.out.println(x);
        /*Categories cate = new Categories("C006", "chuột máy tính",
                "cung cấp chuột máy tính chất lượng cao", 1);
        cDAO.insertCategorie(cate);*/
    }
}

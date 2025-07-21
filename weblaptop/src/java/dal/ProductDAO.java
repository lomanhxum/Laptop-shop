/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Vector;
import models.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Orders;

public class ProductDAO extends DBContext {

    public Vector<Products> getAllBuyProduct(int productID) {
        Vector<Products> listProduct = new Vector<>();
        String sql = "SELECT FROM [dbo].[tblProducts]\n"
                + "      WHERE productID=?";

        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, productID);
            ResultSet rs = getData("SELECT *\n"
                    + "  FROM [dbo].[tblOrderDetails]\n"
                    + "  WHERE productID=" + productID);

            while (rs.next()) {
                Products p = new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16));
                listProduct.add(p);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return listProduct;
    }

    

    public Vector<Products> getAllProduct(String sql) {
        Vector<Products> listProduct = new Vector<>();
        PreparedStatement ptm;
        try {
            ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Products p = new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16));
                listProduct.add(p);
            }
        } catch (SQLException ex) {

        }
        return listProduct;
    }

    public Vector<Products> getProductByCID(int categoryID) {
        Vector<Products> listProduct = new Vector<>();
        String sql = "select * from tblProducts\n"
                + "where categoryID =?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, categoryID);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Products p = new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16));
                listProduct.add(p);
            }
        } catch (SQLException ex) {

        }
        return listProduct;
    }

    public void insertProduct(Products p) {
        String sql = "INSERT INTO [dbo].[tblProducts]\n"
                + "           ([productName]\n"
                + "           ,[image]\n"
                + "           ,[price]\n"
                + "           ,[processor]\n"
                + "           ,[graphique]\n"
                + "           ,[computerScreen]\n"
                + "           ,[RAM]\n"
                + "           ,[memory]\n"
                + "           ,[operatingSystem]\n"
                + "           ,[color]\n"
                + "           ,[quantity]\n"
                + "           ,[categoryID]\n"
                + "           ,[importDate]\n"
                + "           ,[usingDate]\n"
                + "           ,[status])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, p.getProductName());
            ptm.setString(2, p.getImage());
            ptm.setDouble(3, p.getPrice());
            ptm.setString(4, p.getProcessor());
            ptm.setString(5, p.getGraphique());
            ptm.setString(6, p.getComputerScreen());
            ptm.setString(7, p.getRAM());
            ptm.setString(8, p.getMemory());
            ptm.setString(9, p.getOperatingSystem());
            ptm.setString(10, p.getColor());
            ptm.setInt(11, p.getQuantity());
            ptm.setInt(12, p.getCategoryID());
            ptm.setDate(13, p.getImportDate());
            ptm.setDate(14, p.getUsingDate());
            ptm.setInt(15, p.getStatus());
            ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    public Products searchProduct(int productID) {
        String sql = "SELECT * FROM [dbo].[tblProducts]\n"
                + "  WHERE productID =?";

        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, productID);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                Products pro = new Products(productID,
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16));
                return pro;
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public void updateProduct(Products p) {
        String sql = "UPDATE [dbo].[tblProducts]\n"
                + "   SET [productName] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[processor] = ?\n"
                + "      ,[graphique] = ?\n"
                + "      ,[computerScreen] = ?\n"
                + "      ,[RAM] = ?\n"
                + "      ,[memory] = ?\n"
                + "      ,[operatingSystem] = ?\n"
                + "      ,[color] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[categoryID] = ?\n"
                + "      ,[importDate] = ?\n"
                + "      ,[usingDate] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE productID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setString(1, p.getProductName());
            ptm.setString(2, p.getImage());
            ptm.setDouble(3, p.getPrice());
            ptm.setString(4, p.getProcessor());
            ptm.setString(5, p.getGraphique());
            ptm.setString(6, p.getComputerScreen());
            ptm.setString(7, p.getRAM());
            ptm.setString(8, p.getMemory());
            ptm.setString(9, p.getOperatingSystem());
            ptm.setString(10, p.getColor());
            ptm.setInt(11, p.getQuantity());
            ptm.setInt(12, p.getCategoryID());
            ptm.setDate(13, p.getImportDate());
            ptm.setDate(14, p.getUsingDate());
            ptm.setInt(15, p.getStatus());
            ptm.setInt(16, p.getProductID());
            ptm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void changeStatus(int productID, int status) {
        String sql = "UPDATE [dbo].[tblProducts]\n"
                + "   SET [status] = ?\n"
                + " WHERE productID = ?";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, status);
            ptm.setInt(2, productID);
            ptm.executeUpdate();
        } catch (Exception e) {
        }

    }

    public int deleteProduct(int productID) {
        String sql = "DELETE FROM [dbo].[tblProducts]\n"
                + "      WHERE productID=?";
        int n = 0;
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ptm.setInt(1, productID);
            ResultSet rs = getData("SELECT *\n"
                    + "  FROM [dbo].[tblOrderDetails]\n"
                    + "  WHERE productID=" + productID);
            if (rs.next()) {
                changeStatus(productID, 0);
                return n;
            }
            n = ptm.executeUpdate();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return n;
    }

    public Vector<Products> getLast() {
        Vector<Products> listProduct = new Vector<>();
        String sql = "select top 3 * from tblProducts\n"
                + "order by productID desc";
        try {
            PreparedStatement ptm = connection.prepareStatement(sql);
            ResultSet rs = ptm.executeQuery();
            while (rs.next()) {
                Products Pro = new Products(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16));
                listProduct.add(Pro);
            }
        } catch (Exception e) {
        }
        return listProduct;
    }

    public static void main(String[] args) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[tblProducts]";
        ProductDAO pDAO = new ProductDAO();
        Vector<Products> list = pDAO.getLast();
        for (Products p : list) {
            System.out.println(p);
        }

        /*Products last = pDAO.searchProduct(1);
        System.out.println(last);*/
 /*Products pro = new Products("Iphone 18",
                "http//SE1923", 5000,
                50, "C001",
                new Date(2025 - 1900, 1, 15),
                new Date(2025 - 1900, 1, 16), 1);
        pDAO.insertProduct(pro);
        list = pDAO.getAllProduct(sql);
        for (Products p : list) {
            System.out.println(p);
        }*/
 /*Products psearch = pDAO.searchProduct(7);
        if (psearch != null) {
            System.out.println("found id" + psearch.getProductID());
            pDAO.updateProduct(new Products(7, "Samsung222", "http//se1923", 50, 150,
                    "C001", new Date(2025 - 1900, 1, 20),
                    new Date(2025 - 1900, 1, 30), 1));
        }*/
 /*int n = pDAO.deleteProduct(3);
        System.out.println(n);*/
    }
}

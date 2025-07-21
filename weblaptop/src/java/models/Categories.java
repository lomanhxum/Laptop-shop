/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author THIS PC
 */
public class Categories {
    private int categoryID;
    private String categoryName,describe;

    public Categories() {
    }

    public Categories(String categoryName, String describe) {
        this.categoryName = categoryName;
        this.describe = describe;
    }

    public Categories(int categoryID, String categoryName, String describe) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.describe = describe;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    
    
}

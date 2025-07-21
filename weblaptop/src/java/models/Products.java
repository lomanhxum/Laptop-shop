/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

public class Products {
    private int productID;
    private String productName, image;
    private double price;
    private String processor, graphique, computerScreen, RAM, memory, operatingSystem, color;
    private int quantity, categoryID;
    private Date importDate, usingDate;
    private int status;

    public Products() {
    }

    public Products(int productID, String productName, String image, double price, String processor, String graphique, String computerScreen, String RAM, String memory, String operatingSystem, String color, int quantity, int categoryID, Date importDate, Date usingDate, int status) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.processor = processor;
        this.graphique = graphique;
        this.computerScreen = computerScreen;
        this.RAM = RAM;
        this.memory = memory;
        this.operatingSystem = operatingSystem;
        this.color = color;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.importDate = importDate;
        this.usingDate = usingDate;
        this.status = status;
    }

    public Products(String productName, String image, double price, String processor, String graphique, String computerScreen, String RAM, String memory, String operatingSystem, String color, int quantity, int categoryID, Date importDate, Date usingDate, int status) {
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.processor = processor;
        this.graphique = graphique;
        this.computerScreen = computerScreen;
        this.RAM = RAM;
        this.memory = memory;
        this.operatingSystem = operatingSystem;
        this.color = color;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.importDate = importDate;
        this.usingDate = usingDate;
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getGraphique() {
        return graphique;
    }

    public void setGraphique(String graphique) {
        this.graphique = graphique;
    }

    public String getComputerScreen() {
        return computerScreen;
    }

    public void setComputerScreen(String computerScreen) {
        this.computerScreen = computerScreen;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Date getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(Date usingDate) {
        this.usingDate = usingDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

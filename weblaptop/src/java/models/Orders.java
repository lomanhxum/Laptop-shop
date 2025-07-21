/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author THIS PC
 */
public class Orders {
    private int orderID;
    private  Date orderDate;
    private  double total;
    private  String userID;
    private int status ;
    
    public Orders() {
    }  

    public Orders(double total, String userID) {
        this.total = total;
        this.userID = userID;
    }
    
    

    public Orders(Date orderDate, double total, String userID, int status) {
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
        this.status = status;
    }

    
    public Orders(int orderID, Date orderDate, double total, String userID, int status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", orderDate=" + orderDate + ", total=" + total + ", userID=" + userID + ", status=" + status + '}';
    }
}

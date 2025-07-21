<%-- 
    Document   : ProductJSP
    Created on : Feb 14, 2025, 9:01:28 AM
    Author     : THIS PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${pageTile}</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${userID == null}">
                <p align="right"><a href="ServletUserJSP?service=loginUser">Login</a></p>
            </c:when>
            <c:otherwise>
                <p align="right" style="color: green">Wellcome${userID}<a href="ServletUserJSP?service=logoutUser">Logout</a></p>    
            </c:otherwise>
        </c:choose>
        <form action="ServletProductJSP">
            <p>Search By productName 
                <input type="text" name="productName" value="${productName}">
                <input type="submit" name="submit" value="Search">
                <input type="reset" name="Reset" value="Reset">
                <input type="hidden" name="service" value="listProduct">
            </p>
        </form>
        <table border="1">
            <caption style="color: red">${tableTile}</caption>
            <thead>
                <tr>
                    <th>productID</th>
                    <th>productName</th>                   
                    <th>price</th>
                    <th>quantity</th>
                    <th>subTotal</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${data}" var="cart">
                    <tr>  
                        <td>${cart.productID}</td>
                        <td>${cart.productName}</td>
                        <td>${cart.price}</td>
                        <td>
                            <a href="ServletCart?service=decreaseQuantity&pid=${cart.productID}">-</a>
                            ${cart.quantity}
                            <a href="ServletCart?service=increaseQuantity&pid=${cart.productID}">+</a>
                        </td>
                        <td>${cart.price * cart.quantity}</td>
                        <td><a href="ServletCart?service=removeitem&pid=${cart.productID}">remove</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>Grand Total: ${total}</p>
        <p><a href="ServletCart?service=removeall" style="color: red">Remove All</a></p>
        <p><a href="ServletProductJSP?service=checkRoleId"></a></p>
    </body>
</html>

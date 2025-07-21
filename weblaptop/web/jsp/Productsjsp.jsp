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
        <table border="1">
            <caption style="color: red">${tableTile}</caption>
            <thead>
                <tr>
                    <th>productID</th>
                    <th>productName</th>
                    <th>image</th>
                    <th>price</th>
                    <th>processor</th>
                    <th>graphique</th>
                    <th>computerScreen</th>
                    <th>RAM</th>
                    <th>memory</th>
                    <th>operatingSystem</th>
                    <th>color</th>
                    <th>quantity</th>
                    <th>categoryID</th>
                    <th>supplierID</th>
                    <th>importDate</th>
                    <th>usingDate</th>
                    <th>status</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${data}" var="product">
                <tr>
                    <td>${product.productID}</td>
                    <td>${product.productName}</td>
                    <td>${product.image}</td>
                    <td>${product.price}</td>
                    <td>${product.processor}</td>
                    <td>${product.graphique}</td>
                    <td>${product.computerScreen}</td>
                    <td>${product.RAM}</td>
                    <td>${product.memory}</td>
                    <td>${product.operatingSystem}</td>
                    <td>${product.color}</td>
                    <td>${product.quantity}</td>
                    <td>${product.categoryID}</td>
                    <td>${product.supplierID}</td>
                    <td>${product.importDate}</td>
                    <td>${product.usingDate}</td>
                    <td>${product.status}</td>                   
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Details - Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <script>
            function openModal(modalId) {
                document.getElementById(modalId).classList.add("active");
            }

            function closeModal(modalId) {
                document.getElementById(modalId).classList.remove("active");
            }
            function updatePrice() {
                var productID = document.getElementById("productID").value;
                if (productID) {
                    fetch('ServletOrderDetailJSP?service=getProductPrice&productID=' + productID)
                            .then(response => response.json())
                            .then(data => {
                                document.getElementById("price").value = data.price || 0;
                            })
                            .catch(error => console.error('Error:', error));
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="sidebar.jsp"></jsp:include>

                <div class="main-content">
                    <div class="header">
                        <h1>Order Details</h1>
                        <form action="ServletOrderDetailJSP">
                            <input type="text" id="search" name="price" value="${price}" placeholder="Search...">
                        <button type="submit" name="submit" class="btn btn-search">Search</button>
                        <input type="hidden" name="service" value="listOrderDetail">
                    </form>
                </div>

                <div class="section">
                    <h2>Order Details</h2>
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>detailID</th>
                                    <th>price</th>
                                    <th>quantity</th>
                                    <th>orderID</th>
                                    <th>productID</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${data}" var="Od">
                                    <tr>
                                        <td>${Od.detailID}</td>
                                        <td>${Od.price}</td>
                                        <th>${Od.quantity}</th>
                                        <th>${Od.orderID}</th>
                                        <th>${Od.productID}</th>
                                        <td>
                                            <a href="ServletOrderDetailJSP?service=EditOrderDetail&Odid=${Od.detailID}#edit-orderDetail-modal" class="btn btn-edit">Edit</a>
                                            <a href="ServletOrderDetailJSP?service=deleteOrderDetail&id=${Od.detailID}" class="btn btn-delete">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal" id="edit-orderDetail-modal">
            <div class="modal-content">
                <h2>Add Order Detail</h2>
                <form action="ServletOrderDetailJSP">
                    <div class="form-group">
                        <label>detailID</label>
                        <input type="number" name="detailID" value="${i.detailID}" step="0.01" required readonly>
                    </div>
                    <div class="form-group">
                        <label>price</label>
                        <input type="number" name="price" id="price" value="${i.price}" step="0.01" required readonly>
                    </div>
                    <div class="form-group">
                        <label>quantity</label>
                        <input type="number" name="quantity" value="${i.quantity}" required>
                    </div>
                    <div class="form-group">
                        <label>orderID</label>
                        <select name="orderID">
                            <c:forEach items="${listo}" var="o">
                                <option value="${o.orderID}"${o.orderID.equals(i.orderID) ? "selected" : ""}>${o.orderID}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>productID</label>
                        <select name="productID" id="productID" onchange="updatePrice()">
                            <c:forEach items="${listp}" var="p">
                                <option value="${p.productID}"${p.productID.equals(i.productID) ? "selected" : ""}>${p.productName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="hidden" name="service" value="updateOrderDetail">
                    <input type="submit" name="submit" value="Save" class="btn btn-add">
                    <a href="#" class="btn btn-delete">Cancel</a>
                </form>
            </div>
        </div>
    </body>
</html>
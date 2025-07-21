<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Orders - Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <script>

            function openModal(modalId) {
                document.getElementById(modalId).classList.add("active");
            }

            function closeModal(modalId) {
                document.getElementById(modalId).classList.remove("active");
            }
            
            function updateTotal2() {
                var userID = document.getElementById("userID2").value;
                if (userID) {
                    fetch('ServletOrderJSP?service=getUserTotal&userID=' + userID)
                            .then(response => response.json())
                            .then(data => {
                                document.getElementById("total2").value = data.total || 0;
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
                        <h1>Orders</h1>
                        <form action="">
                            <input type="text" id="search" name="userID" value="${userID}" placeholder="Search...">
                        <button type="submit" name="submit" class="btn btn-search">Search</button>
                        <input type="hidden" name="service" value="listOrder">
                    </form>
                </div>

                <div class="section">
                    <h2>Orders</h2>
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>orderID</th>
                                    <th>orderDate</th>
                                    <th>total</th>
                                    <th>userID</th>
                                    <th>status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${data}" var="order">
                                    <tr>
                                        <td>${order.orderID}</td>
                                        <td>${order.orderDate}</td>
                                        <th>${order.total}</th>
                                        <th>${order.userID}</th>
                                        <th>${order.status}</th>
                                        <td>
                                            <a href="ServletOrderJSP?service=editOrder&id=${order.orderID}#edit-order-modal" class="btn btn-edit">Edit</a>
                                            <a href="ServletOrderJSP?service=deleteOrder&id=${order.orderID}" class="btn btn-delete">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Edit Order Modal -->
        <div class="modal" id="edit-order-modal">
            <div class="modal-content">
                <h2>Edit Order</h2>
                <form action="ServletOrderJSP" method="POST">
                    <div class="form-group">
                        <label>orderID</label>
                        <input type="text" name="orderID" value="${order.orderID}" readonly>
                    </div>
                    <div class="form-group">
                        <label>orderDate</label>
                        <input type="date" name="orderDate" value="${order.orderDate}" required>
                    </div>
                    <div class="form-group">
                        <label>total</label>
                        <input type="number" name="total" id="total2" step="0.01" value="${order.total}" required readonly>
                    </div>
                    <div class="form-group">
                        <label>userID</label>
                        <select name="userID" id="userID2" onchange="updateTotal2()" required>
                            <option value="">Select User</option>
                            <c:forEach items="${list}" var="u">
                                <option value="${u.userID}" ${u.userID == order.userID ? 'selected' : ''}>${u.fullName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>status</label>
                        <div class="radio-group">
                            <label><input type="radio" name="status" value="1" ${order.status == 1 ? 'checked' : ''}> Active</label>
                            <label><input type="radio" name="status" value="0" ${order.status == 0 ? 'checked' : ''}> DeActive</label>
                        </div>
                    </div>
                    <input type="hidden" name="service" value="updateOrder">
                    <input type="submit" name="submit" value="Save" class="btn btn-add">
                    <a href="#" class="btn btn-delete" onclick="closeModal('edit-order-modal')">Cancel</a>
                </form>
            </div>
        </div>
    </body>
</html>
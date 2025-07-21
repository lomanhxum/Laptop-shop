<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Users - Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
        <script>
        function openModal(modalId) {
            document.getElementById(modalId).classList.add("active");
        }

        function closeModal(modalId) {
            document.getElementById(modalId).classList.remove("active");
        }
    </script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="sidebar.jsp"></jsp:include>

                <div class="main-content">
                    <div class="header">
                        <h1>Users</h1>
                        <form action="ServletUsers">
                            <input type="text" id="search" name="fullName" value="${fullName}" placeholder="Search...">
                        <button type="submit"  name="submit" class="btn btn-search">Search</button>
                        <input type="hidden" name="service" value="listUser">
                    </form>
                </div>

                <div class="section">
                    <c:if test="${check != null}">
                        <p style='color: red'>${check}</p>
                    </c:if>
                    <h2>Users</h2>
                    <a href="#add-user-modal" class="btn btn-add">Add User</a>
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>userID</th>
                                    <th>fullName</th>
                                    <th>password</th>
                                    <th>roleID</th>
                                    <th>address</th>
                                    <th>phone</th>
                                    <th>email</th>
                                    <th>activate</th>
                                    <th>Actions</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${data}" var="user">
                                    <tr>
                                        <td>${user.userID}</td>
                                        <td>${user.fullName}</td>
                                        <td>${user.password}</td>
                                        <td>${user.roleID}</td>
                                        <td>${user.address}</td>
                                        <td>${user.phone}</td>
                                        <td>${user.email}</td>
                                        <td>${user.activate}</td>
                                        <td>
                                            <a href="ServletUsers?service=editUser&id=${user.userID}#edit-user-modal" class="btn btn-edit">Edit</a>
                                            <a href="ServletUsers?service=deleteUser&id=${user.userID}" class="btn btn-delete">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add User Modal -->
        <div class="modal" id="add-user-modal">
            <div class="modal-content">
                <h2>Add User</h2>
                <form action="ServletUsers">
                    <div class="form-group">
                        <label>userID</label>
                        <input type="text" name="userID" required>
                    </div>
                    <div class="form-group">
                        <label>fullName</label>
                        <input type="text" name="fullName" required>
                    </div>
                    <div class="form-group">
                        <label>password</label>
                        <input type="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label>roleID</label>
                        <select name="roleID" required>
                            <c:forEach items="${vector}" var="r">
                                <option value="${r.roleID}">${r.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>address</label>
                        <input type="text" name="address" required>
                    </div>
                    <div class="form-group">
                        <label>phone</label>
                        <input type="text" name="phone" required>
                    </div>
                    <div class="form-group">
                        <label>email</label>
                        <input type="text" name="email" required>
                    </div>
                    <div class="form-group">
                        <label>activate</label>
                        <div class="radio-group">
                            <label><input type="radio" name="activate" value="true" checked> Active</label>
                            <label><input type="radio" name="activate" value="false"> DeActive</label>
                        </div>
                    </div>
                    <input type="hidden" name="service" value="addUser">
                    <button type="submit" name="submit" class="btn btn-add">Save</button>
                    <a href="#" class="btn btn-delete" onclick="closeModal('add-user-modal')">Cancel</a>
                </form>
            </div>
        </div>

        <!-- Edit User Modal -->
        <div class="modal" id="edit-user-modal">
            <div class="modal-content">
                <h2>Edit User</h2>
                <form action="ServletUsers">
                    <div class="form-group">
                        <label>userID</label>
                        <input type="text" name="userID" value="${editUser.userID}" readonly>
                    </div>
                    <div class="form-group">
                        <label>fullName</label>
                        <input type="text" name="fullName" value="${editUser.fullName}" required>
                    </div>
                    <div class="form-group">
                        <label>password</label>
                        <input type="text" name="password" value="${editUser.password}" required>
                    </div>
                    <div class="form-group">
                        <label>roleID</label>
                        <select name="roleID">
                            <c:forEach items="${vector}" var="r">
                                <option value="${r.roleID}"${r.roleID.equals(editUser.roleID)?"selected":""}>${r.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>address</label>
                        <input type="text" name="address" value="${editUser.address}" required>
                    </div>
                    <div class="form-group">
                        <label>phone</label>
                        <input type="text" name="phone" value="${editUser.phone}" required>
                    </div>
                    <div class="form-group">
                        <label>email</label>
                        <input type="email" name="email" value="${editUser.email}" required>
                    </div>
                    <div class="form-group">
                        <label>activate</label>
                        <div class="radio-group">
                            <label><input type="radio" name="activate" value="true" ${editUser.activate == true? "checked" : ""}> Active</label>
                            <label><input type="radio" name="activate" value="false" ${editUser.activate == false? "checked" : ""}> DeActive</label>
                        </div>
                    </div>
                    <input type="hidden" name="service" value="updateUser">
                    <input type="submit" name="submit" value="Save" class="btn btn-add">
                    <a href="#" class="btn btn-delete" onclick="closeModal('edit-user-modal')">Cancel</a>
                </form>
            </div>
        </div>
    </body>
</html>
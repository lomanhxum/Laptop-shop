<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Roles - Admin Dashboard</title>
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
                        <h1>Roles</h1>
                        <form action="ServletRoleJSP">
                            <input name="roleName" value="${roleName}" type="text" id="search" placeholder="Search...">
                        <button name="submit" value="submit" class="btn btn-search">Search</button>
                        <input type="hidden" name="service" value="listRole">
                    </form>
                </div>

                <div class="section">
                    <c:if test="${check != null}">
                        <p style='color: red'>${check}</p>
                    </c:if>
                    <h2>Roles</h2>
                    <a href="#add-role-modal" class="btn btn-add">Add Role</a>
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>roleID</th>
                                    <th>roleName</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${data}" var="r">
                                    <tr>
                                        <td>${r.roleID}</td>
                                        <td>${r.roleName}</td>
                                        <td>
                                            <a href="ServletRoleJSP?service=EditRole&Rid=${r.roleID}#Edit-role-modal" class="btn btn-edit">Edit</a>
                                            <a href="ServletRoleJSP?service=deleteRole&id=${r.roleID}" class="btn btn-delete">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add Role Modal -->
        <div class="modal" id="add-role-modal">
            <div class="modal-content">
                <h2>Add Role</h2>
                <form action="ServletRoleJSP">
                    <div class="form-group">
                        <label>roleName</label>
                        <input type="text" name="roleName" required>
                    </div>
                    <input type="hidden" name="service" value="addRole">
                    <button name="submit" value="submit" class="btn btn-add">Save</button>
                    <a href="#" class="btn btn-delete" onclick="closeModal('add-role-modal')">Cancel</a>
                </form>
            </div>
        </div>
        
        <div class="modal" id="Edit-role-modal">
            <div class="modal-content">
                <h2>Add Role</h2>
                <form action="ServletRoleJSP">
                    <div class="form-group">
                        <label>roleID</label>
                        <input type="text" name="roleID" value="${i.roleID}" required readonly>
                    </div>
                    <div class="form-group">
                        <label>roleName</label>
                        <input type="text" name="roleName" value="${i.roleName}" required>
                    </div>
                    <input type="hidden" name="service" value="updateRole">
                    <button name="submit" value="submit" class="btn btn-add">Save</button>
                    <a href="#" class="btn btn-delete" onclick="closeModal('Edit-role-modal')">Cancel</a>
                </form>
            </div>
        </div>
    </body>
</html>
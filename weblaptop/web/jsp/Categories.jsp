<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Categories - Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
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
                        <h1>Categories</h1>
                        <form action="ServletCategorieJSP">
                            <input type="text" id="search" name="categoryName" value="${categoryName}" placeholder="Search...">
                        <button name="submit" value="submit" class="btn btn-search">Search</button>
                        <input type="hidden" name="service" value="listCategorie">
                    </form>
                </div>

                <div class="section">
                    <c:if test="${check != null}">
                        <p style='color: red'>${check}</p>
                    </c:if>
                    <h2>Categories</h2>
                    <a href="#add-category-modal" class="btn btn-add">Add Category</a>
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>categoryID</th>
                                    <th>categoryName</th>
                                    <th>describe</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${data}" var="categorie">
                                    <tr>
                                        <td>${categorie.categoryID}</td>
                                        <td>${categorie.categoryName}</td>
                                        <td>${categorie.describe}</td>
                                        <td>
                                            <a href="ServletCategorieJSP?service=EditCategorie&ucid=${categorie.categoryID}#Edit-category-modal" class="btn btn-edit">Edit</a>
                                            <a href="ServletCategorieJSP?service=deleteCategorie&cid=${categorie.categoryID}" class="btn btn-delete">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add Category Modal -->
        <div class="modal" id="add-category-modal">
            <div class="modal-content">
                <h2>Add Category</h2>
                <form action="ServletCategorieJSP">
                    <div class="form-group">
                        <label>categoryName</label>
                        <input type="text" name="categoryName" required>
                    </div>
                    <div class="form-group">
                        <label>describe</label>
                        <input type="text" name="describe">
                    </div>
                    <input type="hidden" name="service" value="addCategorie">
                    <button name="submit" type="submit" class="btn btn-add">Save</button>
                    <a href="#" class="btn btn-delete" onclick="closeModal('add-category-modal')">Cancel</a>
                </form>
            </div>
        </div>

        <div class="modal" id="Edit-category-modal">
            <div class="modal-content">
                <h2>Add Category</h2>
                <form action="ServletCategorieJSP">
                    <div class="form-group">
                        <label>categoryID</label>
                        <input type="text" name="categoryID" value="${i.categoryID}" required readonly>
                    </div>
                    <div class="form-group">
                        <label>categoryName</label>
                        <input type="text" name="categoryName" value="${i.categoryName}" required>
                    </div>
                    <div class="form-group">
                        <label>describe</label>
                        <input type="text" name="describe" value="${i.describe}">
                    </div>
                    <input type="hidden" name="service" value="updateCategorie">
                    <button type="submit" name="submit" class="btn btn-add">Save</button>
                    <a href="#" class="btn btn-delete" onclick="closeModal('Edit-category-modal')">Cancel</a>
                </form>
            </div>
        </div>
    </body>
</html>
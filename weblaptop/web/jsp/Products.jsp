<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products - Admin Dashboard</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background: #f5f5f5;
            color: #333;
            line-height: 1.6;
        }

        .container {
            display: flex;
            min-height: 100vh;
        }

        .sidebar {
            width: 250px;
            background: #2c3e50;
            color: white;
            padding: 20px;
            position: fixed;
            height: 100%;
        }

        .sidebar h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }

        .sidebar ul {
            list-style: none;
        }

        .sidebar ul li {
            padding: 10px;
            margin: 5px 0;
        }

        .sidebar ul li a {
            color: white;
            text-decoration: none;
            transition: 0.3s;
        }

        .sidebar ul li a:hover {
            background: #34495e;
            border-radius: 5px;
            display: block;
            padding: 10px;
        }

        .main-content {
            margin-left: 250px;
            padding: 20px;
            width: calc(100% - 250px);
        }

        .header {
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            margin-bottom: 20px;
            width: 100%;
        }

        .section {
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            width: 100%;
        }

        .section h2 {
            margin-bottom: 15px;
        }

        .table-container {
            margin-top: 20px;
            overflow-x: scroll;
            max-width: 100%;
            -webkit-overflow-scrolling: touch;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 3000px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: 150px;
        }

        th {
            background: #f8f9fa;
        }

        .btn {
            padding: 8px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 5px;
            text-decoration: none;
            color: white;
            display: inline-block;
        }

        .btn-add { background: #2ecc71; }
        .btn-edit { background: #3498db; }
        .btn-delete { background: #e74c3c; }
        .btn-search { background: #95a5a6; }

        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.5);
        }

        .modal.active {
            display: block;
        }

        .modal-content {
            background: white;
            width: 500px;
            margin: 100px auto;
            padding: 20px;
            border-radius: 5px;
            max-height: 80vh;
            overflow-y: auto;
            -webkit-overflow-scrolling: touch;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .form-group .radio-group {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .form-group .radio-group input[type="radio"] {
            width: auto;
            margin: 0 5px 0 0;
        }
        
        .img{
            width: 150px;
        }
    </style>
    <script>
        window.onload = function() {
            <c:if test="${not empty editProduct}">
                document.getElementById("edit-product-modal").classList.add("active");
            </c:if>
        };
        
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
                <h1>Products</h1>
                <form action="ServletProduct">
                    <input type="text" id="search" name="productName" value="${productName}" placeholder="Search...">
                    <button type="submit" name="submit" class="btn btn-search">Search</button>
                    <input type="hidden" name="service" value="listProduct">
                </form>
            </div>

            <div class="section">
                <h2>Products</h2>
                <a href="#" onclick="openModal('add-product-modal')" class="btn btn-add">Add Product</a>
                <div class="table-container">
                    <table>
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
                                <th>importDate</th>
                                <th>usingDate</th>
                                <th>status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listP}" var="product">
                                <tr>
                                    <td>${product.productID}</td>
                                    <td>${product.productName}</td>
                                    <td><img class="img" src="images/${product.image}" alt="IMG-PRODUCT"></td>
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
                                    <td>${product.importDate}</td>
                                    <td>${product.usingDate}</td>
                                    <td>${product.status}</td>
                                    <td>
                                        <a href="ServletProduct?service=editProduct&pid=${product.productID}" class="btn btn-edit">Edit</a>
                                        <a href="ServletProduct?service=deleteProduct&pid=${product.productID}" class="btn btn-delete">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Add Product Modal -->
    <div class="modal" id="add-product-modal">
        <div class="modal-content">
            <h2>Add Product</h2>
            <form action="ServletProduct" method="POST">
                <div class="form-group">
                    <label>productName</label>
                    <input type="text" name="productName" required>
                </div>
                <div class="form-group">
                    <label>image</label>
                    <input type="text" name="image" required>
                </div>
                <div class="form-group">
                    <label>price</label>
                    <input type="number" name="price" step="0.01" required>
                </div>
                <div class="form-group">
                    <label>processor</label>
                    <input type="text" name="processor" required>
                </div>
                <div class="form-group">
                    <label>graphique</label>
                    <input type="text" name="graphique" required>
                </div>
                <div class="form-group">
                    <label>computerScreen</label>
                    <input type="text" name="computerScreen" required>
                </div>
                <div class="form-group">
                    <label>RAM</label>
                    <input type="text" name="RAM" required>
                </div>
                <div class="form-group">
                    <label>memory</label>
                    <input type="text" name="memory" required>
                </div>
                <div class="form-group">
                    <label>operatingSystem</label>
                    <input type="text" name="operatingSystem" required>
                </div>
                <div class="form-group">
                    <label>color</label>
                    <input type="text" name="color" required>
                </div>
                <div class="form-group">
                    <label>quantity</label>
                    <input type="number" name="quantity" required>
                </div>
                <div class="form-group">
                    <label>categoryID</label>
                    <select name="categoryID" required>
                        <c:forEach items="${listc}" var="ca">
                            <option value="${ca.categoryID}">${ca.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>importDate</label>
                    <input type="date" name="importDate" required>
                </div>
                <div class="form-group">
                    <label>usingDate</label>
                    <input type="date" name="usingDate" required>
                </div>
                <div class="form-group">
                    <label>status</label>
                    <div class="radio-group">
                        <label><input type="radio" name="status" value="1" checked> Active</label>
                        <label><input type="radio" name="status" value="0"> DeActive</label>
                    </div>
                </div>
                <input type="submit" name="submit" value="Save" class="btn btn-add">
                <input type="hidden" name="service" value="addProduct">
                <a href="#" class="btn btn-delete" onclick="closeModal('add-product-modal')">Cancel</a>
            </form>
        </div>
    </div>

    <!-- Edit Product Modal -->
    <div class="modal" id="edit-product-modal">
        <div class="modal-content">
            <h2>Edit Product</h2>
            <form action="ServletProduct" method="POST">
                <div class="form-group">
                    <label>productID</label>
                    <input type="text" name="productID" value="${editProduct.productID}" readonly>
                </div>
                <div class="form-group">
                    <label>productName</label>
                    <input type="text" name="productName" value="${editProduct.productName}" required>
                </div>
                <div class="form-group">
                    <label>image</label>
                    <input type="text" name="image" value="${editProduct.image}" required>
                </div>
                <div class="form-group">
                    <label>price</label>
                    <input type="number" name="price" step="0.01" value="${editProduct.price}" required>
                </div>
                <div class="form-group">
                    <label>processor</label>
                    <input type="text" name="processor" value="${editProduct.processor}" required>
                </div>
                <div class="form-group">
                    <label>graphique</label>
                    <input type="text" name="graphique" value="${editProduct.graphique}" required>
                </div>
                <div class="form-group">
                    <label>computerScreen</label>
                    <input type="text" name="computerScreen" value="${editProduct.computerScreen}" required>
                </div>
                <div class="form-group">
                    <label>RAM</label>
                    <input type="text" name="RAM" value="${editProduct.RAM}" required>
                </div>
                <div class="form-group">
                    <label>memory</label>
                    <input type="text" name="memory" value="${editProduct.memory}" required>
                </div>
                <div class="form-group">
                    <label>operatingSystem</label>
                    <input type="text" name="operatingSystem" value="${editProduct.operatingSystem}" required>
                </div>
                <div class="form-group">
                    <label>color</label>
                    <input type="text" name="color" value="${editProduct.color}" required>
                </div>
                <div class="form-group">
                    <label>quantity</label>
                    <input type="number" name="quantity" value="${editProduct.quantity}" required>
                </div>
                <div class="form-group">
                    <label>categoryID</label>
                    <select name="categoryID" required>
                        <c:forEach items="${listc}" var="ca">
                            <option value="${ca.categoryID}" ${ca.categoryID == editProduct.categoryID ? 'selected' : ''}>${ca.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>importDate</label>
                    <input type="date" name="importDate" value="${editProduct.importDate}" required>
                </div>
                <div class="form-group">
                    <label>usingDate</label>
                    <input type="date" name="usingDate" value="${editProduct.usingDate}" required>
                </div>
                <div class="form-group">
                    <label>status</label>
                    <div class="radio-group">
                        <label><input type="radio" name="status" value="1" ${editProduct.status == 1 ? 'checked' : ''}> Active</label>
                        <label><input type="radio" name="status" value="0" ${editProduct.status == 0 ? 'checked' : ''}> DeActive</label>
                    </div>
                </div>
                <input type="submit" name="submit" value="Save" class="btn btn-add">
                <input type="hidden" name="service" value="updateProduct">
                <a href="#" class="btn btn-delete" onclick="closeModal('edit-product-modal')">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>
<%-- 
    Document   : Menu
    Created on : Mar 13, 2025, 2:36:13 PM
    Author     : THIS PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="flex-w flex-sb-m p-b-52">
    <div class="flex-w flex-l-m filter-tope-group m-tb-10">
        <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" data-filter="*">
            <a href="url">All Products</a>
        </button>
        <c:forEach items="${listCC}" var="c">
            <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".category-${c.categoryID}">
                ${c.categoryName}
            </button>
        </c:forEach>

    </div>

    <div class="flex-w flex-c-m m-tb-10">

    </div>
</div>

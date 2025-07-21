<%-- 
    Document   : Search
    Created on : Mar 13, 2025, 4:12:41 PM
    Author     : THIS PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
    <div class="container-search-header">
        <button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
            <img src="images/icons/icon-close2.png" alt="CLOSE">
        </button>

        <form action="ServletProductJSP?service=listProduct" method="post" class="wrap-search-header flex-w p-l-15">
            <button type="submit" name="submit" class="flex-c-m trans-04">
                <i class="zmdi zmdi-search"></i>
            </button>
            <input name="productName" value="${productName}" class="plh3" type="text" placeholder="Search...">
        </form>
    </div>
</div>
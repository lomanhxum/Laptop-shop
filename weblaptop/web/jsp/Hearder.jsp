<%-- 
    Document   : Hearder
    Created on : Mar 13, 2025, 12:55:16 PM
    Author     : THIS PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-menu-desktop">
    <!-- Topbar -->
    <div class="top-bar">
        <div class="content-topbar flex-sb-m h-full container"> 

            <div class="left-top-bar">
                <c:if test="${userID != null}">
                    WELLCOME ${userID}
                </c:if>
            </div>

            <div class="right-top-bar flex-w h-full">
                <c:if test="${roleID == 1}">
                    <a href="ServletProduct" class="flex-c-m  p-lr-25">
                        Admin Account
                    </a>
                </c:if>

                <c:choose>
                    <c:when test="${userID == null}">
                        <a href="jsp/Login.jsp" class="flex-c-m p-lr-25">
                            Login
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="ServletUserJSP?service=logoutUser" class="flex-c-m  p-lr-25">
                            LogOut
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <div class="wrap-menu-desktop">
        <nav class="limiter-menu-desktop container">

            <!-- Logo desktop -->		
            <a href="home" class="logo">
                <img src="images/icons/logo-01.png" alt="IMG-LOGO">
            </a>

            <!-- Menu desktop -->
            <div class="menu-desktop">
                <ul class="main-menu">
                    <li class="active-menu">
                        <a href="home">Home</a>

                    </li>

                    <li>
                        <a href="shop">Shop</a>
                    </li>
                </ul>
            </div>	

            <!-- Icon header -->
            <div class="wrap-icon-header flex-w flex-r-m">
                <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
                    <i class="zmdi zmdi-search"></i>
                </div>

                <a href="ServletCart?service=showcart" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti">
                    <i class="zmdi zmdi-shopping-cart"></i>
                </a>
            </div>
        </nav>
    </div>	
</div>

<!-- Header Mobile -->
<div class="wrap-header-mobile">
    <!-- Logo moblie -->		
    <div class="logo-mobile">
        <a href="home"><img src="images/icons/logo-01.png" alt="IMG-LOGO"></a>
    </div>

    <!-- Icon header -->
    <div class="wrap-icon-header flex-w flex-r-m m-r-15">
        <div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 js-show-modal-search">
            <i class="zmdi zmdi-search"></i>
        </div>

        <a href="ServletCart?service=showcart" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti">
            <i class="zmdi zmdi-shopping-cart"></i>
        </a>
    </div>

    <!-- Button show menu -->
    <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
        <span class="hamburger-box">
            <span class="hamburger-inner"></span>
        </span>
    </div>
</div>


<!-- Menu Mobile -->
<div class="menu-mobile">
    <ul class="topbar-mobile">
        <li>
            <div class="left-top-bar">
                <c:if test="${userID != null}">
                    WELLCOME ${userID}
                </c:if>
            </div>
        </li>

        <li>
            <div class="right-top-bar flex-w h-full">
                <c:if test="${roleID == 1}">
                    <a href="#" class="flex-c-m  p-lr-25">
                        Admin Account
                    </a>
                </c:if>

                <c:choose>
                    <c:when test="${userID == null}">
                        <a href="jsp/Login.jsp" class="flex-c-m p-lr-25">
                            Login
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="ServletUserJSP?service=logoutUser" class="flex-c-m  p-lr-25">
                            LogOut
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </li>
    </ul>

    <ul class="main-menu-m">
        <li>
            <a href="home">Home</a>                       
            <span class="arrow-main-menu-m">
                <i class="fa fa-angle-right" aria-hidden="true"></i>
            </span>
        </li>

        <li>
            <a href="shop">Shop</a>
        </li>
    </ul>
</div>

<!-- Modal Search -->
<jsp:include page="Search.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Product Detail</title>       
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.png"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/MagnificPopup/magnific-popup.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
    </head>
    <body class="animsition">

        <!-- Header -->
        <header class="header-v4">
            <!-- Header desktop -->
            <jsp:include page="jsp/Hearder.jsp"></jsp:include>
            </header>

            <!-- Product Detail -->
            <section class="sec-product-detail bg0 p-t-65 p-b-60">
                <div class="container">

                    <div class="row">

                        <div class="col-md-6 col-lg-7 p-b-30">
                            <div class="p-l-25 p-r-30 p-lr-0-lg">
                                <div class="wrap-slick1 flex-sb flex-w">
                                    <div class="wrap-slick-dots"></div>
                                    <div class="wrap-slick-arrows flex-sb-m flex-w"></div>

                                    <div class="slick gallery-lb">

                                        <div class="wrap-pic-w pos-relative">
                                            <img src="images/${DescribeP.image}" alt="IMG-PRODUCT">
                                        <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/${DescribeP.image}">
                                            <i class="fa fa-expand"></i>
                                        </a>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-5 p-b-30">
                        <div class="p-r-50 p-t-5 p-lr-0-lg">
                            <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                                ${DescribeP.productName}
                            </h4>

                            <span class="mtext-106 cl2">
                                $${DescribeP.price}
                            </span>

                            <dl class="item-property">
                                <dt>Processor</dt>
                                <dd><p>${DescribeP.processor}</p></dd>                                          
                                <dt>Graphique</dt>
                                <dd><p>${DescribeP.graphique}</p></dd>
                                <dt>ComputerScreen</dt>
                                <dd><p>${DescribeP.computerScreen}</p></dd>
                                <dt>RAM</dt>
                                <dd><p>${DescribeP.RAM}</p></dd>
                                <dt>Memory</dt>
                                <dd><p>${DescribeP.memory}</p></dd>
                                <dt>OperatingSystem</dt>
                                <dd><p>${DescribeP.operatingSystem}</p></dd>
                                <dt>Color</dt>
                                <dd><p>${DescribeP.color}</p></dd>
                            </dl>

                            <!--  -->
                            <div class="p-t-33">
                                <div class="flex-w flex-r-m p-b-10">
                                    <div class="size-204 flex-w flex-m respon6-next">
                                        <c:choose>
                                            <c:when test="${DescribeP.quantity == 0}">
                                                <a href="#" style="color: red">
                                                    out of stock!
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail" data-product-id="${DescribeP.productID}">
                                                    Add to cart
                                                </button>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>	
                            </div>



                            </section>



                            <!-- Footer -->
                            <jsp:include page="jsp/Footer.jsp"></jsp:include>



                            <!--===============================================================================================-->	
                            <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
                            <!--===============================================================================================-->
                            <script src="vendor/animsition/js/animsition.min.js"></script>
                            <!--===============================================================================================-->
                            <script src="vendor/bootstrap/js/popper.js"></script>
                            <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
                            <!--===============================================================================================-->
                            <script src="vendor/select2/select2.min.js"></script>
                            <script>
                                $(".js-select2").each(function () {
                                    $(this).select2({
                                        minimumResultsForSearch: 20,
                                        dropdownParent: $(this).next('.dropDownSelect2')
                                    });
                                })
                            </script>
                            <!--===============================================================================================-->
                            <script src="vendor/daterangepicker/moment.min.js"></script>
                            <script src="vendor/daterangepicker/daterangepicker.js"></script>
                            <!--===============================================================================================-->
                            <script src="vendor/slick/slick.min.js"></script>
                            <script src="js/slick-custom.js"></script>
                            <!--===============================================================================================-->
                            <script src="vendor/parallax100/parallax100.js"></script>
                            <script>
                                $('.parallax100').parallax100();
                            </script>
                            <!--===============================================================================================-->
                            <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
                            <script>
                                $('.gallery-lb').each(function () { // the containers for all your galleries
                                    $(this).magnificPopup({
                                        delegate: 'a', // the selector for gallery item
                                        type: 'image',
                                        gallery: {
                                            enabled: true
                                        },
                                        mainClass: 'mfp-fade'
                                    });
                                });
                            </script>
                            <!--===============================================================================================-->
                            <script src="vendor/isotope/isotope.pkgd.min.js"></script>
                            <!--===============================================================================================-->
                            <script src="vendor/sweetalert/sweetalert.min.js"></script>
                            <script>
                                $('.js-addwish-b2, .js-addwish-detail').on('click', function (e) {
                                    e.preventDefault();
                                });

                                $('.js-addwish-b2').each(function () {
                                    var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
                                    $(this).on('click', function () {
                                        swal(nameProduct, "is added to wishlist !", "success");

                                        $(this).addClass('js-addedwish-b2');
                                        $(this).off('click');
                                    });
                                });

                                $('.js-addwish-detail').each(function () {
                                    var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

                                    $(this).on('click', function () {
                                        swal(nameProduct, "is added to wishlist !", "success");

                                        $(this).addClass('js-addedwish-detail');
                                        $(this).off('click');
                                    });
                                });

                                /*---------------------------------------------*/

                                $('.js-addcart-detail').each(function () {
                                    var $button = $(this);
                                    var nameProduct = $button.parent().parent().parent().parent().find('.js-name-detail').html();
                                    var productID = $button.data('product-id');
                                    console.log("Product ID:", productID);

                                    $button.on('click', function () {
                                        $.ajax({
                                            url: 'ServletCart?service=addcart',
                                            type: 'POST',
                                            data: {
                                                productID: productID
                                            },
                                            success: function (response) {
                                                swal(nameProduct, "is added to cart!", "success");
                                            },
                                            error: function (xhr, status, error) {
                                                console.log("Error:", status, error);
                                                swal("Error", "Failed to add to cart!", "error");
                                            }
                                        });
                                    });
                                });

                            </script>
                            <!--===============================================================================================-->
                            <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
                            <script>
                                $('.js-pscroll').each(function () {
                                    $(this).css('position', 'relative');
                                    $(this).css('overflow', 'hidden');
                                    var ps = new PerfectScrollbar(this, {
                                        wheelSpeed: 1,
                                        scrollingThreshold: 1000,
                                        wheelPropagation: false,
                                    });

                                    $(window).on('resize', function () {
                                        ps.update();
                                    })
                                });
                            </script>
                            <!--===============================================================================================-->
                            <script src="js/main.js"></script>

                            </body>
                            </html>
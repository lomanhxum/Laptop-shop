<!DOCTYPE html>
<html>

    <html>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Bootstrap Registration Page with Floating Labels</title>

            <style>
                body {
                    background: #007bff;
                    background: linear-gradient(to right, #0062E6, #33AEFF);
                }

                .card-img-left {
                    width: 45%;
                    /* Link to your background image using in the property below! */
                    background: scroll center url('/se1923/images/r_logo.jpg');
                    background-size: cover;
                }

                .btn-login {
                    font-size: 0.9rem;
                    letter-spacing: 0.05rem;
                    padding: 0.75rem 1rem;
                }

                .btn-google {
                    color: white !important;
                    background-color: #ea4335;
                }

                .btn-facebook {
                    color: white !important;
                    background-color: #3b5998;
                }

            </style>

            <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
            <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        </head>
        <body>
            <!-- This snippet uses Font Awesome 5 Free as a dependency. You can download it at fontawesome.io! -->

        <body>
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 col-xl-9 mx-auto">
                        <div class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
                            <div class="card-img-left d-none d-md-flex">
                                <!-- Background image for card set in CSS! -->
                            </div>
                            <div class="card-body p-4 p-sm-5">
                                <h5 class="card-title text-center mb-5 fw-light fs-5">Register</h5>
                                <c:if test="${mess != null}">
                                    <p style='color: red'>${mess}</p>
                                </c:if>
                                <c:if test="${CheckUser != null}">
                                    <p style='color: red'>${CheckUser}</p>
                                </c:if>
                                <c:if test="${successful != null}">
                                    <p style='color: blue'>${successful}</p>
                                </c:if>
                                
                                <form action="/se1923/ServletUserJSP?service=RegisterUsers" method="POST">
                                    <div class="form-floating mb-3">
                                        <input name="userID" value="${userID}" type="text" class="form-control" id="floatingInputUserID" placeholder="myusername" required autofocus>
                                        <label for="floatingInputUsername">UserID</label>
                                    </div>

                                    <div class="form-floating mb-3">
                                        <input name="fullName" value="${fullName}" type="text" class="form-control" id="floatingInputFullName" placeholder="myname">
                                        <label for="floatingInputFullName">FullName</label>
                                    </div>

                                    <div class="form-floating mb-3">
                                        <input name="phone" value="${phone}" type="text" class="form-control" id="floatingInputPhone" placeholder="03xxxxxxxx">
                                        <label for="floatingInputPhone">Phone</label>
                                    </div>

                                    <div class="form-floating mb-3">
                                        <input name="email" value="${email}" type="email" class="form-control" id="floatingInputEmail" placeholder="name@example.com">
                                        <label for="floatingInputEmail">Email</label>
                                    </div>

                                    <div class="form-floating mb-3">
                                        <input name="address" value="${address}" type="text" class="form-control" id="floatingInputAddress" placeholder="123 Main St, City">
                                        <label for="floatingInputAddress">Address</label>
                                    </div>


                                    <hr>

                                    <div class="form-floating mb-3">
                                        <input name="password" value="${password}" type="text" class="form-control" id="floatingPassword" placeholder="Password">
                                        <label for="floatingPassword">Password</label>
                                    </div>

                                    <div class="form-floating mb-3">
                                        <input name="ConfirmPassword" value="${confirmPassword}" type="text" class="form-control" id="floatingPasswordConfirm" placeholder="Confirm Password">
                                        <label for="floatingPasswordConfirm">Confirm Password</label>
                                    </div>

                                    <div class="d-grid mb-2">
                                        <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Register</button>
                                    </div>

                                    <a class="d-block text-center mt-2 small" href="/se1923/jsp/Login.jsp">Have an account? Sign In</a>

                                    <hr class="my-4">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>


        <script>

        </script>
    </body>
</html>

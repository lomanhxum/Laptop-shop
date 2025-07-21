<!DOCTYPE html>
<html>

    <html>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Bootstrap Sign In Form Layout</title>

            <style>
                .login {
                    min-height: 100vh;
                }

                .bg-image {
                    background-image: url('/se1923/images/r_logo.jpg');
                    background-size: cover;
                    background-position: center;
                }

                .login-heading {
                    font-weight: 300;
                }

                .btn-login {
                    font-size: 0.9rem;
                    letter-spacing: 0.05rem;
                    padding: 0.75rem 1rem;
                }

            </style>

            <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        </head>
        <body>
            <div class="container-fluid ps-md-0">
                <div class="row g-0">
                    <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
                    <div class="col-md-8 col-lg-6">
                        <div class="login d-flex align-items-center py-5">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-9 col-lg-8 mx-auto">
                                        <h3 class="login-heading mb-4"></h3>
                                        <c:if test="${message != null}">
                                            <p style='color: red'>${message}</p>
                                        </c:if>

                                        <!-- Sign In Form -->
                                        <form action="/se1923/ServletUserJSP">
                                            <div class="form-floating mb-3">
                                                <input name="userID" type="text" class="form-control" id="floatingInput" placeholder="userID">
                                                <label for="floatingInput">userID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
                                                <label for="floatingPassword">Password</label>
                                            </div>

                                            <div class="form-check mb-3">
                                                <input class="form-check-input" type="checkbox" value="" id="rememberPasswordCheck">
                                                <label class="form-check-label" for="rememberPasswordCheck">
                                                    Remember password
                                                </label>
                                            </div>

                                            <div class="d-grid">
                                                <button class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2" name="submit" type="submit">Login</button>
                                                <input type="hidden" name="service" value="loginUser">
                                                <div class="text-center">
                                                    <a class="small" href="Register.jsp">Register</a>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
            </script>
        </body>
    </html>

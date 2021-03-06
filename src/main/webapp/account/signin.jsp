<%--
  Created by IntelliJ IDEA.
  Autheor: Jeanne d'Arc
  Date: 2/7/20
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="SignIn" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container wrapper">
    <div class="form row">
        <h1 class="text-center">Login </h1>
        <c:if test="${not empty registerSuccess}">
            <h2 id="flash" class="alert alert-success">${registerSuccess}</h2>
            <c:remove var="registerSuccess"/>
        </c:if>
        <form method="post" action="j_security_check" class="card col-md-10 offset-md-1 needs-validation" novalidate>
            <div class="form-group row">
                <label for="username" class="col-md-3 col-form-label">Username</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="j_username" id="username" placeholder="Username" required>
                    <div class="invalid-feedback">Please provide a valid username!</div>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputPassword" class="col-md-3 col-form-label">Password</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" name="j_password"
                           id="inputPassword" placeholder="Password" required>
                    <div class="invalid-feedback">Please provide a valid password!</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-9">
                    <button type="submit"
                            name="signin" class="btn btn-lg btn-success">Sign In</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<%@include file="../afterFooter.jsp"%>
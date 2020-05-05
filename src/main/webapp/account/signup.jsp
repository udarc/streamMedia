<%--
  Created by IntelliJ IDEA.
  Autheor: Jeanne d'Arc
  Date: 2/7/20
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Register" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container wrapper">
    <div class="form row">
        <h1 class="text-center">Register</h1>
        <c:if test="${not empty errorMessage}">
            <h2 id="flash" class="alert alert-danger">${errorMessage}</h2>
            <c:remove var="errorMessage"/>
        </c:if>
     <form action="register" method="post" class="card col-sm-10 offset-sm-1 was-validated" novalidate>
        <div class="form-group row">
            <label for="inputEmail" class="col-sm-3 col-form-label">Email</label>
            <div class="col-sm-9">
                <input type="email" class="form-control"
                       name="email" id="inputEmail" placeholder="Email" required>
            </div>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Email is required.</div>
        </div>
        <div class="form-group row">
            <label for="username" class="col-sm-3 col-form-label">Username</label>
            <div class="col-sm-9">
                <input type="text" class="form-control"
                       name="username" id="username" placeholder="Username" required>
            </div>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Useraname is required.</div>
        </div>
         <div class="form-group row">
             <label for="firstName" class="col-sm-3 col-form-label">First Name</label>
             <div class="col-sm-9">
                 <input type="text" class="form-control"
                        name="firstname" id="firstName" placeholder="First Name">
             </div>
         </div>
         <div class="form-group row">
             <label for="lastName" class="col-sm-3 col-form-label">Last Name</label>
             <div class="col-sm-9">
                 <input type="text" class="form-control"
                        name="lastname" id="lastName" placeholder="Last Name">
             </div>
         </div>
        <div class="form-group row">
            <label for="inputPassword" class="col-sm-3 col-form-label">Password</label>
            <div class="col-sm-9">
                <input type="password" class="form-control" name="password"p
                       id="inputPassword" placeholder="Password" required>
            </div>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Password is required.</div>
        </div>
        <div class="form-group row">
            <label for="confirmPassword" class="col-sm-3 col-form-label">Confirm Password</label>
            <div class="col-sm-9">
                <input type="password" class="form-control"
                       name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" required>
            </div>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please Confirm your password.</div>
        </div>
        <div class="form-group row">
            <div class="col-sm-9">
                <button type="submit"
                        name="signup" class="btn btn-lg btn-success">Sign Up</button>
            </div>
        </div>
    </form>
    </div>


</div>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
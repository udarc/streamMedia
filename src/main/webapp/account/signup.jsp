<%--
  Created by IntelliJ IDEA.
  Autheor: Jeanne d'Arc
  Date: 2/7/20
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<%@include file="../head.jsp"%>
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="form">
    <form class="card">
        <div class="form-group row">
            <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail" placeholder="Email">
            </div>
        </div>
        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="username" placeholder="Username">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" placeholder="Password">
            </div>
        </div>
        <div class="form-group row">
            <label for="confirmPassword" class="col-sm-2 col-form-label">Confirm Password</label>
            <div class="col-sm-10">
                <input type="confirmPassword" class="form-control" id="confirmPassword" placeholder="Confirm Password">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-success">Sign Up</button>
            </div>
        </div>
    </form>
    </div>
</div>
<%@include file="../footer.jsp"%>
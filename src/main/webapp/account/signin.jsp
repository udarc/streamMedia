<%--
  Created by IntelliJ IDEA.
  Autheor: Jeanne d'Arc
  Date: 2/7/20
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="../css/account.css">
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="form row">
        <form class="card col-sm-10 offset-sm-1">
            <div class="form-group row">
                <label for="username" class="col-sm-3 col-form-label">Username</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="username" id="username" placeholder="Username">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputPassword" class="col-sm-3 col-form-label">Password</label>
                <div class="col-sm-9">
                    <input type="password" class="form-control" name="password"p
                           id="inputPassword" placeholder="Password">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-9">
                    <button type="submit"
                            name="signin" class="btn btn-lg btn-success">Sign Up</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
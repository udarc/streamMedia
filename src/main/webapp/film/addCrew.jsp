<%--
  Created by IntelliJ IDEA.
  Autheor: Jeanne d'Arc
  Date: 2/17/20
  Time: 5:12 PM
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

<%--TODO Add to Servlet --%>
     <form action="addCrew" method="post" class="card col-sm-10 offset-sm-1 was-validated">
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
             <label for="inputEmail" class="col-sm-3 col-form-label">Email</label>
             <div class="col-sm-9">
                 <input type="email" class="form-control"
                        name="email" id="inputEmail" placeholder="Email" required>
             </div>
             <div class="valid-feedback">Valid.</div>
             <div class="invalid-feedback">Email is required.</div>
         </div>
         <div class="form-group row">
             <label for="profession" class="col-sm-3 col-form-label">Profession</label>
             <div class="col-sm-9">
                 <input type="text" class="form-control"
                        name="profession" id="profession" placeholder="Profession" required>
             </div>
             <div class="valid-feedback">Valid.</div>
             <div class="invalid-feedback">Profession is required.</div>
         </div>

    <div class="form-group row">
        <label for="bio" class="col-sm-3 col-form-label" >Biography</label>
        <div class="col-sm-9">
            <textarea class="form-control"name="biography" id="bio" rows="6"></textarea>
        </div>
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
<script>
    // Disable form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Get the forms we want to add validation styles to
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
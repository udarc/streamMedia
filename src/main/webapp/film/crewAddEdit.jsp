<%--
  Created by IntelliJ IDEA.
  Autheor: Jeanne d'Arc
  Date: 2/17/20
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:choose>
    <c:when test="${crew ne null}">
        <c:set var="title" value="Edit Crew" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Add Crew" />
    </c:otherwise>
</c:choose>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="form row">

        <c:choose>
            <c:when test="${crew ne null}">
    <h1 class="text-center">Edit Crew</h1>
    <form action="crew-edit" method="post" class="card col-md-10 offset-md-1 needs-validation" novalidate>
        <input type="hidden" name="uid" value="${crew.crewId}">
            </c:when>
        <c:otherwise>
        <h1 class="text-center">Add Crew</h1>
                <form action="crew-new" method="post" class="card col-md-10 offset-md-1 needs-validation" novalidate>
                </c:otherwise>
        </c:choose>
                    <c:if test="${not empty crewErrorMessage}">
                    <h2 id="flash" class="alert alert-success">${crewErrorMessage}</h2>
                        <c:remove var="crewErrorMessage"/>
                    </c:if>
         <div class="form-group row">
             <label for="firstName" class="col-md-3 col-form-label">First Name</label>
             <div class="col-md-9">
                 <input type="text" class="form-control"
                        name="firstname" id="firstName" placeholder="First Name"
                 value="${crew.firstName}" required>
                 <div class="invalid-feedback">Crew first name is required!</div>
             </div>
         </div>
         <div class="form-group row">
             <label for="lastName" class="col-md-3 col-form-label">Last Name</label>
             <div class="col-md-9">
                 <input type="text" class="form-control"
                        name="lastname" id="lastName" placeholder="Last Name"
                         value="${crew.lastName}" required>
                 <div class="invalid-feedback">Crew last name is required!</div>
             </div>
         </div>
         <div class="form-group row">
             <label for="inputEmail" class="col-md-3 col-form-label">Email</label>
             <div class="col-md-9">
                 <input type="email" class="form-control"
                        name="email" id="inputEmail" placeholder="Email" required
                        value="${crew.email}">
                 <div class="invalid-feedback">Crew email is required!</div>
             </div>
         </div>
         <div class="form-group row">
             <label for="profession" class="col-md-3 col-form-label">Profession</label>
             <div class="col-md-9">
                 <input type="text" class="form-control"
                        name="profession" id="profession" placeholder="Profession" required
                        value="${crew.profession}">
                 <div class="invalid-feedback">Crew profession is required!</div>
             </div>
         </div>

    <div class="form-group row">
        <label for="bio" class="col-md-3 col-form-label" >Biography</label>
        <div class="col-md-9">
            <textarea class="form-control"name="biography" id="bio" rows="6" required>${crew.biography}</textarea>
            <div class="invalid-feedback">Crew  biography is required!</div>
        </div>
    </div>
        <div class="form-group row">
            <div class="col-md-9">
                <button type="submit"
                        name="saveCrew" class="btn btn-lg btn-success">Save Crew</button>
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
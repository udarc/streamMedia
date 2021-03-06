<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/16/20
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:choose>
    <c:when test="${faq ne null}">
        <c:set var="title" value="Edit Faq" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Add Faq" />
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
            <c:when test="${faq ne null}">
            <form action="faq-edit" method="post" class="card col-md-10 offset-md-1 needs-validation" novalidate>
                <h1 class="text-center">Edit FAQ</h1>
                <input type="hidden" name="uid" value="${faq.faqId}">
            </c:when>
            <c:otherwise>
            <form action="faq-new" method="post" class="card col-md-10 offset-md-1 was-validated">
                <h1 class="text-center">Add FAQ</h1>
            </c:otherwise>
        </c:choose>
                <c:if test="${not empty faqErrorMessage}">
                    <h2 id="flash" class="alert alert-danger">${faqErrorMessage}</h2>
                    <c:remove var="faqErrorMessage"/>
                </c:if>
                <div class="form-group row">
                <label for="title" class="col-md-3 col-form-label">Title</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="title" id="title" placeholder="Title"
                           required value="${faq.title}">
                    <div class="invalid-feedback">FAQ title is required!</div>
                </div>

            </div>
            <div class="form-group row">
                <label for="category" class="col-md-3 col-form-label">Category</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="category" id="category" placeholder="Category" required
                    value="${faq.category}">
                    <div class="invalid-feedback">FAQ category is required!</div>
                </div>
            </div>
            <div class="form-group row">
                <label for="description" class="col-md-3 col-form-label" >Description</label>
                <div class="col-md-9">
                    <textarea class="form-control"name="description" id="description"
                              rows="6" required>${faq.description}</textarea>
                    <div class="invalid-feedback">FAQ description is required!</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-9">
                    <button type="submit"
                            name="createFAQ" class="btn btn-lg btn-success">Save FAQ</button>
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
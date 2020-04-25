<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/16/20
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
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
            <form action="faq-edit" method="post" class="card col-sm-10 offset-sm-1 was-validated">
                <input type="hidden" name="uid" value="${faq.faqId}">
            </c:when>
            <c:otherwise>
            <form action="faq-new" method="post" class="card col-sm-10 offset-sm-1 was-validated">
            </c:otherwise>
        </c:choose>
<%--                <c:if test="${faq != null}" >--%>
<%--                    --%>
<%--                </c:if>--%>
                <div class="form-group row">
                <label for="title" class="col-sm-3 col-form-label">Title</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="title" id="title" placeholder="Title"
                           required value="${faq.title}">
                </div>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Title is required.</div>
            </div>
            <div class="form-group row">
                <label for="category" class="col-sm-3 col-form-label">Category</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="category" id="category" placeholder="Category" required
                    value="${faq.category}">
                </div>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Category is required.</div>
            </div>
            <div class="form-group row">
                <label for="description" class="col-sm-3 col-form-label" >Description</label>
                <div class="col-sm-9">
                    <textarea class="form-control"name="description" id="description"
                              rows="6">${faq.description}</textarea>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-9">
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
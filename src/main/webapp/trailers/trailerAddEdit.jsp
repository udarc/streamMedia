<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/16/20
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:choose>
    <c:when test="${trailer ne null}">
        <c:set var="title" value="Edit Trailer" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Add Trailer" />
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
        <c:when test="${trailer ne null}">
        <form class="card col-md-10 offset-md-1 needs-validation" novalidate action="trailer-edit" method="post"
              enctype="multipart/form-data">
            <input type="hidden" id="id" name="uid"  value="${trailer.trailerId}">
            </c:when>
            <c:otherwise>
            <form action="trailer-new" method="post" class="card col-md-10 offset-md-1 needs-validation" novalidate
                  enctype="multipart/form-data">
                </c:otherwise>
                </c:choose>
                    <c:if test="${not empty trailerErrorMessage}">
                        <h2 id="flash" class="alert alert-danger">${trailerErrorMessage}</h2>
                        <c:remove var="trailerErrorMessage"/>
                    </c:if>
                <div class="form-group row">
                    <label for="title" class="col-md-3 col-form-label">Title</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" name="title"
                               id="title" value="${trailer.title}" placeholder="Trailer Title" required>
                        <div class="invalid-feedback">Trailer title is required!</div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="author" class="col-md-3 col-form-label">Author</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control"
                               name="author" id="author" placeholder="Author" value="${trailer.author}" required>
                        <div class="invalid-feedback">Trailer author is required!</div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="duration" class="col-md-3 col-form-label">Duration</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" name="duration" id="duration"
                               value="${trailer.duration}" placeholder="Duration (00:00:00)" required>
                        <div class="invalid-feedback">Trailer duration is required!</div>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cover" class="col-md-3 col-form-label">Cover</label>
                    <div class="col-md-9">
                        <input type="file" class="form-control"
                               name="cover"  value="${trailer.cover}" id="cover" placeholder="Cover">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="pub_date" class="col-md-3 col-form-label">Publication Date</label>
                    <div class="col-md-9">
                        <input type="datetime-local" class="form-control"
                               name="pub_date" id="pub_date" value="${trailer.publicationDate}" placeholder="Publication Date">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="video" class="col-md-3 col-form-label">Video</label>
                    <div class="col-md-9">
                        <input type="file" class="form-control"
                               name="video" id="video" value="${trailer.video}" placeholder="Video">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="link" class="col-md-3 col-form-label">Link To Video</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control"
                               name="link" id="link" value="${trailer.link}" placeholder="Link">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="summary" class="col-md-3 col-form-label" >Summary</label>
                    <div class="col-md-9">
                    <textarea class="form-control"name="summary" id="Summary"
                              rows="6" required>${trailer.summary}</textarea>
                        <div class="invalid-feedback">Trailer summary is required!</div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-9">
                        <button type="submit"
                                name="saveTrailer" class="btn btn-lg btn-success">Save Trailer</button>
                    </div>
                </div>
            </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>





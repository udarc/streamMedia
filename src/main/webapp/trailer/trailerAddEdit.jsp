<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/16/20
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="form row">
        <c:choose>
        <c:when test="${trailer ne null}">
        <form class="card col-sm-10 offset-sm-1" action="trailer-edit" method="post"
              enctype="multipart/form-data">
            <input type="hidden" id="id" name="uid"  value="${trailer.trailerId}">
            </c:when>
            <c:otherwise>
            <form action="add-trailer" method="post" class="card col-sm-10 offset-sm-1 was-validated"
                  enctype="multipart/form-data">
                </c:otherwise>
                </c:choose>
                <div class="form-group row">
                    <label for="title" class="col-sm-3 col-form-label">Title</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="title"
                               id="title" value="${trailer.title}" placeholder="Trailer Title">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="author" class="col-sm-3 col-form-label">Author</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control"
                               name="author" id="author" placeholder="Author" value="${trailer.author}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="duration" class="col-sm-3 col-form-label">Duration</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control"
                               name="duration" id="duration" value="${trailer.duration}" placeholder="Duration">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cover" class="col-sm-3 col-form-label">Cover</label>
                    <div class="col-sm-9">
                        <input type="file" class="form-control"
                               name="cover"  value="${trailer.cover}" id="cover" placeholder="Cover">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="pub_date" class="col-sm-3 col-form-label">Publication Date</label>
                    <div class="col-sm-9">
                        <input type="datetime-local" class="form-control"
                               name="pub_date" id="pub_date" value="${trailer.publicationDate}" placeholder="Publication Date">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="video" class="col-sm-3 col-form-label">Video</label>
                    <div class="col-sm-9">
                        <input type="file" class="form-control"
                               name="video" id="video" value="${trailer.video}" placeholder="Video">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="link" class="col-sm-3 col-form-label">Link To Video</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control"
                               name="link" id="link" value="${trailer.link}" placeholder="Link">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="summary" class="col-sm-3 col-form-label" >Summary</label>
                    <div class="col-sm-9">
                    <textarea class="form-control"name="summary" id="Summary"
                              rows="6">${trailer.summary}</textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-9">
                        <button type="submit"
                                name="saveTrailer" class="btn btn-lg btn-success">Save Trailer</button>
                    </div>
                </div>
            </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>





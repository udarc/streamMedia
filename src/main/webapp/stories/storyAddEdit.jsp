<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 4/23/20
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:choose>
    <c:when test="${story ne null}">
        <c:set var="title" value="Edit Story" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Add Story" />
    </c:otherwise>
</c:choose>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <h1 class="text-center">Add Short Story</h1>
    <c:if test="${not empty storyErrorMessage}">
        <h2 id="flash" class="alert alert-danger">${storyErrorMessage}</h2>
        <c:remove var="storyErrorMessage"/>
    </c:if>
    <c:if test="${not empty unsupportedExtension}">
        <h2 id="flash" class="alert alert-danger">${unsupportedExtension}</h2>
        <c:remove var="unsupportedExtension"/>
    </c:if>

    <div class="form row">
        <c:choose>
        <c:when test="${story ne null}">
        <form class="card col-md-10 offset-md-1 was-validated" action="story-edit" method="post"
              enctype="multipart/form-data">
            <input type="hidden" id="id" name="uid"  value="${story.shortStoryId}">
            </c:when>
            <c:otherwise>
            <form action="story-new" method="post" class="card col-md-10 offset-md-1 was-validated"
                  enctype="multipart/form-data">
                </c:otherwise>
                </c:choose>
                <div class="form-group row">
                    <label for="title" class="col-md-3 col-form-label">Title</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" name="title"
                               id="title" value="${story.title}" placeholder="Story Title">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="author" class="col-md-3 col-form-label">Author</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control"
                               name="author" id="author" placeholder="Author" value="${story.author}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="cover" class="col-md-3 col-form-label">Cover</label>
                    <div class="col-md-9">
                        <input type="file" class="form-control"
                               name="cover"  value="${story.cover}" id="cover" placeholder="Cover">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="pub_date" class="col-md-3 col-form-label">Publication Date</label>
                    <div class="col-md-9">
                        <input type="datetime-local" class="form-control"
                               name="pub_date" id="pub_date" value="${story.publicationDate}" placeholder="Publication Date">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="description" class="col-md-3 col-form-label" >Description</label>
                    <div class="col-md-9">
                    <textarea class="form-control"name="description" id="description"
                              rows="6">${story.description}</textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-9">
                        <button type="submit"
                                name="saveStory" class="btn btn-lg btn-success">Save Story</button>
                    </div>
                </div>
            </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

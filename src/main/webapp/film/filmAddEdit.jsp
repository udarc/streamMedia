<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/16/20
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:choose>
    <c:when test="${film ne null}">
        <c:set var="title" value="Edit Film" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Add Film" />
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
            <c:when test="${film ne null}">
            <h1 class="text-center">Edit Film</h1>
            <form class="card col-md-10 offset-md-1" action="film-edit" method="post" enctype="multipart/form-data">
                <input type="hidden" name="uid" value="${film.filmId}">
            </c:when>
            <c:otherwise>
            <h1 class="text-center">Add Film</h1>
            <form class="card col-md-10 offset-md-1" action="film-new" method="post" enctype="multipart/form-data">
            </c:otherwise>
        </c:choose>
                <c:if test="${not empty filmErrorMessage}">
                    <h2 id="flash" class="alert alert-success">${filmErrorMessage}</h2>
                    <c:remove var="filmErrorMessage"/>
                </c:if>
            <div class="form-group row">
                <label for="title" class="col-md-3 col-form-label">Title</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="title"
                           id="title" placeholder="Film Title" value="${film.title}">
                </div>
            </div>
            <div class="form-group row">
                <label for="genre" class="col-md-3 col-form-label">Genre(s)</label>
                <div class="col-md-7">
                <select name="genre" class="form-control" id="genre" multiple>
                    <option value="select a Genre">Select</option>
                    <c:choose>
                        <c:when test="${film.genres ne null}">
                            <c:forEach var="genre" items="${genres}">
                                <option value="${genre.genreId}">${genre.title}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="genre" items="${genres}">
                                <option value="${genre.genreId}">${genre.title}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
                </div>
                <div class="col-md-2"><a class="btn btn-secondary" href="genre-new">Add Genre</a></div>
            </div>
            <div class="form-group row">
                <label for="episode" class="col-md-3 col-form-label">Episode</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="episode" id="episode" placeholder="Episode" value="${film.episode}">
                </div>
            </div>
            <div class="form-group row">
                <label for="director" class="col-md-3 col-form-label">Director</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="director" id="director" placeholder="Director"
                    value="${film.director}">
                </div>
            </div>
                <div class="form-group row">
                    <label for="duration" class="col-md-3 col-form-label">Duration</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control"
                               name="duration" id="duration"  placeholder="Duration"
                        value="${film.duration}">
                    </div>
                </div>
            <div class="form-group row">
                <label for="cover" class="col-md-3 col-form-label">Cover</label>
                <div class="col-md-9">
                    <input type="file" class="form-control"
                           name="cover" id="cover" placeholder="Cover" value="${film.cover}">
                </div>
            </div>
            <div class="form-group row">
                <label for="pub_date" class="col-md-3 col-form-label">Publication Date</label>
                <div class="col-md-9">
                    <input type="datetime-local" class="form-control"
                           name="pub_date" id="pub_date" placeholder="Publication Date"
                    value="${film.publicationDate}">
                </div>
            </div>
            <div class="form-group row">
                <label for="video" class="col-md-3 col-form-label">Video</label>
                <div class="col-md-9">
                    <input type="file" class="form-control"
                           name="video" id="video" placeholder="Video" value="${film.video}">
                </div>
            </div>
                <div class="form-group row">
                    <label for="link" class="col-md-3 col-form-label">Link</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control"
                               name="episode" id="link" placeholder="Link" value="${film.link}">
                    </div>
                </div>
            <div class="form-group row">
                <label for="genre" class="col-md-3 col-form-label">Crew(s)</label>
                <div class="col-md-7">
                <select name="crew" class="form-control" id="crew" multiple>
                    <option value="select crew members">Select Crew</option>
                    <c:choose>
                        <c:when test="${film.crews ne null}">
                            <c:forEach var="crew" items="${crews}">
                                <option value="${crew.crewId}">${crew.firstName} ${crew.lastName}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="crew" items="${crews}">
                                <option value="${crew.crewId}">${crew.firstName} ${crew.lastName}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                 </select>

                </div>
                <div class="col-md-2"><a class="btn btn-secondary" href="crew-new">Add Crew</a></div>
            </div>
            <div class="form-group row">
                <label for="summary" class="col-md-3 col-form-label" >Summary</label>
                <div class="col-md-9">
                    <textarea class="form-control"name="summary" id="Summary" rows="6">${film.summary}</textarea>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-9">
                    <button type="submit"
                            name="sendMessage" class="btn btn-lg btn-success">Save Film</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>






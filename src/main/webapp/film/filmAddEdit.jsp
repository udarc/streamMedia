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
            <c:when test="${film ne null}">
            <h1 class="text-center">Edit Film</h1>
            <form class="card col-sm-10 offset-sm-1" action="film-edit" method="post">
                <input type="hidden" name="uid" value="${film.filmId}">
            </c:when>
            <c:otherwise>
            <h1 class="text-center">Add Film</h1>
            <form class="card col-sm-10 offset-sm-1" action="film-new" method="post">
            </c:otherwise>
        </c:choose>

            <div class="form-group row">
                <label for="title" class="col-sm-3 col-form-label">Title</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="title"
                           id="title" placeholder="Film Title">
                </div>
            </div>
            <div class="form-group row">
                <label for="genre" class="col-sm-3 col-form-label">Genre(s)</label>
                <div class="col-sm-7">
                <select name="genre" class="form-control" id="genre" multiple>
                    <option value="select a Genre">Select</option>
                    <c:choose>
                        <c:when test="${film.genres ne null}">
                            <c:forEach var="genre" items="${film.genres}">
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
                <div class="col-sm-2"><a class="btn btn-secondary" href="genre-new">Add Genre</a></div>
            </div>
            <div class="form-group row">
                <label for="episode" class="col-sm-3 col-form-label">Episode</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="episode" id="episode" placeholder="Episode">
                </div>
            </div>
            <div class="form-group row">
                <label for="director" class="col-sm-3 col-form-label">Director</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="director" id="director" placeholder="Director">
                </div>
            </div>
            <div class="form-group row">
                <label for="duration" class="col-sm-3 col-form-label">Duration</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="duration" id="duration" placeholder="Duration">
                </div>
            </div>
            <div class="form-group row">
                <label for="cover" class="col-sm-3 col-form-label">Cover</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="cover" id="cover" placeholder="Cover">
                </div>
            </div>
            <div class="form-group row">
                <label for="pub_date" class="col-sm-3 col-form-label">Publication Date</label>
                <div class="col-sm-9">
                    <input type="date" class="form-control"
                           name="pub_date" id="pub_date" placeholder="Publication Date">
                </div>
            </div>
            <div class="form-group row">
                <label for="video" class="col-sm-3 col-form-label">Video</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="video" id="video" placeholder="Video">
                </div>
            </div>
            <div class="form-group row">
                <label for="genre" class="col-sm-3 col-form-label">Crew(s)</label>
                <div class="col-sm-7">
                <select name="crew" class="form-control" id="crew" multiple>
                    <option value="select crew members">Select Crew</option>
                    <c:choose>
                        <c:when test="${film.crews ne null}">
                            <c:forEach var="crew" items="${film.crews}">
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
                <div class="col-sm-2"><a class="btn btn-secondary" href="crew-new">Add Crew</a></div>
            </div>
            <div class="form-group row">
                <label for="summary" class="col-sm-3 col-form-label" >Summary</label>
                <div class="col-sm-9">
                    <textarea class="form-control"name="summary" id="Summary" rows="6"></textarea>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-9">
                    <button type="submit"
                            name="sendMessage" class="btn btn-lg btn-success">Save Film</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>






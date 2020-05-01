<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/18/20
  Time: 4:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Film List" />
<%@include file="../head.jsp"%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper" role="main">
    <div class="row">
        <div class="col-sm-9">
            <h1 class="text-center">List of Films</h1>
            <c:if test="${pageContext.request.isUserInRole('admin')}">
                            <span> <a href="film-new" class="btn btn-success ml-auto">
                                <i class="fas fa-plus-square fa-1x"></i>Add Film</a>
                            </span>
            </c:if>
            <div     class="row">

            <c:choose>
                <c:when test="${films ne null}">
                    <c:forEach var="film" items="${films}" >

                        <div class="col-sm-4">
                            <div class="card">
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="180"
                                     xmlns="http://www.w3.org/2000/svg" aria-label="Placeholder: Image cap"
                                     preserveAspectRatio="xMidYMid slice" role="img"><title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#868e96"/>
                                    <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text></svg>

                                <div class="card-body">
                                    <h2 class="card-title">Title: ${film.title}
                                            <%--                                        <a class="btn btn-outline-primary" --%>
                                            <%--                                                              href="trailer-detail?uid=<c:out value="${trailer.trailerId}"/>">${trailer.title}</a>--%>
                                    </h2>

                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Time: ${film.duration}</li>
                                        <li class="list-group-item">Directed by: ${film.director}</li>
                                        <li class="list-group-item">Publication Date: ${film.publicationDate}</li>
                                        <c:forEach var="genre" items="${film.genres}">
                                            <li class="list-group-item">${genre.title}</li>
                                        </c:forEach>
                                        <c:forEach var="genre" items="${film.crews}">
                                            <li class="list-group-item">${genre.fullName}</li>
                                        </c:forEach>
                                        <li class="list-group-item">Publication Date: ${film.publicationDate}</li>
                                        <li class="list-group-item">${film.summary}</li>
                                    </ul>
                                </div>
                                <div class="card-body">
                                <span class="btn-media-right">
                                <a  class="card-link btn btn-secondary" href="film-detail?uid=<c:out value="${film.filmId}"/>">Film Details</a>
                                <c:if test="${pageContext.request.isUserInRole(\"admin\")}">
                                    <a class="card-link btn btn-outline-primary" href="film-edit?uid=${film.filmId}">Edit</a>
                                    <a class="card-link btn btn-outline-danger" href="film-delete?uid=${film.filmId}}">Delete</a>
                                </c:if>
                            </span>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="col-md-6 offset-3">
                        <p>Film  Not Found!</p>
                    </div>
                </c:otherwise>
            </c:choose>
            </div>
        </div>
        <div class="col-sm-3">
            <ul class="list-group list-group-flush">
                <h2>Internal Data</h2>
                <c:if test="${pageContext.request.isUserInRole('admin')}">
                    <li class="list-group-item"><a class="btn btn-link" href="crews">All Crews</a></li>
                </c:if>
                <li class="list-group-item"><a class="btn btn-link" href="genres">All Movie Genres</a></li>
            </ul>
            <ul class="list-group list-group-flush">
                <h2>External Data</h2>
                <li class="list-group-item list-group-item-primary"><h3>
                    <a class="btn btn-primary" target="_blank"
                       href="https://developers.themoviedb.org/3/movies/get-movie-details">From Movies Database API</a>
                </h3></li>
                    <li class="list-group-item"><a class="btn btn-link" href="now-playing-movies">Now Playing Movies</a></li>
                <li class="list-group-item"><a class="btn btn-link" href="top-rated-movies">Top Rated</a></li>
                <li class="list-group-item"><a class="btn btn-link" href="popular-movies">Popular</a></li>
                <li class="list-group-item"><a class="btn btn-link" href="upcoming-movies">Up Coming</a></li>
            </ul>
        </div>
    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

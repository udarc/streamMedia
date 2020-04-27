<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/24/20
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Now Playing Movies" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
        <h1 class="alert alert-info text-center">Now Playing Movies</h1>
            <div class="row">
            <c:choose>
            <c:when test="${playingMovies ne null}">
            <c:forEach var="movie" items="${playingMovies}" >
                <div class="col-sm-3">
            <ul class="list-group list-group-flush">
                <li class="list-group-item "><h2>Title: ${movie.title}</h2></li>
                <li class="list-group-item ">Original Title:
                        ${movie.originalTitle}</li>
                <li class="list-group-item ">Popularity:
                        ${movie.popularity}</li>
                <li class="list-group-item ">Average Vote:
                        ${movie.voteAverage}</li>
            <li class="list-group-item">${movie.overview}</li>
            <li class="list-group-item">Release Date: ${movie.releaseDate}</li>
        </ul>
            </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <ul list-group >
                        <li class="list-group-item ">NoW Playing Movies Not found!</li>
                    </ul>
                </c:otherwise>
            </c:choose>
    </div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

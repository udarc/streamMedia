<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: 2:10 PMM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Film Details" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper" role="main">
    <h1 class="text-center">Film Details</h1>
    <c:if test="${not empty filmEditSuccessMessage}">
        <h2 id="flash" class="alert alert-success">${filmEditSuccessMessage}</h2>
        <c:remove var="filmEditSuccessMessage"/>
    </c:if>
        <div class="row">
            <c:choose>
                <c:when test="${film ne null}">
                    <div class="col-sm-6 offset-sm-3">
                        <div class="card">
                            <c:choose>

                            <c:when test="${ not empty film.video}">

                                <video width="400" controls poster="${film.cover}">
                                    <source src="${film.video}" type="video/mp4">
                                        <%--                                    <source src="movie.ogg" type="video/ogg">--%>
                                        ${film.video}
                                </video>

                            </c:when>
                            <c:otherwise>
                            <video width="320" height="240" controls poster="images/film1.jpp">
                                <source class="rounded mx-auto d-block img-fluid" src="media/filmv.mp4">
                                </c:otherwise>
                                </c:choose>
                                <div class="card-body">

                                    <h2 class="card-title"><a href=""></a>${film.title}</h2>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">${film.director}</li>
                                        <li class="list-group-item">${film.duration}</li>
                                        <c:forEach var="genre" items="${film.genres}">
                                            <li class="list-group-item list-group-item-info">Genres: ${genre.title}</li>
                                        </c:forEach>
                                        <c:forEach var="crew" items="${film.crews}">
                                            <li class="list-group-item list-group-item-info">Crews: ${crew.fullName}</li>
                                        </c:forEach>
                                    </ul>
                                    <p class="card-text">${film.summary}</p>
                                    <p>Published ${film.publicationDate}</p>
                                </div>

                                <div class="card-body">
                                    <c:if test="${pageContext.request.isUserInRole('admin')}">
                                        <a class="card-link btn btn-primary" href="film-edit?uid=<c:out value="${film.filmId}"/>">
                                            <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                        <a  class="card-link btn btn-danger" href="film-delete?uid=<c:out value="${film.filmId}"/>">
                                            <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a>
                                    </c:if>

                                </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Film was not found!</p>
                </c:otherwise>
            </c:choose>

        </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
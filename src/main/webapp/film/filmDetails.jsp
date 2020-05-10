<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: 2:10 PMM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp" %>
<c:set var="title" value="Film Details"/>
<%@include file="../head.jsp" %>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp" %>
<main class="container-fluid wrapper" role="main">
    <h1 class="text-center"> <span onclick="goBack()">
        <i class="fas fa-arrow-left fa-1x" aria-hidden="true"></i></span> Film Details</h1>
    <c:if test="${not empty filmEditSuccessMessage}">
        <h2 id="flash" class="alert alert-success">${filmEditSuccessMessage}</h2>
        <c:remove var="filmEditSuccessMessage"/>
    </c:if>
    <div class="row">
        <c:choose>
        <c:when test="${film ne null}">
        <div class="col-md-6 offset-md-3">
            <div class="card">
                <div class="card-img-top">
                <c:choose>

                <c:when test="${ not empty film.video}">

                    <video width="400" controls poster="${film.cover}">
                        <source src="${film.video}" type="video/mp4">
                    </video>

                </c:when>
                <c:otherwise>
                <video width="320" height="240" controls poster="media/trailer1.jpp">
                    <source class="rounded mx-auto d-block img-fluid" src="media/trailer.mp4">
                    </c:otherwise>
                    </c:choose>
                </div>
                    <div class="card-body">
                        <h3 class="card-title">${film.title}</h3>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">${film.director}</li>
                            <li class="list-group-item">${film.duration}</li>
                            <li class="list-group-item list-group-item-info">
                            <c:forEach var="genre" items="${film.genres}">
                                <span class="badge badge-light">Genres: ${genre.title}</span>
                            </c:forEach>
                            </li>
                            <li class="list-group-item list-group-item-info">
                            <c:forEach var="crew" items="${film.crews}">
                                <span  class="badge badge-light">Crews: ${crew.fullName}</span>
                            </c:forEach>
                            </li>
                        </ul>
                        <p class="card-text">${film.summary}</p>
                        <p>Published ${film.publicationDate}</p>
                    </div>

                    <div class="card-body">
                        <c:if test="${pageContext.request.isUserInRole('admin')}">
                            <a class="card-link btn btn-primary"
                               href="film-edit?uid=<c:out value="${film.filmId}"/>">
                                <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                            <a class="card-link btn btn-danger"
                               href="film-delete?uid=<c:out value="${film.filmId}"/>">
                                <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a>
                        </c:if>

                    </div>


                    </c:when>
                    <c:otherwise>
                    <p>Film was not found!</p>
                    </c:otherwise>
                    </c:choose>
            </div>
        </div>
    </div>
</main>
<%@include file="../footer.jsp" %>
<%@include file="../afterFooter.jsp" %>
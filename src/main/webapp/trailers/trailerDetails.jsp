<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/20/20
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid" role="main">
    <h1 class="text-center">List of Trailers</h1>
    <div class="row">
        <c:choose>
            <c:when test="${trailer ne null}">
                <div class="col-sm-6 offset-sm-3">
                    <div class="card">
                        <c:choose>

                            <c:when test="${ not empty trailer.video}">

                                <video width="400" controls poster="${trailer.cover}">
                                    <source src="${trailer.video}" type="video/mp4">
<%--                                    <source src="movie.ogg" type="video/ogg">--%>
                                        ${trailer.video}
                                </video>

                            </c:when>
                            <c:otherwise>
                            <video width="320" height="240" controls poster="images/trailer1.jpp">
                                <source class="rounded mx-auto d-block img-fluid" src="media/trailerv.mp4">
                            </c:otherwise>
                        </c:choose>
                        <div class="card-body">

                            <h2 class="card-title"><a href=""></a>${trailer.title}</h2>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${trailer.author}</li>
                                <li class="list-group-item">${trailer.duration}</li>
                            </ul>
                            <p class="card-text">${trailer.summary}</p>
                            <p>Published ${trailer.publicationDate}</p>
                        </div>

                        <div class="card-body">
                            <c:if test="${pageContext.request.isUserInRole('admin')}">
                                <a class="card-link btn btn-primary" href="trailer-edit?uid=<c:out value="${trailer.trailerId}"/>">
                                    <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                <a  class="card-link btn btn-danger" href="remove-trailer?uid=<c:out value="${trailer.trailerId}"/>">
                                    <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a>
                            </c:if>

                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>Trailer was not found!</p>
            </c:otherwise>
        </c:choose>

    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
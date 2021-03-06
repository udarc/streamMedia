<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/20/20
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Trailer Details" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid" role="main">
    <h1 class="text-center"> <span onclick="goBack()">
        <i class="fas fa-arrow-left fa-1x" aria-hidden="true"></i></span> Trailer Details</h1>
    <c:if test="${not empty trailerEditSuccessMessage || not empty unsupportedVideoExtension
    || unsupportedExtension }">
            <h3 id="flash" ><span class="alert alert-success"> ${trailerEditSuccessMessage}</span>
                <span class="alert alert-success">${unsupportedVideoExtension} ${unsupportedExtension}</span>
            </h3>
            <c:remove var="trailerEditSuccessMessage"/>
            <c:remove var="unsupportedVideoExtension"/>
            <c:remove var="unsupportedExtension"/>
        </c:if>
    <div class="row">
        <c:choose>
            <c:when test="${trailer ne null}">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <c:choose>

                            <c:when test="${ not empty trailer.video}">

                                <video width="400" controls poster="${trailer.cover}">
                                    <source class="rounded mx-auto d-block img-fluid" src="${trailer.video}" type="video/mp4">
                                    <source src="movie.ogg" type="video/ogg">
                                        ${trailer.video}
                                </video>
                            </c:when>
                            <c:otherwise>
                            <video width="320" height="240" controls poster="media/trailer1.jpp">
                                <source class="rounded mx-auto d-block img-fluid" src="media/trailer.mp4">
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
                                <a  class="card-link btn btn-danger" href="trailer-delete?uid=<c:out value="${trailer.trailerId}"/>">
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
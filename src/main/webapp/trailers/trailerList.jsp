<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/18/20
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper" role="main">
    <h1 class="text-center">List of Trailers</h1>
    <c:if test="${pageContext.request.isUserInRole(\"admin\")}" >
        <p class="ml-auto"><a href="add-trailer" class="btn btn-success" >Add Trailer</a></p>
    </c:if>
    <div class="row">
        <c:choose>
            <c:when test="${trailers ne null}">
                <c:forEach var="trailer" items="${trailers}" >

                    <div class="col-sm-3">
                        <div class="card">

                            <c:choose>

                                <c:when test="${ not empty trailer.video}">

                                    <video width="400" controls poster="${trailer.cover}">
                                        <source src="${trailer.video}" type="video/mp4">
                                    </video>

                                </c:when>
                                <c:otherwise>
                                    <video width="320" height="240" controls poster="media/trailer1.jpg">
                                        <source class="rounded mx-auto d-block img-fluid" src="media/trailerv.mp4" type="video/mp4" alt="trailer video">
                                    </video>
                                </c:otherwise>
                            </c:choose>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${ not empty trailer.cover}">--%>
<%--                                        <img class="rounded mx-auto d-block img-fluid" src="${trailer.cover}" alt="trailer cover">--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>--%>
<%--                                        <img class="rounded mx-auto d-block img-fluid" src="images/trailerc.jpg" alt="trailer cover">--%>
<%--                                    </c:otherwise>--%>
<%--                                </c:choose>--%>

                            <div class="card-body">
                                <h2 class="card-title"><a class="btn btn-outline-primary" href="trailer-detail?uid=<c:out value="${trailer.trailerId}"/>">${trailer.title}</a></h2>

                                <p class="card-text">${trailer.summary}</p>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Published by ${trailer.author} on ${trailer.publicationDate}</li>
                            </ul>
                            <div class="card-body">
                                <p><a  class="card-link btn btn-secondary" href="trailer-detail?uid=<c:out value="${trailer.trailerId}"/>">Trailer Details</a></p>
                                <span class="btn-media-right">
                                <c:if test="${pageContext.request.isUserInRole(\"admin\")}">
                                    <a class="card-link btn btn-outline-primary" href="trailer-edit?uid=${trailer.trailerId}">Edit</a>
                                    <a class="card-link btn btn-outline-danger" href="remove-trailer?uid=${trailer.trailerId}">Remove</a>
                                </c:if>
                            </span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-md-6 offset-3">
                    <p>No Trailer object created Yet!</p>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#">Next</a>
            </li>
        </ul>
    </nav>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Genre List" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
    <div class="row">
        <div class="col-sm-8">

            <ul class="list-group list-group-info list-group-flush">
                <c:choose>
                    <c:when test="${fn:length(genres) gt 0}">
                        <li class="list-group-item list-group-item-primary"><h1>Genre List</h1></li>

                        <c:forEach var="genre" items="${genres}">

                            <li class="list-group-item "><a href="genre-details?uid=${genre.genreId}">${genre.title}</a>
                                <c:if test="${pageContext.request.isUserInRole('admin')}">
                                <span class="btn-media-right">
                                    <a class="btn btn-primary" href="genre-edit?uid=${genre.genreId}">
                                        <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                    <a class="btn btn-danger" href="genre-delete?uid=${genre.genreId}">
                                        <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i> Delete</a>
                                </span>
                                </c:if>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li class="list-group-item ">No Genre found!</li>
                    </c:otherwise>
                </c:choose>
                <c:if test="${pageContext.request.isUserInRole('admin')}">
                    <a href="genre-new" class="btn btn-success ml-auto">
                        <i class="fas fa-plus-square fa-3x"></i>Add Genre</a>
                </c:if>
            </ul>
        </div>
        <div class="col-sm-4">
            <h2>Rest API Genres</h2>
            <%@include file="restAPIGenres.jsp"%>
        </div>
</div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
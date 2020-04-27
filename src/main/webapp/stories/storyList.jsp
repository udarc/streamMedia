<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/3/20
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Short Stories List" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper" role="main">
    <h1 class="text-center">List of Stories</h1>
    <c:if test="${pageContext.request.isUserInRole(\"admin\")}" >
        <p class="ml-auto"><a href="add-story" class="btn btn-success" >Add Story</a></p>
    </c:if>
    <div class="row">
        <c:choose>
            <c:when test="${stories ne null}">
                <c:forEach var="story" items="${stories}" >

                    <div class="col-sm-3">
                        <div class="card">

                            <c:choose>

                                <c:when test="${ not empty story.cover}">

                                    <img class="rounded mx-auto d-block img-fluid" src="${story.cover}" alt="Story Cover">

                                </c:when>
                                <c:otherwise>
                                    <img class="rounded mx-auto d-block img-fluid"  src="media/story.jpg" alt="Story Cover">
                                </c:otherwise>
                            </c:choose>
                            <div class="card-body">
                                <h2 class="card-title"><a class="btn btn-outline-primary" href="story-details?uid=<c:out value="${story.shortStoryId}"/>">${story.title}</a></h2>

                                <p class="card-text">${story.description}</p>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Published by ${story.author} on ${story.publicationDate}</li>
                            </ul>
                            <div class="card-body">
                                <p><a  class="card-link btn btn-secondary" href="story-details?uid=<c:out value="${story.shortStoryId}"/>">Story Details</a></p>
                                <span class="btn-media-right">
                                <c:if test="${pageContext.request.isUserInRole(\"admin\")}">
                                    <a class="card-link btn btn-outline-primary" href="story-edit?uid=${story.shortStoryId}">Edit</a>
                                    <a class="card-link btn btn-outline-danger" href="story-delete?uid=${story.shortStoryId}">Remove</a>
                                </c:if>
                            </span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-md-6 offset-3">
                    <p>No Story object created Yet!</p>
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


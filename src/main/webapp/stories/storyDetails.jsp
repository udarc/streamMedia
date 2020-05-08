<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 4/23/20
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Short Story Details" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid" role="main">
    <h1 class="text-center"> <span onclick="goBack()">
        <i class="fas fa-arrow-left fa-1x" aria-hidden="true"></i></span> Short Story Details</h1>
    <c:if test="${not empty storyEditSuccessMessage}">
        <h2 id="flash" class="alert alert-danger">${storyEditSuccessMessage}</h2>
        <c:remove var="storyEditSuccessMessage"/>
    </c:if>
    <div class="row">
        <c:choose>
            <c:when test="${story ne null}">
                <div class="col-md-6 offset-md-3">
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

                                <h2 class="card-title"><a href=""></a>${story.title}</h2>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${story.author}</li>
                                </ul>
                                <p class="card-text">${story.description}</p>
                                <p>Published ${story.publicationDate}</p>
                            </div>

                            <div class="card-body">
                                <c:if test="${pageContext.request.isUserInRole('admin')}">
                                    <a class="card-link btn btn-primary" href="story-edit?uid=<c:out value="${story.shortStoryId}"/>">
                                        <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                    <a  class="card-link btn btn-danger" href="story-delete?uid=<c:out value="${story.shortStoryId}"/>">
                                        <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a>
                                </c:if>

                            </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>Story was not found!</p>
            </c:otherwise>
        </c:choose>

    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
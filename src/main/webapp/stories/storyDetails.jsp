<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/23/20
  Time: 11:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid" role="main">
    <h1 class="text-center">List of Storys</h1>
    <div class="row">
        <c:choose>
            <c:when test="${story ne null}">
                <div class="col-sm-6 offset-sm-3">
                    <div class="card">
                        <c:choose>

                        <c:when test="${ not empty story.cover}">

                            <img src="$story.cover" alt="">

                        </c:when>
                        <c:otherwise>
                        <img src="images/story1.jpp" alt="Story Cover ">
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
                                    <a class="card-link btn btn-primary" href="story-edit?uid=<c:out value="${story.storyId}"/>">
                                        <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                    <a  class="card-link btn btn-danger" href="remove-story?uid=<c:out value="${story.storyId}"/>">
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
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
        <div class="col-md-8">
            <h1 class="text-center"><h1>Genre List</h1></h1>
            <c:if test="${pageContext.request.isUserInRole('admin')}">
                <p class="ml-auto"><a href="genre-new" class="btn btn-success">
                    <i class="fas fa-plus-square fa-3x"></i>Add Genre</a></p>
            </c:if>
            <c:if test="${not empty filmAddSuccessMessage}">
                <h3 id="flash" class="alert alert-success">${filmAddSuccessMessage}</h3>
                <c:remove var="filmAddSuccessMessage"/>
            </c:if>

            <ul class="list-group list-group-info list-group-flush">
                <c:choose>
                    <c:when test="${fn:length(genres) gt 0}">
                        <c:forEach var="genre" items="${genres}">

                            <li class="list-group-item "><a href="genre-details?uid=${genre.genreId}">${genre.title}</a>
                                <c:if test="${pageContext.request.isUserInRole('admin')}">
                                <span class="btn-media-right">
                                    <a class="btn btn-primary" href="genre-edit?uid=${genre.genreId}">
                                        <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                   <a class="btn btn-danger" href="genre-delete?uid=${genre.genreId}" Onclick="return confirmDeletion();">
                                    <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a>
                                </span>
                                </c:if>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li class="list-group-item ">No Genre found!</li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
        <div class="col-md-4">
            <h2>Rest API Genres</h2>
            <%@include file="restAPIGenres.jsp"%>
        </div>

<%--        <!-- Modal -->--%>
<%--        <div class="modal modal-danger fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
<%--            <div class="modal-dialog" role="document">--%>
<%--                <div class="modal-content">--%>
<%--                    <form action= method="post" id="deleteForm">--%>
<%--                        <input type="hidden" name="uid" value="">--%>
<%--                    <div class="modal-header">--%>
<%--                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
<%--                        <h4 class="modal-title text-center" id="myModalLabel">Delete Confirmation</h4>--%>
<%--                    </div>--%>

<%--                        <div class="modal-body">--%>
<%--                            <p class="text-center">--%>
<%--                                Are you sure you want to delete this?--%>
<%--                                                          </p>--%>

<%--                        </div>--%>
<%--                        <div class="modal-footer">--%>
<%--                            <button type="button" class="btn btn-default" data-dismiss="modal">No, Cancel</button>--%>
<%--                            <button type="submit" class="btn btn-danger">Yes, Delete</button>--%>
<%--                            <input type="hidden" name="uid" id="id">--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
</div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
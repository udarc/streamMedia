<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/3/20
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Book List" />
<%@include file="../head.jsp"%>
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container wrapper" >
    <div class="row">
            <div class="col-md-8 col-lg-9">
                <h1 class="text-center">Book List</h1>
                <c:if test="${pageContext.request.isUserInRole('admin')}">
                    <p class="ml-auto"><a href="book-new" class="btn btn-success">
                        <i class="fas fa-plus-square fa-1x"></i>Add Book</a></p>
                </c:if>
                <c:if test="${not empty addBookSuccess}">
                    <h2 id="flash" class="alert alert-success">${addBookSuccess}</h2>
                    <c:remove var="addBookSuccess"/>
                </c:if>
                    <c:choose>
                        <c:when test="${books ne null}">
                            <c:forEach var="book" items="${books}">
                        <ul class="list-group list-group-info list-group-flush">
                            <li class="list-group-item "><a href="book-details?uid=${book.bookId}">${book.title}</a></li>
                            <li class="list-group-item ">${book.ISBN}</li>
                            <li class="list-group-item ">${book.pageNumber}</li>
                                <li class="list-group-item ">
                                    <c:if test="${pageContext.request.isUserInRole('admin')}">
                                <span class="btn-media-right">
                                    <a class="btn btn-primary" href="book-edit?uid=${book.bookId}">
                                        <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                    <a class="btn btn-danger" href="book-delete?uid=${book.bookId}">
                                        <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i> Delete</a>
                                </span>
                                    </c:if>
                                </li>
                        </ul>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <ul class="list-group">
                            <li class="list-group-item ">No Book found!</li>
                            </ul>
                        </c:otherwise>
                    </c:choose>

            </div>
        <div class="col-md-4 col-lg-3">
            <ul class="list-group list-group-flush">
                <c:if test="${pageContext.request.isUserInRole('admin')}">
                    <li class="list-group-item"><a class="btn btn-primary" href="bkcategory-new"> Add Book Category</a></li>
                </c:if>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a class="btn btn-link" href="bk-categories">Book Category Genres</a></li>
                </ul>
            </ul>
        </div>
    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>


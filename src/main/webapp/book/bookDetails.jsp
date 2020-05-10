<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: 2:10 PMM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Book Details" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper" role="main">
    <h1 class="text-center"> <span onclick="goBack()">
        <i class="fas fa-arrow-left fa-1x" aria-hidden="true"></i></span> Book Details</h1>
    <c:if test="${not empty editBookSuccess}">
        <h2 id="flash" class="alert alert-success">${editBookSuccess}</h2>
        <c:remove var="editBookSuccess"/>
    </c:if>
    <div class="row">
        <c:choose>
            <c:when test="${book ne null}">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <c:choose>
                        <c:when test="${ not empty book.cover}">
                            <img class="card-img-top img-fluid" src="${book.cover}" alt="Book Cover"
                        </c:when>
                        <c:otherwise>
                        <img class="card-img-top img-fluid" src="media/book.png"/>
                            </c:otherwise>
                        </c:choose>
                        <div class="card-body">

                            <h2 class="card-title"><a href=""></a>${book.title}</h2>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item list-group-item-info">
                                <c:forEach var="category" items="${book.categories}">
                                    <span class="badge badge-light">${category.title}</span>
                                </c:forEach>
                                </li>
                                <li class="list-group-item">${book.ISBN}</li>
                                <c:if test="${ not empty book.edition}">
                                <li class="list-group-item">${book.edition}</li>
                                </c:if>
                                <li class="list-group-item">${book.author}</li>
                                <li class="list-group-item">${book.pageNumber}</li>

                            </ul>
                            <p class="card-text">${book.summary}</p>
                            <p>Published ${book.publicationDate}</p>
                        </div>

                        <div class="card-body">
                            <c:if test="${pageContext.request.isUserInRole('admin')}">
                                <a class="card-link btn btn-primary" href="book-edit?uid=<c:out value="${book.bookId}"/>">
                                    <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                <a  class="card-link btn btn-danger" href="book-delete?uid=<c:out value="${book.bookId}"/>">
                                    <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a>
                            </c:if>

                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>Book was not found!</p>
            </c:otherwise>
        </c:choose>

    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
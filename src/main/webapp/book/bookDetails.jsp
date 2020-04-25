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
    <h1 class="text-center">Book Details</h1>
    <div class="row">
        <c:choose>
            <c:when test="${book ne null}">
                <div class="col-sm-6 offset-sm-3">
                    <div class="card">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" role="img"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"/><text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text></svg>

                        <div class="card-body">

                            <h2 class="card-title"><a href=""></a>${book.title}</h2>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${book.ISBN}</li>
                                <li class="list-group-item">${book.edition}</li>
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
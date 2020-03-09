<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/20/20
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid" role="main">
    <h1 class="text-center">List of Trailers</h1> <a href="add-trailer" class="btn btn-success ml-auto" >Add Trailer</a>
    <div class="row">
        <c:choose>
            <c:when test="${trailer ne null}">
                <div class="col-sm-6 offset-sm-3">
                    <div class="card">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" role="img"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"/><text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text></svg>

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
                            <a class="card-link btn btn-primary" href="trailer-edit?uid=<c:out value="${trailer.trailerId}"/>">Edit</a>
                            <a  class="card-link btn btn-danger" href="remove-trailer?uid=<c:out value="${trailer.trailerId}"/>">Remove</a>
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
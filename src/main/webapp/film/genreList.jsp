<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="../css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
    <div class="row">
        <div class="col-sm-8">

            <ul class="list-group list-group-flush">

                <c:choose>
                    <li class="list-group-item list-group-item-info"><h1>Genre List</h1></li>

                </c:choose><c:when test="${genres.size != 0}">
                <c:forEach var="genre" items="${genres}" >
                    <li class="list-group-item list-group-item-info">${genre.title}</li>
                    <li class="list-group-item list-group-item-info">${genre.description}</li>
                </c:forEach>
            </c:when>
                <c:otherwise>
                    <li class="list-group-item list-group-item-info"><h1>Genres are Not Found!</h1></li>
                </c:otherwise>

            </ul>
        </div>

</div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
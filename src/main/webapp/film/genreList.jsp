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

            <ul class="list-group list-group-info list-group-flush">
                <c:choose>
                    <c:when test="${fn:length(genres) gt 0}">
                        <li class="list-group-item list-group-item-primary"><h1>Genre List</h1></li>

                        <c:forEach var="genre" items="${genres}">

                            <li class="list-group-item ">${genre.title}</li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li class="list-group-item ">No Genre found!</li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>

</div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
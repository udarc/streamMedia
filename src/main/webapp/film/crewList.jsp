<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
    <div class="row">
        <div class="col-sm-8">
            <h1 class="alert alert-info text-center">Crew List</h1>
            <c:choose>
            <c:when test="${crews ne null}">
            <c:forEach var="crew" items="${crews}" >
            <ul class="list-group list-group-flush">
                <li class="list-group-item ">
                    <a class="btn- btn-link" href="crew-details?uid=${crew.crewId}">${crew.fullName}</a>
                    <c:if test="${pageContext.request.isUserInRole('admin')}">
                    <span class="btn-media-right">
                    <a class="btn btn-primary" href="crew-edit?uid=${crew.crewId}">
                        <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                        <a class="btn btn-danger" href="crew-delete?uid=${crew.crewId}">
                            <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i> Delete</a>
                    </span>
                    </c:if>
                </li>
            </ul>
                </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <ul class="ist-group">
                            <li class="list-group-item ">No Crew Data found!</li>
                        </ul>
                    </c:otherwise>
                </c:choose>
<c:if test="${pageContext.request.isUserInRole('admin')}">
    <a href="crew-new" class="btn btn-success ml-auto">
        <i class="fas fa-plus-square fa-3x"></i>Add Crew</a>
</c:if>
        </div>
</div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
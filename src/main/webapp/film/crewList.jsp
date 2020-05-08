<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Crew List" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
    <div class="row">
        <div class="col-md-8">
            <h1 class="text-center">Crew List</h1>
            <c:if test="${pageContext.request.isUserInRole('admin')}">
                <p class="ml-auto"><a href="crew-new" class="btn btn-success">
                    <i class="fas fa-plus-square fa-1x"></i>Add Crew</a></p>
            </c:if>
            <c:if test="${not empty crewAddSuccessMessage}">
                <h2 id="flash" class="alert alert-success">${crewAddSuccessMessage}</h2>
                <c:remove var="crewAddSuccessMessage"/>
            </c:if>
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
        </div>
</div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
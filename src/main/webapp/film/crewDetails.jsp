<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid" role="main">
    <h1>Crew Details</h1>
    <div class="row">
        <c:choose>
            <c:when test="${crew ne null}">
                <div class="col-sm-6 offset-sm-3">
                    <div class="card">

                        <div class="card-body">
                            <h2 class="card-title">${crew.firstName} ${crew.lastName}</h2>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${crew.email}</li>
                                <li class="list-group-item">${crew.profession}</li>
                                <li class="list-group-item">${crew.biography}</li>
                            </ul>
                        </div>
                        <div class="card-body">
                        <c:if test="${pageContext.request.isUserInRole('admin')}">
                            <span class="btn-group ml-auto" role="group" aria-label="Edit and Delete Crew">
                                <a href="crew-edit?uid=<c:out value="${crew.crewId}"/>" class="btn btn-primary">
                                    <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit Crew</a>
                                <a href="crew-delete?uid=<c:out value="${crew.crewId}"/>" class="btn btn-danger" >
                                    <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete Crew</a>
                            </span>
                        </c:if>
                            <a href="crews" class="btn btn-outline-link"><i class="fas fa-list" ></i> Crew List</a>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>Crew was not found!</p>
            </c:otherwise>
        </c:choose>

    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/7/20
  Time: 7:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container">
    <div class="card-deck">
    <div
        <c:choose>
            <c:when test="${user ne null}">
                <div class="card-img">
                    <img class="card-img-top img-responsive" src="images/user.png" alt="Card image cap">
                </div>
                <div class="card-body">
                    <h1 class="card-title"> Name: ${user.firstName} ${user.lastName}</h1>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Role(s):
                            <c:forEach var="role" items="${user.roles}" >
                                ${role.name}
                            </c:forEach>
                        </li>
                        <li class="list-group-item">Email: ${user.email}</li>

                        <li class="list-group-item">Bio: ${user.biography}</li>

                        <li class="btn-group"><a class="btn btn-outline-primary" href="profile-edit?id=<c:out value="${user.userId}"/>">Edit</a><a class="btn btn-outline-danger"  href="deleteUser?id=<c:out value="${user.userId}"/>">Delete</a></li>
                    </ul>
                    <p>Bio</p>
                </div>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>

    </div>
    </div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

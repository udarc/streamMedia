<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/7/20
  Time: 7:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container wrapper">
    <div class="card-deck">
    <div
        <c:choose>
            <c:when test="${user ne null}">
                <div class="img-responsive">
                    <img class="card-img-top " src="images/user.png" alt="Card image cap">
                </div>
                <div class="card-body">
                    <h1 class="card-title"> Name: ${user.fullName}</h1>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Role(s):
                            <c:forEach var="role" items="${user.roles}" >
                                ${role.name}
                            </c:forEach>
                        </li>
                        <li class="list-group-item">Email: ${user.email}</li>
                        <li class="list-group-item">Age: ${user.age}</li>
                        <li class="list-group-item">Bio: ${user.biography}</li>

                        <li class="btn-group"><a class="btn btn-outline-primary" href="profile-edit?user=<c:out value="${user.username}"/>"><i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                            <a class="btn btn-outline-danger"  href="deleteUser?user=<c:out value="${user.username}"/>"><i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a></li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>

    </div>
    </div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

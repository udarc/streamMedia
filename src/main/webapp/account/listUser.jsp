<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/10/20
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="User List" />
<%@include file="../head.jsp"%>
<%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">--%>
<%--<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>--%>

<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container-fluid wrapper">
    <div class="row">
        <div class="col-md-8">
            <h1 class="text-center">Users</h1>
            <c:if test="${(not empty loginSuccess) || not empty userEditSuccess}">
            <h2 id="flash" class="alert alert-success">${loginSuccess} ${userEditSuccess}</h2>
            <c:remove var="loginSuccess"/>
                <c:remove var="userEditSuccess"/>
        </c:if>
            <table class="table table-striped display" id="table">
                <thead class="thead-dark">
                <tr>
                    <th>User Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Roles</th>
                    <th>Edit</th>
                    <th>Delete</th>

                </tr>
                </thead>
                <c:forEach var ="user" items= "${users}">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.username}</td>
                        <td> ${user.email}</td>
                        <td>
                           <c:forEach var="role" items="${user.roles}" >
                               ${role.name}
                           </c:forEach>

                    <td><a class="btn btn-outline-primary" href="profile-edit?user=<c:out value="${user.username}"/>"><i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a></td>
                    <td><a class="btn btn-outline-danger"  href="deleteUser?user=<c:out value="${user.username}"/>"><i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-md-3">
            <a href="register">Add New User</a>
         </div>
    </div>
</div>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
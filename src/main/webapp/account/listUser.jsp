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
<link rel="stylesheet" href="../css/account.css">
<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#table').DataTable();
    } );

</script>
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container-fluid wrapper">
    <div class="row">
        <div class="col-sm-8">
            <h1 class="text-center">Users</h1>
            <c:if test="${not empty loginSuccess}">
                <h2 id="flash" class="alert alert-success">${loginSuccess}</h2>
                <c:remove var="loginSuccess"/>
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
        <div class="col-sm-3">
            <a href="register">Add New User</a>
         </div>
    </div>
</div>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
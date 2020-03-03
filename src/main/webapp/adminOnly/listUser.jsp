<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/10/20
  Time: 1:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="../css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container-fluid wrapper">
    <div class="row">
        <div class="col-sm-8">
            <h1>All Users</h1>

            <table class="table table-striped">
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

                    <td><a class="btn btn-outline-primary" href="profile-edit?id=<c:out value="${user.userId}"/>">Edit</a></td>
                    <td><a class="btn btn-outline-danger"  href="deleteUser?id=<c:out value="${user.userId}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-sm-3">
            <a href="register">Add New User</a>
            <main id="main">
                <form action="#" method="get">
                    <div class="form-group row">
                        <label for="searchTerm" class="col-sm-4 col-form-label">
                            Search Term</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"
                                   id="searchTerm"  name="searchTerm"
                                   placeholder="Search By Last Name"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
            </main>

        </div>
    </div>
</div>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
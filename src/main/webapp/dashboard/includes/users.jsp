<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/8/20
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<table class="table table-striped display" id="table">
    <thead class="thead-dark">
    <tr>
        <th>Full Name</th>
        <th>email</th>
        <th>Roles</th>
        <th>Edit</th>
        <th>Delete</th>

    </tr>
    </thead>
    <c:forEach var ="user" items= "${users}">
        <tr>
            <td>${user.fullName}</td>
            <td> ${user.email}</td>
            <td>
                <c:forEach var="role" items="${user.roles}" >
                    ${role.name}
                </c:forEach>
            </td>

            <td><a class="btn btn-outline-primary" href="profile-edit?user=<c:out value="${user.username}"/>"><i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a></td>
            <td><a class="btn btn-outline-danger"  href="deleteUser?user=<c:out value="${user.username}"/>"><i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a></td>
        </tr>
    </c:forEach>
</table>
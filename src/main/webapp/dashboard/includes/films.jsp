<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/8/20
  Time: 11:29 PM
  To change this template use File | Settings | File Templates.
--%>
<table class="table table-striped display" >
    <thead class="thead-dark">
    <tr>
        <th>Title</th>
        <th>Director</th>
        <th>Genre Count</th>
        <th>Crew Count</th>
        <th>Edit</th>
        <th>Delete</th>

    </tr>
    </thead>
    <c:forEach var ="film" items= "${films}">
        <tr>
            <td>${film.title}</td>
            <td> ${film.director}</td>
            <td>
        ${fn:length(film.genres)}
            </td>
            <td>
                    ${fn:length(film.crews)}
            </td>
            <td><a class="btn btn-outline-primary" href="film-edit?uid=${film.filmId}">
                <i class="fas fa-edit fa-1x" aria-hidden="true"></i>Edit</a></td>
            <td><a class="btn btn-outline-danger"  href="film-delete?uid=${film.filmId}">
                <i class="fas fa-trash-alt fa-1x" aria-hidden="true"></i>Delete</a></td>
        </tr>
    </c:forEach>
</table>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/23/20
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="row">
    <div class="col-sm-2">
        <ul class="list-group list-group-flush">
            <c:forEach var="genre" items="${restGenres}">
                <li class="list-group-item">${genre.name}</li>
            </c:forEach>
        </ul>
    </div>
</div>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/27/20
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<div class="row">
    <c:choose>
        <c:when test="${movies ne null}">
            <c:forEach var="movie" items="${movies}" >
                <div class="col-md-3">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item "><h2>Title: ${movie.title}</h2></li>
                        <li class="list-group-item ">Original Title:
                                ${movie.originalTitle}</li>
                        <li class="list-group-item ">Popularity:
                                ${movie.popularity}</li>
                        <li class="list-group-item ">Average Vote:
                                ${movie.voteAverage}</li>
                        <li class="list-group-item">${movie.overview}</li>
                        <li class="list-group-item">Release Date: ${movie.releaseDate}</li>
                    </ul>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <ul list-group >
                <li class="list-group-item ">${notFoundMessage}</li>
            </ul>
        </c:otherwise>
    </c:choose>
</div>

<%--
  Created by IntelliJ IDEA.
  Trailer: student
  Date: 5/8/20
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<table class="table table-striped display" id="table">
    <thead class="thead-dark">
    <tr>
        <th>Title</th>
        <th>author</th>
        <th>Publication Date</th>
        <th>Edit</th>
        <th>Delete</th>

    </tr>
    </thead>
    <c:forEach var ="trailer" items= "${trailers}">
        <tr>
            <td>${trailer.title}</td>
            <td> ${trailer.author}</td>
            <td>
                    ${trailer.publicationDate}
            </td>
            <td><a class="btn btn-outline-primary" href="trailer-edit?uid=${trailer.trailerId}"><i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a></td>
            <td><a class="btn btn-outline-danger"  href="trailer-delete?uid=${trailer.trailerId}"><i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a></td>
        </tr>
    </c:forEach>
</table>
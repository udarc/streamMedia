<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/8/20
  Time: 11:28 PM
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
    <c:forEach var ="book" items= "${books}">
        <tr>
            <td>${book.title}</td>
            <td> ${book.author}</td>
            <td>
                    ${book.publicationDate}
            </td>
            <td><a class="btn btn-outline-primary" href="book-edit?uid=${book.bookId}"><i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a></td>
            <td><a class="btn btn-outline-danger"  href="book-delete?uid=${book.bookId}"><i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a></td>
        </tr>
    </c:forEach>
</table>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/8/20
  Time: 11:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/8/20
  Time: 11:28 PM
  To change this template use File | Settings | File Templates.
--%>
<table class="table table-striped display" >
    <thead class="thead-dark">
    <tr>
        <th>Title</th>
        <th>author</th>
        <th>Edit</th>
        <th>Delete</th>

    </tr>
    </thead>
    <c:forEach var ="story" items= "${stories}">
        <tr>
            <td>${story.title}</td>
            <td> ${story.author}</td>
            <td><a class="btn btn-outline-primary" href="story-edit?uid=${story.shortStoryId}">
                <i class="fas fa-edit fa-1x" aria-hidden="true"></i>Edit</a></td>
            <td><a class="btn btn-outline-danger"  href="story-delete?uid=${story.shortStoryId}">
                <i class="fas fa-trash-alt fa-1x" aria-hidden="true"></i>Delete</a></td>
        </tr>
    </c:forEach>
</table>

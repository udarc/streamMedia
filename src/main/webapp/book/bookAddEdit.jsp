<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/16/20
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:choose>
    <c:when test="${book ne null}">
        <c:set var="title" value="Edit Book" />
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Add Book" />
    </c:otherwise>
</c:choose>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css"/>
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="form row">
        <c:choose>
            <c:when test="${book ne null}">
            <h1 class="text-center">Edit Book</h1>
            <form class="card col-sm-10 offset-sm-1" action="book-edit" method="post">
                <input type="hidden" name="uid" value="${book.bookId}">
            </c:when>
            <c:otherwise>
            <h1 class="text-center">Add Book</h1>
            <form class="card col-sm-10 offset-sm-1" action="book-new" method="post">
            </c:otherwise>
        </c:choose>

            <div class="form-group row">
                <label for="title" class="col-sm-3 col-form-label">Title</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="title"
                           id="title" placeholder="Book Title" value="${book.title}">
                </div>
            </div>
            <div class="form-group row">
                <label for="category" class="col-sm-3 col-form-label">Category(s)</label>
                <div class="col-sm-7">
                <select name="category" class="form-control" id="category" multiple>
                    <option value="select a Category">Select</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.bkCategoryId}">${category.title}</option>

                    </c:forEach>
                </select>
                </div>
                <div class="col-sm-2"><a class="btn btn-secondary" href="bkcategory-new">Add Category</a></div>
            </div>
            <div class="form-group row">
                <label for="isbn" class="col-sm-3 col-form-label">ISBN</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="isbn" id="isbn" placeholder="Isbn" value="${book.ISBN}">
                </div>
            </div>
            <div class="form-group row">
                <label for="author" class="col-sm-3 col-form-label">Author</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="author" id="author" placeholder="Author"
                    value="${book.author}">
                </div>
            </div>
                <div class="form-group row">
                    <label for="edition" class="col-sm-3 col-form-label">Edition</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control"
                               name="edition" id="edition"  placeholder="Edition"
                        value="${book.edition}">
                    </div>
                </div>
            <div class="form-group row">
                <label for="cover" class="col-sm-3 col-form-label">Cover</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="cover" id="cover" placeholder="Cover" value="${book.cover}">
                </div>
            </div>
            <div class="form-group row">
                <label for="pub_date" class="col-sm-3 col-form-label">Publication Date</label>
                <div class="col-sm-9">
                    <input type="datetime-local" class="form-control"
                           name="pub_date" id="pub_date" placeholder="Publication Date"
                    value="${book.publicationDate}">
                </div>
            </div>
            <div class="form-group row">
                <label for="publisher" class="col-sm-3 col-form-label">Publisher</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="publisher" id="publisher" placeholder="Publisher" value="${book.publisher}">
                </div>
            </div>
                <div class="form-group row">
                    <label for="page-number" class="col-sm-3 col-form-label">Page Number</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control"
                               name="page_number" id="page-number" placeholder="Page Number" value="${book.pageNumber}">
                    </div>
                </div>
            <div class="form-group row">
                <label for="summary" class="col-sm-3 col-form-label" >Summary</label>
                <div class="col-sm-9">
                    <textarea class="form-control"name="summary" id="Summary" rows="6">${book.summary}</textarea>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-9">
                    <button type="submit"
                            name="sendMessage" class="btn btn-lg btn-success">Save Book</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>






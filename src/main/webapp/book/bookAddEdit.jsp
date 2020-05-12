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
        <c:if test="${not empty bookErrorMessage}">
            <h2 id="flash" class="alert alert-danger">${bookErrorMessage}</h2>
            <c:remove var="bookErrorMessage"/>
        </c:if>
        <c:if test="${not empty unsupportedExtension}">
            <h2 id="flash" class="alert alert-danger">${unsupportedExtension}</h2>
            <c:remove var="unsupportedExtension"/>
        </c:if>

        <c:choose>
            <c:when test="${book ne null}">
            <h1 class="text-center">Edit Book</h1>
            <form class="card col-md-10 offset-md-1 needs-validation" novalidate action="book-edit" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="uid" value="${book.bookId}">
            </c:when>
            <c:otherwise>
            <h1 class="text-center">Add Book</h1>
            <form class="card col-md-10 offset-md-1 needs-validation" novalidate action="book-new" method="post"
                  enctype="multipart/form-data">
            </c:otherwise>
        </c:choose>
            
            <div class="form-group row">
                <label for="title" class="col-md-3 col-form-label">Title</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="title"
                           id="title" placeholder="Book Title" value="${book.title}" required>
                    <div class="invalid-feedback">Book Title is required!</div>
                </div>
            </div>
            <div class="form-group row">
                <label for="category" class="col-md-3 col-form-label">Categories</label>
                <div class="col-md-7">
                <select name="category" class="form-control" id="category" multiple required>
                    <option value="select a Category">Select</option>
                    <c:forEach var="category" items="${categories}">

<%--                        TO DO CREATE TAB LIB  https://stackoverflow.com/questions/1490139/evaluate-list-contains-string-in-jstl--%>
<%--                        <c:if test = "${fn:contains(${book.categories}, ${category})}">--%>
<%--                            <option value="${category.bkCategoryId}" selected>${category.title}</option>--%>
<%--                        </c:if>--%>
                        <option value="${category.bkCategoryId}">${category.title}</option>

                    </c:forEach>
                    <div class="invalid-feedback">Book category is required!</div>
<%--                    <option value="">${book.categories}</option>--%>
                </select>
                </div>
                <div class="col-md-2"><a class="btn btn-secondary" href="category-new">Add Category</a></div>
            </div>
            <div class="form-group row">
                <label for="isbn" class="col-md-3 col-form-label">ISBN</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="isbn" id="isbn" placeholder="Isbn" value="${book.ISBN}" required>
                    <div class="invalid-feedback">Book's ISBN is required!</div>
                </div>
            </div>
            <div class="form-group row">
                <label for="author" class="col-md-3 col-form-label">Author</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="author" id="author" placeholder="Author"
                    value="${book.author}" required>
                    <div class="invalid-feedback">Author is required!</div>
                </div>
            </div>
                <div class="form-group row">
                    <label for="edition" class="col-md-3 col-form-label">Edition</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control"
                               name="edition" id="edition"  placeholder="Edition"
                        value="${book.edition}">
                    </div>
                </div>
            <div class="form-group row">
                <label for="cover" class="col-md-3 col-form-label">Cover</label>
                <div class="col-md-9">
                    <input type="file" class="form-control"
                           name="cover" id="cover" placeholder="Cover" value="${book.cover}">
                </div>
            </div>
            <div class="form-group row">
                <label for="pub_date" class="col-md-3 col-form-label">Publication Date</label>
                <div class="col-md-9">
                    <input type="datetime-local" class="form-control"
                           name="pub_date" id="pub_date" placeholder="Publication Date"
                    value="${book.publicationDate}">
                </div>
            </div>
            <div class="form-group row">
                <label for="publisher" class="col-md-3 col-form-label">Publisher</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="publisher" id="publisher" placeholder="Publisher"
                           value="${book.publisher}" required>
                    <div class="invalid-feedback">Publisher is required!</div>
                </div>
            </div>
                <div class="form-group row">
                    <label for="page-number" class="col-md-3 col-form-label">Page Number</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control"
                               name="page_number" id="page-number"
                               placeholder="Page Number" value="${book.pageNumber}" required>
                        <div class="invalid-feedback">Book page number is required!</div>
                    </div>
                </div>
            <div class="form-group row">
                <label for="summary" class="col-md-3 col-form-label" >Summary</label>
                <div class="col-md-9">
                    <textarea class="form-control"name="summary" id="Summary" rows="6" required>${book.summary}</textarea>
                    <div class="invalid-feedback">Book summary must not be empty!</div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-9">
                    <button type="submit"
                            name="sendMessage" class="btn btn-lg btn-success">Save Book</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>






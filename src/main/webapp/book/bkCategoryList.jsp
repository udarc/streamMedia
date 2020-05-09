<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 4/4/20
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Book Category List" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
    <div class="row">
        <div class="col-md-8">
            <h1 class="text-center">BkCategory List</h1>
            <c:if test="${not empty addBookCategorySuccess}">
                <h2 id="flash" class="alert alert-success">${addBookCategorySuccess}</h2>
                <c:remove var="addBookCategorySuccess"/>
            </c:if>
            <ul class="list-group list-group-info list-group-flush">
                <c:choose>
                    <c:when test="${fn:length(categories) gt 0}">

                        <c:forEach var="bkCategory" items="${categories}">

                            <li class="list-group-item "><a href="bkcategory-details?uid=${bkCategory.bkCategoryId}">${bkCategory.title}</a>
                                <c:if test="${pageContext.request.isUserInRole('admin')}">
                                <span class="btn-media-right">
                                    <a class="btn btn-primary" href="bkcategory-edit?uid=${bkCategory.bkCategoryId}">
                                        <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                                    <a class="btn btn-danger" href="bkcategory-delete?uid=${bkCategory.bkCategoryId}">
                                        <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i> Delete</a>
                                </span>
                                </c:if>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li class="list-group-item ">No BkCategory found!</li>
                    </c:otherwise>
                </c:choose>
                <c:if test="${pageContext.request.isUserInRole('admin')}">
                    <p class="ml-auto"><a href="category-new" class="btn btn-success">
                        <i class="fas fa-plus-square fa-1x"></i>Add BkCategory</a></p>
                </c:if>
            </ul>
        </div>

    </div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

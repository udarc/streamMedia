<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/4/20
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
    <div class="row">
        <div class="col-sm-8">

            <ul class="list-group list-group-info list-group-flush">
                <c:choose>
                    <c:when test="${fn:length(categories) gt 0}">
                        <li class="list-group-item list-group-item-primary"><h1>BkCategory List</h1></li>

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
                    <a href="bkcategory-new" class="btn btn-success ml-auto">
                        <i class="fas fa-plus-square fa-3x"></i>Add BkCategory</a>
                </c:if>
            </ul>
        </div>

    </div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

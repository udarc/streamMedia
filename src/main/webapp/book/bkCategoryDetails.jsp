<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/4/20
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Book category Details" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper" role="main">
    <h1 class="text-center"> <span onclick="goBack()">
        <i class="fas fa-arrow-left fa-1x" aria-hidden="true"></i></span> Category Details</h1>
    <div class="row">
        <c:choose>
            <c:when test="${bkCategory ne null}">
                <div class="col-sm-6 offset-sm-3">
                    <div class="card">

                        <div class="card-body">
                            <h2 class="card-title">${bkCategory.title}</h2>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${bkCategory.description}</li>
                            </ul>
                        </div>
                        <c:if test="${pageContext.request.isUserInRole('admin')}">
                            <span class="btn-group ml-auto " role="group" aria-label="Edit and Delete BkCategory">
                        <a href="bkcategory-edit?uid=<c:out value="${bkCategory.bkCategoryId}"/>" class="btn btn-primary" >
                            <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit</a>
                    <a href="bkcategory-delete?uid=<c:out value="${bkCategory.bkCategoryId}"/>" class="btn btn-danger" >
                        <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete</a>
                        </span>
                        </c:if>
                        <a href="bk-categories" class="btn btn-link" ><i class="fas fa-list"></i> BkCategory List</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>BkCategory was not found!</p>
            </c:otherwise>
        </c:choose>

    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
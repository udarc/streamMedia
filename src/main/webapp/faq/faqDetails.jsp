<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/28/20
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid" role="main">
    <h1 class="text-center">FAQ</h1> <a href="faq-new" class="btn btn-success ml-auto" >Add FAQ</a>
    <div class="row">
        <c:choose>
            <c:when test="${faq ne null}">
                <div class="col-sm-6 offset-sm-3">
                    <div class="card">

                        <div class="card-body">
                            <h2 class="card-title">${faq.title}</h2>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${faq.category}</li>
                                <li class="list-group-item">${faq.description}</li>
                            </ul>
                        </div>
                        <div class="card-body">
                            <span class="btn-group ml-auto " role="group" aria-label="Edit and Delete FAQ">
                        <a href="faq-edit?uid=<c:out value="${faq.faqId}"/>" class="btn btn-primary" >
                            <i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit FAQ</a>
                    <a href="faq-delete?uid=<c:out value="${faq.faqId}"/>" class="btn btn-danger" >
                        <i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete FAQ</a>
                        </span>
                        </div>
                        <a href="faqs" class="btn btn-link" >FAQ List</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <p>FAQ was not found!</p>
            </c:otherwise>
        </c:choose>

    </div>

</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
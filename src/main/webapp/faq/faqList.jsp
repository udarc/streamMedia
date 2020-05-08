<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/28/20
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp" %>
<c:set var="title" value="FAQ List"/>
<%@include file="../head.jsp" %>
<link rel="stylesheet" href="css/accordion.css">
</head>
<body>
<%@include file="../navbar.jsp" %>
<main class="container-fluid wrapper">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h1 class="text-center">Frequently Asked Questions</h1>
            <c:if test="${pageContext.request.isUserInRole('admin')}">

                <p class="ml-auto"><a href="faq-new" class="btn btn-success ml-auto">
                    <i class="fas fa-plus-square fa-1x"></i>Add FAQ</a></p>
            </c:if>
            <c:if test="${not empty faqAddSuccessMessage}">
                <h2 id="flash" class="alert alert-success">${faqAddSuccessMessage}</h2>
                <c:remove var="faqAddSuccessMessage"/>
            </c:if>
            <c:choose>
                <c:when test="${pageContext.request.isUserInRole('admin')}">


                    <%@include file="includes/adminView.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="includes/nonAdminView.jsp" %>
                </c:otherwise>
            </c:choose>


        </div>


    </div>
</main>

<%@include file="../footer.jsp" %>
<%@include file="../afterFooter.jsp" %>
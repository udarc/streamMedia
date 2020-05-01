<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/26/20
  Time: 11:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Popular Movies" />
<c:set var="notFoundMessage" value="Popular Movies Not found!"/>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
    <h1 class="alert alert-info text-center">
        <span onclick="goBack()"><i class="fas fa-arrow-left fa-1x" aria-hidden="true"></i></span>Popular Movies</h1>
    <%@include file="../includes/apiMoviesDB.jsp"%>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
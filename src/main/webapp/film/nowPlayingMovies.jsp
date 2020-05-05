<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/24/20
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Now Playing Movies" />
<c:set var="notFoundMessage" value="NoW Playing Movies Not found!"/>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
        <h1 class="alert alert-info text-center"> <span onclick="goBack()"><i class="fas fa-arrow-left fa-1x" aria-hidden="true"></i></span> Now Playing Movies</h1>
<%@include file="../includes/apiMoviesDB.jsp"%>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

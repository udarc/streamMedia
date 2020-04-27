<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/26/20
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/26/20
  Time: 11:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Top Rates Movies" />
<c:set var="notFoundMessage" value="Top Rated Movies Not found!"/>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper">
    <h1 class="alert alert-info text-center">Top Rated Movies</h1>
    <%@include file="../includes/apiMoviesDB.jsp"%>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

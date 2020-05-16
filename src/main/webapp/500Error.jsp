<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/2/20
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="taglib.jsp"%>
<c:set var="title" value="500 Error" />
<%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<main class="container wrapper" >

    <h1 class="alert alert-danger">Something is not right!</h1>
    <h2 class="alert alert-primary"><a class="btn btn-link"  href="/streamMedia">Back Home</a></h2>
</main>
<%@include file="footer.jsp"%>
<%@include file="afterFooter.jsp"%>



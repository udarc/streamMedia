<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="SignIn Error" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container wrapper">
    <div class=" row">
        <div class="alert alert-danger" role="alert">
            <h1 class="alert-heading">Login failed...Please try again!</h1>
            <p>Already have an account? <a class="btn btn-primary" href="login">SignIn</a></p>
            <hr>
            <p class="mb-0">No account yet ? <a class="btn btn-primary" href="register">SignUp</a></p>
        </div>
    </div>

</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
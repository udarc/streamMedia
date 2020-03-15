<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 3/1/20
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container wrapper">
    <div class="alert alert-danger row">
<h1 class="">Login failed...Please try again</h1>
        <p>Already have an account? <a class="btn btn-primary" href="login">SignIn</a></p>
        <p>No account yet ? <a class="btn btn-primary" href="register">SignUp</a></p>
    </div>

</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/2/20
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<main class="container wrapper" >

   <p class="alert alert-warning">Trying to access restricted content</p>
   <p class="alert alter-primary">Login to access content for logged in users
      <a href="login">SignIn</a></p>
</main>
<%@include file="footer.jsp"%>
<%@include file="afterFooter.jsp"%>


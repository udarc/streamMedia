<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/1/20
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Upload S3" />
<%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container wrapper" >

    <form action="aws-s3" method="post" enctype="multipart/form-data">
        <input class="form-control"  name="file" type="file">
        <input class="btn btn-success" type="submit" value="Upload">
    </form>

</div>
<%@include file="footer.jsp"%>
<%@include file="afterFooter.jsp"%>


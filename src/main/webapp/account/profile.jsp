<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/7/20
  Time: 7:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container">
    <div class="card-deck">
    <div class="card">
        <div class="card-img">
            <img class="card-img-top" src="../images/user.png" alt="Card image cap">
        </div>

        <div class="card-body">
            <h5 class="card-title">Name</h5>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Cras justo odio</li>
            <li class="list-group-item">Dapibus ac facilisis in</li>
            <li class="list-group-item">Vestibulum at eros</li>
        </ul>
        <div class="card-body">
            <p>Bio</p>
        </div>
    </div>
    </div>
</main>

<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

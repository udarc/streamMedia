<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/18/20
  Time: 4:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="../css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper" role="main">
    <h1 class="text-center">List of Trailers</h1> <a href="filmList.jsp" class="btn btn-success ml-auto" >Add Trailer</a>
    <div class="row">
        <div class="col-sm-6 offset-sm-3">
            <div class="card">
                <svg class="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" role="img"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"/><text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text></svg>

                <div class="card-body">
                    <h5 class="card-title">Film title</h5>
                    <p class="card-text">Short Summary about Film.</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Cras justo odio</li>
                    <li class="list-group-item">Dapibus ac facilisis in</li>
                    <li class="list-group-item">Vestibulum at eros</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link btn btn-primary">Trailer Details</a>

                </div>
            </div>
        </div>

    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

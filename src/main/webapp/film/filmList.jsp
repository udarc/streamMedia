<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/18/20
  Time: 4:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<main class="container-fluid wrapper" role="main">
    <h1 class="text-center">List of Films</h1> <a href="filmList.jsp" class="btn btn-success ml-auto" >Add Film</a>
    <div class="row">
        <div class="col-sm-2">
            <h2>Genres</h2>
            <%@include file="restAPIGenres.jsp"%>
        </div>
        <div class="col-sm-7">
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
                    <a href="#" class="card-link btn btn-primary">Film Details</a>

                </div>
            </div>
        </div>
        <div class="col-sm-2">
            <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a class="btn btn-link" href="now-playing-movies">Now Playing Movies</a></li>
                <li class="list-group-item"><a class="btn btn-link" href="#">Top Rated</a></li>
                <li class="list-group-item"><a class="btn btn-link" href="#">Popular</a></li>
                <li class="list-group-item"><a class="btn btn-link" href="#">Up Coming</a></li>
            </ul>
        </div>


    </div>
</main>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

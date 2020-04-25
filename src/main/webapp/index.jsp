<%@include file="taglib.jsp"%>
<c:set var="title" value="Home" />
<%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container wrapper" >
    <main class="homepage">
            <h1 class="text-center">Welcome to Stream Media</h1>
            <div id="carouselIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselIndicators" data-slide-to="2"></li>
                    <li data-target="#carouselIndicators" data-slide-to="3"></li>
                    <li data-target="#carouselIndicators" data-slide-to="4"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="rounded d-block w-100" src="images/homepage/movie.png" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="rounded d-block w-100" src="images/homepage/cinemabg.png" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class=" rounded d-block w-100" src="images/homepage/photo2.png" alt="Third slide">
                    </div>
                    <div class="carousel-item">
                        <img class=" rounded d-block w-100" src="images/homepage/Hnetbg.png" alt="Fourth slide">
                    </div>
                    <div class="carousel-item">
                        <img class="rounded d-block w-100" src="images/homepage/brisbane.jpg" alt="Fifth slide">
                    </div>

                </div>
                <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

        <section class="container">
                <!-- Example row of columns -->
                <div class="row" style="margin: 3em 3em auto 3em;">
                    <div class="col-md-3">
                        <a class="btn btn-media" href="trailers" role="button">Trailers &raquo;</a>
                    </div>
                    <div class="col-md-3">
                        <a class="btn btn-media" href="films" role="button">Films &raquo;</a>
                    </div>
                    <div class="col-md-3">
                        <a class="btn btn-media" href="books" role="button">Books &raquo;</a>
                    </div>
                    <div class="col-md-3">
                        <a class="btn btn-media" href="short-stories" role="button">Short Stories &raquo;</a>
                    </div>
                </div>
        </section>
    </main>

</div>
<%@include file="footer.jsp"%>
<%@include file="afterFooter.jsp"%>


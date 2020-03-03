<%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container wrapper" >
    <main class="homepage">
        <section id="home" class="jumbotron">
            <h1>Welcome to Stream Media</h1>
        </section>

        <section class="container">
                <!-- Example row of columns -->
                <div class="row" style="margin: 3em 3em auto 3em;">
                    <div class="col-md-3">
                        <a class="btn btn-media" href="trailers" role="button">Trailers &raquo;</a>
                    </div>
                    <div class="col-md-3">
                        <a class="btn btn-media" href="./film/filmList.jsp" role="button">Films &raquo;</a>
                    </div>
                    <div class="col-md-3">
                        <a class="btn btn-media" href="./book/bookList.jsp" role="button">Books &raquo;</a>
                    </div>
                    <div class="col-md-3">
                        <a class="btn btn-media" href="./stories/storyList.jsp" role="button">Short Stories &raquo;</a>
                    </div>
                </div>
        </section>
    </main>

</div>
<%@include file="footer.jsp"%>
<%@include file="afterFooter.jsp"%>


<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/16/20
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../head.jsp"%>
<link rel="stylesheet" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container">
    <div class="form row">
        <h1>Add Film</h1>
        <form class="card col-sm-10 offset-sm-1" action="addFilm" method="post">
            <div class="form-group row">
                <label for="title" class="col-sm-3 col-form-label">Title</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="title"
                           id="title" placeholder="Film Title">
                </div>
            </div>
            <div class="form-group row">
                <label for="episode" class="col-sm-3 col-form-label">Episode</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="episode" id="episode" placeholder="Episode">
                </div>
            </div>
            <div class="form-group row">
                <label for="director" class="col-sm-3 col-form-label">Director</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="director" id="director" placeholder="Director">
                </div>
            </div>
            <div class="form-group row">
                <label for="duration" class="col-sm-3 col-form-label">Duration</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="duration" id="duration" placeholder="Duration">
                </div>
            </div>
            <div class="form-group row">
                <label for="cover" class="col-sm-3 col-form-label">Cover</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="cover" id="cover" placeholder="Cover">
                </div>
            </div>
            <div class="form-group row">
                <label for="pub_date" class="col-sm-3 col-form-label">Publication Date</label>
                <div class="col-sm-9">
                    <input type="date" class="form-control"
                           name="pub_date" id="pub_date" placeholder="Publication Date">
                </div>
            </div>
            <div class="form-group row">
                <label for="video" class="col-sm-3 col-form-label">Video</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="video" id="video" placeholder="Video">
                </div>
            </div>
            <div class="form-group row">
                <label for="summary" class="col-sm-3 col-form-label" >Summary</label>
                <div class="col-sm-9">
                    <textarea class="form-control"name="summary" id="Summary" rows="6"></textarea>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-9">
                    <button type="submit"
                            name="sendMessage" class="btn btn-lg btn-success">Send Message</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>






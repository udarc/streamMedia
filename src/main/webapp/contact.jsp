<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/8/20
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="head.jsp"%>
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">
    <div class="form row">
        <h1>Contact Us</h1>
        <form class="card col-sm-10 offset-sm-1" action="contact-us" method="post">
            <div class="form-group row">
                <label for="inputFirstName" class="col-sm-3 col-form-label">First Name</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="firstName"p
                           id="inputFirstName" placeholder="First Name">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputLastName" class="col-sm-3 col-form-label">Last Name</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="lastName" id="inputLastName" placeholder="Last Name">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputEmail" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-9">
                    <input type="email" class="form-control"
                           name="email" id="inputEmail" placeholder="Email">
                </div>
            </div>
            <div class="form-group row">
                <label for="subject" class="col-sm-3 col-form-label">Subject</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"
                           name="subject" id="subject" placeholder="Subject">
                </div>
            </div>

            <div class="form-group row">
                <label for="message" class="col-sm-3 col-form-label" >Message</label>
                <div class="col-sm-9">
                    <textarea class="form-control"name="message" id="message" rows="6"></textarea>
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
<%@include file="footer.jsp"%>
<%@include file="afterFooter.jsp"%>
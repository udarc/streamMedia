<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    html, body{
        height: 100%;
    }
    body {
        font-family: 'DejaVu Sans Mono', monospace;
        min-height: 100vh;
    }
    .bg-dark {
        background-color: darkred !important;

    }
    .bg-light {
        background-color: #450000  !important;

    }
    .navbar-dark .navbar-nav .nav-link, .navbar-light .navbar-nav .nav-link,
    .nav-link{
        color: #f5f5f5;
    }
    .nav-link {
        padding: .5rem 1rem;
        margin: .1em .8em;
    }
    navbar-light .navbar-nav .nav-link:focus, .navbar-light .navbar-nav .nav-link:hover,
    navbar-dark .navbar-brand:focus, .navbar-dark .navbar-brand:hover,
    navbar-dark .navbar-nav .nav-link:focus, .navbar-dark .navbar-nav .nav-link:hover
    .nav-link:focus, .nav-link:hover  {
        color: #f6b66c
    }
    .nav .navbar-nav .nav-link {
        margin: .2em .6em;
    }

    .wrapper{
        min-height: 60vh;
    }
    #home {
        overflow: auto;
        background-size: cover;
        /*background: url("./images/homepagebg.jpg") no-repeat;*/
        height: auto;
        border-radius: .5em;
        margin: 2em auto;
        padding: 2em;
        /*color: whitesmoke;*/
    }
.btn-media{
    background-color: #980404;
    min-height: 4em;
    padding-top: 1em;
    color: ghostwhite;
    font-weight: 600;
    margin: .5em;
    min-width: 10em;
    }
.btn-media:focus,.btn-media:hover{
    color: #f6b66c;
    background-color: #450000;
}

    /*Footer style*/
    .footer {
        position:relative;
        left: 0;
        bottom: 0;
        width: 100%;
        color: white;
        font-size: medium;
    }
    .footer-upper{
        background-color: #2d0101;

    }
    .footer-upper nav {
        padding: 1em 2em;
    }
    .footer-bottom{
        background-color: #1b0000;
        padding: 1em;
    }

    /*Input text and placeholder color*/

    .form-control,.form-control:focus {
        color: #100000;
    }
    /*
    CSS from account.css
  */
    .form {
        margin: 1.5em;
    }
    .card {
        padding: 3em;
        margin-top: 1em;
    }
    form.card {
        background-color: #1b0000;
        opacity: .80;
        color: aliceblue;
        /*font-size: 1.5em;*/
    }
    .card-img{
        width: 20em;
    }
/*
TODO fix external css
 */
</style>
<header>
    <nav class="navbar-nav navbar-light bg-light justify-content-between" style="background-color: #450000!important;">
            <ul id="utility" class="nav  ml-auto">
                <c:if test="${request.getRemoteUser()}">
                    <p class="alert alert-success">${request.getRemoteUser()}</p>
                </c:if>

                <c:choose>
                    <c:when test="${(pageContext.request.isUserInRole('admin')) ||
                                    (pageContext.request.isUserInRole('user')) }">
                        <c:if test="${(pageContext.request.isUserInRole('admin'))}">
                            <li class="nav-item">
                                <a class="nav-link"  href="users">Users</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link"  href="profile-edit?id=1">Edit Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"  href="user-profile">Profile ${user.username}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"  href="logout">Logout</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link"  href="register">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"  href="login">Login</a>
                        </li>
                    </c:otherwise>
                </c:choose>


            </ul>
    </nav>

    <nav class="navbar navbar-expand-md navbar-dark bg-dark justify-content-between">
        <a class="navbar-brand" href="/streamMedia">Stream Media</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/streamMedia">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="contact-us">Contact</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="streammedia" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">Media</a>
                    <div class="dropdown-menu" aria-labelledby="/streammedia">
                        <a class="dropdown-item" href="/film/filmList.jsp">Films</a>
                        <a class="dropdown-item" href="trailers">Trailers</a>
                        <a class="dropdown-item" href="/stories/storyList.jsp">Short Stories</a>
                        <a class="dropdown-item" href="/book/bookList.jsp">Books</a>
                    </div>
                </li>
            </ul>
        </div>
        <form action="#" method="get" class="form-inline">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Search</button>
        </form>
        <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="faqs">FAQ</a>
        </li>
        </ul>
    </nav>
</header>


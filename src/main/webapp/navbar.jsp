<header>
    <nav class="navbar-nav navbar-light bg-light justify-content-between" style="background-color: #450000;">
            <ul id="utility" class="nav ml-auto">
                <li class="nav-item">
                <li class="nav-item">
                    <a class="nav-link"  href="users">Users</a>
                </li>
                    <a class="nav-link"  href="register">Register</a>
                </li>
                </li>
                <a class="nav-link"  href="profile-edit?id=1">Edit Porfile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href="./account/signin.jsp">Login</a>
                </li>
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
                    <div class="dropdown-menu" aria-labelledby="streammedia">
                        <a class="dropdown-item" href="#">Film</a>
                        <a class="dropdown-item" href="trailers">Trailers</a>
                        <a class="dropdown-item" href="#">Short Stories</a>
                    </div>
                </li>
            </ul>
        </div>
        <form action="searchUser" method="get" class="form-inline">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <li class="nav-item">
            <a class="nav-link" href="/faq/faqList.jsp">FAQ</a>
        </li>
    </nav>
</header>

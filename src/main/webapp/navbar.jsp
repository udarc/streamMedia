
<header>
    <nav class="navbar-nav navbar-light bg-light justify-content-between" style="background-color: #450000!important;">
            <ul id="utility" class="nav  ml-auto">
                <c:if test="${request.getRemoteUser()}">
                    <p class="alert alert-success">${request.getRemoteUser()}</p>
                </c:if>

                <c:choose>
                    <c:when test="${(pageContext.request.isUserInRole('admin')) ||
                                    (pageContext.request.isUserInRole('media creator')) ||
                                    (pageContext.request.isUserInRole('user')) }">
                        <li class="nav-item">
                            <a class="nav-link text-capitalize"  href="user-profile">
                                <span class="mr-2 d-none d-lg-inline"><%= request.getRemoteUser() %></span>
                                <c:if test="${not empty profileImage}">
                                <img class="img-profile rounded-circle" src="${profileImage}" style="height: 3.75em; width: 3.75em;">
                                </c:if>
                            </a>

                        </li>
                        <c:if test="${(pageContext.request.isUserInRole('admin'))}">

                            <li class="nav-item">
                                <a class="nav-link"  href="users">Users</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link"  href="profile-edit?user=<%= request.getRemoteUser()%>">Edit Profile</a>
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
        <a class="navbar-brand" href="/streamMedia"><img src="images/logos.png" class="img-fluid img-rounded" style="max-width: 4em; height: auto;" alt="logo">Stream Media</a><button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
<%--                <li class="nav-item active">--%>
<%--                    <a class="nav-link" href="/streamMedia">Home</a>--%>
<%--                </li>--%>
                <li class="nav-item">
                    <a class="nav-link" href="contact-us">Contact</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="streammedia" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">Media</a>
                    <div class="dropdown-menu" aria-labelledby="streammedia">
                        <a class="dropdown-item" href="books">Books</a>
                        <a class="dropdown-item" href="films">Films</a>
                        <a class="dropdown-item" href="short-stories">Short Stories</a>
                        <a class="dropdown-item" href="trailers">Trailers</a>


                    </div>
                </li>
            </ul>
        </div>
        <ul class="navbar-nav mr-auto">
            <c:if test="${pageContext.request.isUserInRole('admin')}">
                <li class="nav-item">
                    <a class="nav-link" href="dashboard">Admin Dashboard</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="faqs">FAQ</a>
            </li>
        </ul>
<%--        <form action="#" method="get" class="form-inline">--%>
<%--            <input class="form-control mr-md-2" type="search" placeholder="Search" aria-label="Search">--%>
<%--            <button class="btn btn-outline-danger my-2 my-md-0" type="submit">Search</button>--%>
<%--        </form>--%>

    </nav>
</header>


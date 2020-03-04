<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                        <a class="dropdown-item" href="books">Books</a>
                        <a class="dropdown-item" href="films">Films</a>
                        <a class="dropdown-item" href="short-stories">Short Stories</a>
                        <a class="dropdown-item" href="trailers">Trailers</a>


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


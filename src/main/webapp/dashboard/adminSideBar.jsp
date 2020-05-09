<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/8/20
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashboard">
        <div class="sidebar-brand-icon">
            <i class="fas fa-users-cog"></i>Dashboard
        </div>
    </a>
        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">
        <li class="nav-item">
            <a class="nav-link" href="<%= request.getContextPath()%>"><i class="fas fa-user-alt"></i>User View</a>
        </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">
    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Interface
    </div>
    <!-- Nav Item - Users -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#users" data-toggle="collapse" data-target="#collapseUsers" aria-expanded="false" aria-controls="collapseUsers">
            <i class="fas fa-users"></i>
            <span>Users</span>
        </a>
        <div id="collapseUsers" class="collapse" aria-labelledby="headingUsers" data-parent="#accordionSidebar" style="">
            <div class="bg-white py-2 collapse-inner rounded">
                <h3 class="collapse-header">Actions</h3>
                <a class="collapse-item" href="register">Register User</a>
                <a class="collapse-item" href="users">All users</a>
            </div>
        </div>
    </li>
    <!-- Nav Item - Film, Genre and Crew -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#films" data-toggle="collapse" data-target="#collapseFilms" aria-expanded="false" aria-controls="collapseFilms">
            <i class="fas fa-film"></i>
            <span>Films</span>
        </a>
        <div id="collapseFilms" class="collapse" aria-labelledby="headingFilms" data-parent="#accordionSidebar" style="">
            <div class="bg-white py-2 collapse-inner rounded">
                <h3 class="collapse-header">Actions</h3>
                <a class="collapse-item" href="film-new">Add Film</a>
                <a class="collapse-item" href="films">All Film</a>
                <hr class="sidebar-divider d-none d-md-block">
                <h3 class="collapse-header">Genres</h3>
                <a class="collapse-item" href="genre-new">Add Genre</a>
                <a class="collapse-item" href="genres">All Genres</a>
                <hr class="sidebar-divider d-none d-md-block">
                <h3 class="collapse-header">Crew</h3>
                <a class="collapse-item" href="crew-new">Add Crew</a>
                <a class="collapse-item" href="crews">All crew members</a>
                <hr class="sidebar-divider d-none d-md-block">
            </div>
        </div>
    </li>
    <!-- Nav Item - Book and Category -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#books" data-toggle="collapse" data-target="#collapseBooks" aria-expanded="false" aria-controls="collapseBooks">
            <i class="fas fa-book"></i>
            <span>Books</span>
        </a>
        <div id="collapseBooks" class="collapse" aria-labelledby="headingBooks" data-parent="#accordionSidebar" style="">
            <div class="bg-white py-2 collapse-inner rounded">
                <h3 class="collapse-header">Actions</h3>
                <a class="collapse-item" href="book-new">Add Book</a>
                <a class="collapse-item" href="books">All Books</a>
                <h3 class="collapse-header">Category</h3>
                <a class="collapse-item" href="category-new">Add Book Category</a>
                <a class="collapse-item" href="categories">All Categories</a>
                <hr class="sidebar-divider d-none d-md-block">
            </div>
        </div>
    </li>
    <!-- Nav Item - Trailers -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#trailers" data-toggle="collapse" data-target="#collapseTrailers"
           aria-expanded="false" aria-controls="collapseTrailers">
            <i class="fas fa-video"></i>
            <span>Trailers</span>
        </a>
        <div id="collapseTrailers" class="collapse" aria-labelledby="headingTrailers" data-parent="#accordionSidebar" style="">
            <div class="bg-white py-2 collapse-inner rounded">
                <h3 class="collapse-header">Actions</h3>
                <a class="collapse-item" href="trailer-new">Add Trailer</a>
                <a class="collapse-item" href="trailers">All Trailers</a>
            </div>
        </div>
    </li>
    <!-- Nav Item - Short Stories -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#stories" data-toggle="collapse" data-target="#collapseStories"
           aria-expanded="false" aria-controls="collapseStories">
            <i class="fab fa-readme"></i>
            <span>Short Story</span>
        </a>
        <div id="collapseStories" class="collapse" aria-labelledby="headingStories" data-parent="#accordionSidebar" style="">
            <div class="bg-white py-2 collapse-inner rounded">
                <h3 class="collapse-header">Actions</h3>
                <a class="collapse-item" href="story-new">Add Short Story</a>
                <a class="collapse-item" href="short-stories">All Short Stories</a>
            </div>
        </div>
    </li>
    <!-- Nav Item - FAQ -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFAQ"
           aria-expanded="false" aria-controls="collapseFAQ">
            <i class="fas fa-question-circle"></i>
            <span>FAQ</span>
        </a>
        <div id="collapseFAQ" class="collapse" aria-labelledby="headingFAQ"
             data-parent="#accordionSidebar" style="">
            <div class="bg-white py-2 collapse-inner rounded">
                <h3 class="collapse-header">Actions</h3>
                <a class="collapse-item" href="faq-new">Add FAQ</a>
                <a class="collapse-item" href="faqs">All FAQs</a>
            </div>
        </div>
    </li>
    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>

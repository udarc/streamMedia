
<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 5/8/20
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="admin dashboard"/>
<%@include file="../head.jsp" %>

<link rel="stylesheet" href="css/sb-admin-2.min.css">
<link rel="stylesheet" href="css/admin.css">
</head>
<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
    <!-- Sidebar -->
    <%@include file="adminSideBar.jsp" %>
    <!-- End of Sidebar -->
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <!-- Top bar -->
            <%@include file="adminNavbar.jsp" %>
            <!-- End of Top bar -->
            <!-- Begin Page Content -->
            <div class="container-fluid">
                <div class="row">
                    <c:forEach var="entry" items="${streamMediaCounter}">
                    <div class="col-xl-2 col-md-3 mb-4">
                        <div class="card border-left-media shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold media-text text-uppercase mb-1">
                                            <c:out value="${entry.key}"/></div>
                                        <div class="h5 mb-0 font-weight-bold"><c:out value="${entry.value}"/></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <!-- Content Row -->
                <div class="row">
                    <!-- Content Column -->
                    <div class="col-lg-6 mb-4">
                        <!-- Project Card Example -->
                        <div class="card shadow mb-4">
                            <div id="users" class="card-header py-3">
                                <h2 class="m-0 font-weight-bold text-primary">Users</h2>
                            </div>
                            <div class="card-body">
                                <%@include file="includes/users.jsp"%>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 mb-4">
                        <!-- Project Card Example -->
                        <div class="card shadow mb-4">
                            <div id="stories" class="card-header py-3">
                                <h2 class="m-0 font-weight-bold text-primary">SHort Story</h2>
                            </div>
                            <div class="card-body">
                                <%@include file="includes/stories.jsp"%>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content Row -->
                <div class="row">
                    <!-- Content Column -->
                    <div class="col-lg-6 mb-4">
                        <!-- Project Card Example -->
                        <div class="card shadow mb-4">
                            <div id="films" class="card-header py-3">
                                <h2 class="m-0 font-weight-bold text-primary">Films</h2>
                            </div>
                            <div class="card-body">
                                <%@include file="includes/films.jsp"%>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 mb-4">
                    <!-- Project Card Example -->
                    <div class="card shadow mb-4">
                        <div id="Books" class="card-header py-3">
                            <h2 class="m-0 font-weight-bold text-primary">Books</h2>
                        </div>
                        <div class="card-body">
                            <%@include file="includes/books.jsp"%>
                        </div>
                    </div>
                </div>
                </div>
                <!-- Content Row -->
                <div class="row">
                    <div class="col-lg-6 mb-4">
                    <!-- Project Card Example -->
                    <div class="card shadow mb-4">
                        <div id="trailers" class="card-header py-3">
                            <h2 class="m-0 font-weight-bold text-primary">Trailers</h2>
                        </div>
                        <div class="card-body">
                            <%@include file="includes/trailers.jsp"%>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
            <!-- /.container-fluid -->
        <!-- End of Main Content -->
        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                <small>© Copyright <script>const currentDate = new Date();
                document.write(currentDate.getFullYear());</script>2020, Stream Media. All Rights Reserved</small>
            </div>
            </div>
        </footer>
        <!-- End of Footer -->
    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<%@include file="../afterFooter.jsp" %>


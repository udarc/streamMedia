<%--
  Created by IntelliJ IDEA.
  User: Jeanne
  Date: 2/7/20
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Edit User Profile" />
<%@include file="../head.jsp"%>
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container wrapper">
    <div class="form row">
        <form class="card col-md-10 offset-md-1"
        method="post" action="profile-edit" enctype="multipart/form-data">
            <input type="hidden" id="id"
                   name="user"
                   value="${user.username}">
            <div class="form-group row">
                <label for="inputEmail" class="col-md-3 col-form-label">Email</label>
                <div class="col-md-9">
                    <input type="email" class="form-control"
                           name="email" id="inputEmail" placeholder="Email"
                     value="${user.email}">
                </div>
            </div>
            <div class="form-group row">
                <label for="username" class="col-md-3 col-form-label">Username</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="username" id="username" placeholder="Username"
                    value="${user.username}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputFirstName" class="col-md-3 col-form-label">First Name</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="firstName"
                           id="inputFirstName" placeholder="First Name"
                    value="${user.firstName}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputLastName" class="col-md-3 col-form-label">Last Name</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"
                           name="lastName" id="inputLastName" placeholder="Last Name"
                    value="${user.lastName}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputBirthday" class="col-md-3 col-form-label">Birthday</label>
                <div class="col-md-9">
                    <input type="date" class="form-control"
                           name="birthday" id="inputBirthday" placeholder="Date Of Birth"
                    value="${user.birthdate}">
                </div>
            </div>
<%--    TO DO        http://www.instanceofjava.com/2016/08/jstl-if-else-statement-conditions.html--%>
            <fieldset class="form-group">
                <div class="row">
                    <legend class="col-form-label col-md-3 pt-0">Gender</legend>
                    <div class="col-md-9">
                        <div class="form-check">
                            <c:choose>
                                <c:when test="${(user.gender).equalsIgnoreCase('Male')}">
                                <%@include file="includes/checkMale.jsp"%>
                                </c:when>
                                <c:when test="${(user.gender).equalsIgnoreCase('Female')}">
                                    <%@include file="includes/checkFemale.jsp"%>
                                </c:when>
                                <c:otherwise >
                                    <%@include file="includes/checkOther.jsp"%>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </fieldset>
            <div class="form-group row">
                <label for="profilePicture" class="col-md-3 col-form-label">Profile Picture</label>
                <div class="col-md-9">
                    <input type="file" class="form-control-file"
                           name="profilePicture" id="profilePicture"
                   value="${user.picture}">
                </div>
            </div>

            <div class="form-group row">
                <label for="bio" class="col-md-3 col-form-label" >Biography</label>
                <div class="col-md-9">
                <textarea class="form-control" name="biography" id="bio" rows="4">${user.biography}</textarea>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-9">
                    <button type="submit"
                            name="editProfile" class="btn btn-lg btn-success">Edit Profile</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@include file="../footer.jsp"%>
<%@include file="../afterFooter.jsp"%>

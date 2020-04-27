<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/2/20
  Time: 9:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../taglib.jsp"%>
<c:set var="title" value="Logout" />
<%
    session.invalidate();
    response.sendRedirect("streamMedia");
%>
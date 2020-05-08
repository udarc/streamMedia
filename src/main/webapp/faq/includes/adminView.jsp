<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/8/20
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<ul class="list-group list-group-flush">
    <c:forEach var="faq" items="${listFAQ}">
        <li class="list-group-item "><a class="btn-link mr-auto" href="faq-details?uid=<c:out value="${faq.faqId}"/>">${faq.title}</a>
<%--            <c:if test="${pageContext.request.isUserInRole('admin')}">--%>
                        <span class="btn-group ml-auto btn-media-right" role="group" aria-label="Edit and Delete FAQ">
                        <a href="faq-edit?uid=<c:out value="${faq.faqId}"/>" class="btn btn-primary" ><i class="fas fa-edit fa-2x" aria-hidden="true"></i>Edit FAQ</a>
                    <a href="faq-delete?uid=<c:out value="${faq.faqId}"/>" class="btn btn-danger" ><i class="fas fa-trash-alt fa-2x" aria-hidden="true"></i>Delete FAQ</a>

                        </span>
<%--            </c:if>--%>
        </li>
    </c:forEach>
</ul>
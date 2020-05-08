<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/8/20
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="accordion" id="accordionFAQ">
    <c:forEach var="faq" items="${listFAQ}">
    <div class="card">
        <div class="card-header" id="heading-${faq.faqId}">
            <h2 class="card-title">
                <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapse-${faq.faqId}" aria-expanded="false" aria-controls="collapse-${faq.faqId}">
                   ${faq.title}
                </button>
            </h2>
        </div>
        <div id="collapse-${faq.faqId}" class="collapse" aria-labelledby="heading-${faq.faqId}" data-parent="#accordionFAQ">
            <div class="card-body">
                ${faq.description}
            </div>
        </div>
    </div>
    </c:forEach>
</div>

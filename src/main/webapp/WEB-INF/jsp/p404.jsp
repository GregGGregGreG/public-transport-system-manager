<%@include file="../layout/taglib.jsp" %>
<div class="container text-center error-404">
    <div id="hex-time"></div>
    <a href="<spring:url value="/" />">
        <p class="big-title-3d">404 <br>File <br>Not Found</p>

        <h5 class="message"><strong><c:out value="${errorUrl}"/></strong></h5>

        <p><spring:message code="label.404.error.page.text"/></p>
    </a>
</div>


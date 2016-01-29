<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2 class="sub-header">Route list</h2>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="label.user.routes.page.columnNumber"/></th>
            <th><spring:message code="label.user.routes.page.columnCircular"/></th>
            <th><spring:message code="label.user.routes.page.columnPrace"/></th>
            <th><spring:message code="label.user.routes.page.columnRace"/></th>
            <th><spring:message code="label.user.routes.page.columnDuration"/></th>
            <th><spring:message code="label.user.routes.page.columnAvgTransport"/></th>
            <th><spring:message code="label.user.routes.page.columnCountStation"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="route" items="${routes}">
            <tr>
                <td>
                    <c:out value="${route.number}"/>
                </td>
                <td>
                    <c:set var="circular" value="${route.circular}"/>
                    <c:if test="${circular eq true}">
                        Yes
                    </c:if>
                    <c:if test="${circular eq false}">
                        Non
                    </c:if>
                </td>
                <td>
                    <c:out value="${route.price}"/>
                </td>
                <td>
                    <c:out value="${route.races}"/>
                </td>
                <td>
                    <c:out value="${route.duration}"/>
                </td>
                <td>
                    <c:out value="${route.avgTransport}"/>
                </td>
                <td>
                    <c:out value="${route.countStation}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
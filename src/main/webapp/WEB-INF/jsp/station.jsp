<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2 class="sub-header">Station list</h2>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><spring:message code="label.user.stations.page.columnName"/></th>
            <th><spring:message code="label.user.stations.page.columnAvgCountPassenger"/></th>
            <th><spring:message code="label.user.stations.page.columnCountRace"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="station" items="${stations}">
            <tr>
                <td>
                    <c:out value="${station.name}"/>
                </td>
                <td>
                    <c:out value="${station.avgCountPassenger}"/>
                </td>
                <td>
                    <c:out value="${station.countRace}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
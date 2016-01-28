<%@include file="../layout/taglib.jsp" %>

<div class="container">
    <div class="row">
        <div class=" col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="<spring:url value="/find"/>">Find </a></li>
                <li><a href="<spring:url value="/routes"/>">Routes</a></li>
                <li><a href="<spring:url value="/stations"/>">Stations</a></li>
            </ul>
        </div>
        <div class=" col-md-10">
            <tiles:insertAttribute name="data"/>
        </div>
    </div>
</div>
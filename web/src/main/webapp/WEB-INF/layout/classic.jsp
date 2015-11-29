<%@ include file="../layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>TEst</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>
<tiles:insertAttribute name="templateItem" ignore="true"/>
</body>
</html>
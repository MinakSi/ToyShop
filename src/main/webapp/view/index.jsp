<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:if test="${locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
</c:if>
<fmt:bundle basename="prop" prefix="index.">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title><fmt:message key="h"/></title>
</head>
<body>

<h2>
    <fmt:message key="h"/>
</h2>

<form action="${pageContext.request.contextPath}/" method="post">
    <div class="form-group">
        <label>
            <input class="form-control" name="phone" placeholder="<fmt:message key="phone"/>"/>
        </label>
    </div>
    <div class="form-group">
        <label>
            <input class="form-control" name="password" type="password" placeholder="<fmt:message key="password"/>"/>
        </label>
    </div>

    <input type="submit" value="<fmt:message key="h"/>" class="btn btn-secondary"/>
</form>
<form action="${pageContext.request.contextPath}/catalog" method="post">
    <input type="submit" value="<fmt:message key="without_registration"/>" class="btn btn-secondary">
</form>
<form action="${pageContext.request.contextPath}/registration" method="get">
    <input type="submit" value="<fmt:message key="registration"/>" class="btn btn-secondary">
</form>

</body>
</fmt:bundle>
</html>

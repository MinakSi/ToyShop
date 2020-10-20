<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Регистрация</title>
</head>
<body>
<h2>
    Hello World!
    <c:if test="${loggedIn}">
        I am <c:out value="${user.firstName}"/>
    </c:if>
    <c:if test="${!loggedIn}">
        Please log in
    </c:if>
</h2>

<form action="${pageContext.request.contextPath}/" method="post">
    <label>
        <input name="phone" placeholder="phone"/>
    </label>
    <br><br>
    <label>
        <input name="password" type="password" placeholder="password"/>
    </label>
    <br><br>
    <input type="submit" value="Войти" />
</form>
<form action="${pageContext.request.contextPath}/catalog" method="post">
    <input type="submit" value="Войти без регистрации">
</form>

</body>
</html>

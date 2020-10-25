<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Войти</title>
</head>
<body>
<h2>
    Войти
</h2>

<form action="${pageContext.request.contextPath}/" method="post">
    <div class="form-group">
        <label>
            <input class="form-control" name="phone" placeholder="phone"/>
        </label>
    </div>
    <div class="form-group">
        <label>
            <input class="form-control" name="password" type="password" placeholder="password"/>
        </label>
    </div>

    <input type="submit" value="Войти" class="btn btn-secondary"/>
</form>
<form action="${pageContext.request.contextPath}/catalog" method="post">
    <input type="submit" value="Войти без регистрации" class="btn btn-secondary">
</form>
<form action="${pageContext.request.contextPath}/registration" method="get">
    <input type="submit" value="Зарегистрироваться" class="btn btn-secondary">
</form>

</body>
</html>

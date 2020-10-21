<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Регистрация</title>
</head>
<body>
<h2>
    Регистрация
</h2>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label>
        <input name="firstName" placeholder="Name"/>
    </label>
    <br><br>
    <label>
        <input name="secondName" placeholder="Surname"/>
    </label>
    <br><br>
    <label>
        <input name="email" placeholder="e-mail"/>
    </label>
    <br><br>
    <label>
        <input name="phone" placeholder="Phone number"/>
    </label>
    <br><br>
    <label>
        <input name="address" placeholder="Address"/>
    </label>
    <br><br>
    <label>
        <input name="password" type="password" placeholder="password"/>
    </label>
    <br><br>
    <label>
        <input name="checkPass" type="password" placeholder="enter password again"/>
    </label>
    <br><br>
    <input type="submit" value="Зарегистрироваться"/>
</form>

</body>
</html>

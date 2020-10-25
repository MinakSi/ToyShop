<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js" integrity="sha512-WNLxfP/8cVYL9sj8Jnp6et0BkubLP31jhTG9vhL/F5uEZmg5wEzKoXp1kJslzPQWwPT1eyMiSxlKCgzHLOTOTQ==" crossorigin="anonymous"></script>
    <title>Регистрация</title>
    <style type="text/css">
        <%@include file="main.css"%>
    </style>
</head>
<body>
<h2>
    Регистрация
</h2>

<form name="reg_form" action="${pageContext.request.contextPath}/registration" method="post" onsubmit="return validateCheck()">
    <div class="form-group">
        <label>
            <input id="name"
                   name="firstName"
                   placeholder="Name"
                   required
                   pattern="^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                   title="Латиница/кириллица с заглавной буквы"
                   class="form-control required"
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input id="surName"
                   name="secondName"
                   placeholder="Surname"
                   required
                   pattern="^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                   title="Латиница/кириллица с заглавной буквы"
                   class="required form-control"
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input name="email"
                   placeholder="e-mail"
                   pattern=".+@.+\..+|.{0}"
                   title="Заполните почту в формате body@mail.domain или оставьте поле пустым"
                   class="form-control"
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input id="phone"
                   name="phone"
                   placeholder="Phone number"
                   class="required form-control"
                   required
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input id="address"
                   name="address"
                   placeholder="Address"
                   class="required form-control"
                   required
            />
        </label>
    </div>

    <div class="form-group">
        <label>
            <input id="pass1"
                   name="password"
                   type="password"
                   placeholder="password"
                   required
                   class="required form-control"
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input id="pass2"
                   name="checkPass"
                   type="password"
                   placeholder="enter password again"
                   class="required form-control"
                   required
            />
        </label>
    </div>
    <input type="submit" value="Зарегистрироваться" class="btn btn-secondary"/>
</form>
<c:if test="${exception}">
    <span class="badge badge-pill badge-danger">Пользователь с таким номером телефона уже зарегистрирован</span>
</c:if>
<script type="text/javascript">
    <%@include file="js/modules/registration.js"%>
</script>

</body>
</html>

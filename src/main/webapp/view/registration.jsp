<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:if test="${locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
</c:if>
<fmt:bundle basename="prop" prefix="reg.">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js" integrity="sha512-WNLxfP/8cVYL9sj8Jnp6et0BkubLP31jhTG9vhL/F5uEZmg5wEzKoXp1kJslzPQWwPT1eyMiSxlKCgzHLOTOTQ==" crossorigin="anonymous"></script>
    <title><fmt:message key="h"/></title>
    <style type="text/css">
        <%@include file="css/main.css"%>
    </style>
</head>
<body>
<h2>
    <fmt:message key="h"/>
</h2>

<form name="reg_form" action="${pageContext.request.contextPath}/registration" method="post" onsubmit="return validateCheck()">
    <div class="form-group">
        <label>
            <input id="name"
                   name="firstName"
                   placeholder="<fmt:message key="name"/>"
                   required
                   pattern="^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                   title="<fmt:message key="name_title"/>"
                   class="form-control required"
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input id="surName"
                   name="secondName"
                   placeholder="<fmt:message key="surname"/>"
                   required
                   pattern="^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                   title="<fmt:message key="name_title"/>"
                   class="required form-control"
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input name="email"
                   placeholder="e-mail"
                   pattern=".+@.+\..+|.{0}"
                   title="<fmt:message key="mail_title"/>"
                   class="form-control"
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input id="phone"
                   name="phone"
                   placeholder="<fmt:message key="phone"/>"
                   class="required form-control"
                   required
            />
        </label>
    </div>
    <div class="form-group">
        <label>
            <input id="address"
                   name="address"
                   placeholder="<fmt:message key="address"/>"
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
                   placeholder="<fmt:message key="password"/>"
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
                   placeholder="<fmt:message key="repassword"/>"
                   class="required form-control"
                   required
            />
        </label>
    </div>
    <input type="submit" value="<fmt:message key="registrate"/>" class="btn btn-secondary"/>
</form>
<c:if test="${exception}">
    <span class="badge badge-pill badge-danger"> <fmt:message key="user_already_exists"/></span>
</c:if>
<script type="text/javascript">
    <%@include file="js/modules/registration.js"%>
</script>

</body>
</fmt:bundle>
</html>

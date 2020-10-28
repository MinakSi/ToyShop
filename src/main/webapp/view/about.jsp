<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:if test="${locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
</c:if>
<fmt:bundle basename="prop" prefix="about.">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="css/main.css"%>
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js" integrity="sha512-WNLxfP/8cVYL9sj8Jnp6et0BkubLP31jhTG9vhL/F5uEZmg5wEzKoXp1kJslzPQWwPT1eyMiSxlKCgzHLOTOTQ==" crossorigin="anonymous"></script>
    <title><fmt:message key="h"/> | ${product.name}</title>
</head>
<body>
<div class="container-fluid" id="home_pets">
    <nav class="navbar navbar-expand-lg navbar-dark home_nav row">
        <a class="navbar-brand col-1 offset-1" href="#">
            <p class="logtop" style="color:#545454;">ToyShop</p>
            <p class="logbot" style="color:#545454;">Shop at home</p>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" 	aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse col-6 offset-2 justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <form action="${pageContext.request.contextPath}/about" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" name="rus" type="submit" value="Ru"
                               style="color:#545454;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/about" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" name="eng" type="submit" value="Eng"
                               style="color:#545454;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/catalog" method="post">
                    <li class="nav-item">
                        <input class="nav-link" type="submit" value="<fmt:message key="catalog"/>"
                               style="border: none;
                                    outline: none;
                                     background-color: #fafafa;
                                     color:#545454;
                                      cursor: pointer;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" type="submit" value="<fmt:message key="cart"/>"
                               style="color:#545454;">
                    </li>
                </form>
                <c:if test="${!empty(user)}">
                    <form action="${pageContext.request.contextPath}/account" method="post">
                        <li class="nav-item">
                            <input class="nav-link li_button" type="submit" value="<fmt:message key="account"/>"
                                   style="color:#545454;">
                        </li>
                    </form>
                </c:if>
                <c:if test="${empty(user)}">
                    <form action="${pageContext.request.contextPath}/">
                        <li class="nav-item">
                            <input class="nav-link li_button" type="submit" value="<fmt:message key="login"/>"
                                   style="color:#545454;">
                        </li>
                    </form>
                </c:if>
            </ul>
        </div>
    </nav>
</div>

<div class="container-fluid" id="main_pets">
    <div class="container-fluid row justify-content-center main_pets_wrapper">
        <p class="main_pets_h col-4 ">${product.getName()}</p>
    </div>
    <div class="container-fluid row product-wrapper">
        <div class="product-image col-4 inner" style="background-image:url(${pageContext.request.contextPath}/view/img/petroll${product.getId()}.png);
         background-color: #f6f6f6;">
        </div>
    <div class="container row col-6 product-description about_plain_txt">
        <p class="col-12">${product.getDescription()}<br><br>
            <fmt:message key="price"/>: ${product.getPrice()}<fmt:message key="UAH"/><br>
            <fmt:message key="on_storage"/>: ${product.getAmountOnStorage()}
        </p>


        <form class="col-10">
            <input type="button" id="minus" class="col-3 plusminus bttn bttn_gettoknow" value="-"/>
            <span id="product_id" hidden>${product.getId()}</span>
            <span id="amount">${productAmount}</span>
            <input type="button" id="plus" class="col-3 plusminus bttn bttn_gettoknow" value="+"/>
            <input type="button" id="clear" class="col-4 plusminus bttn bttn_gettoknow" value="<fmt:message key="clear"/>"/>
        </form>

        <br>


    </div>
</div>

</div>




<script type="text/javascript">
    <%@include file="js/modules/plusMinusButton.js"%>
</script>

</body>
</fmt:bundle>
</html>

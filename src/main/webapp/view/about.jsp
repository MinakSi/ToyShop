<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="main.css"%>
        <%@include file="slick/slick.css"%>
        <%@include file="slick/slick-theme.css"%>
    </style>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js" integrity="sha512-WNLxfP/8cVYL9sj8Jnp6et0BkubLP31jhTG9vhL/F5uEZmg5wEzKoXp1kJslzPQWwPT1eyMiSxlKCgzHLOTOTQ==" crossorigin="anonymous"></script>
    <title>О товаре | ${product.getName()}</title>
<%--    <script src="js/vendor/jquery.min.js" type="text/javascript"></script>--%>
<%--    <script src="js/vendor/jquery.cookie.js" type="text/javascript"></script>--%>
<%--    <script src="js/vendor/underscore.min.js" type="text/javascript"></script>--%>
<%--    <script src="js/modules/plusMinusButton.js" type="text/javascript"></script>--%>
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
                <form action="${pageContext.request.contextPath}/main" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" type="submit" value="Главная"
                               style="color:#545454;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/catalog" method="post">
                    <li class="nav-item">
                        <input class="nav-link" type="submit" value="Каталог"
                               style="border: none;
                                    outline: none;
                                     background-color: #fafafa;
                                     color:#545454;
                                      cursor: pointer;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" type="submit" value="Корзина"
                               style="color:#545454;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/account" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" type="submit" value="Мой кабинет"
                               style="color:#545454;">
                    </li>
                </form>
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
        <p class="col-12">${product.getDescription()}<br><br>Цена: ${product.getPrice()}грн<br>На складе: ${product.getAmountOnStorage()}
        </p>
        <form class="col-5">
            <input type="button" id="minus" class="col-5 plusminus bttn bttn_gettoknow" value="-"/>
            <span id="amount">0</span>
            <input type="button" id="plus" class="col-5 plusminus bttn bttn_gettoknow" value="+"/>
        </form>

        <br>


        <!-- <a href="cart.php" class="col-5 offset-1"><button class="col bttn bttn_gettoknow">Корзина</button></a> -->

    </div>
</div>

</div>



<%--<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>--%>

<%--<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>--%>

<script type="text/javascript">
    <%@include file="js/modules/plusMinusButton.js"%>
    <%@include file="slick/slick.js"%>
    <%@include file="js/modules/main.js"%>
</script>

<%--<script src="js/modules/plusMinusButton.js"></script>--%>

</body>
</html>

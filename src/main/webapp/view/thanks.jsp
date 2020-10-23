<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="main.css"%>
    </style>
    <title>Спасибо за покупку!</title>
</head>
<body>

<div class="container-fluid" id="home_pets">
    <nav class="navbar navbar-expand-lg navbar-dark home_nav row">
        <a class="navbar-brand col-1 offset-1" href="#">
            <p class="logtop" style="color:#545454;">ToyShop</p>
            <p class="logbot" style="color:#545454;">Shop at home</p>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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
        <c:if test="${exception}">
            <h1 class="main_pets_h col-4">Ошибка заказа. Повторите попытку позже :(</h1>
        </c:if>
        <c:if test="${!exception}">
            <h1 class="main_pets_h col-4">Спасибо за покупку!</h1>
        </c:if>

    </div>
</div>


</div>
<div class="container-fluid" id="footer">
    <div class="container-fluid row footer_wrapper justify-content-left">
        <div class="main_h footer_txt col-3" id="footer_txt1">
            <p class=" footer_h">Наши контакты</p>
            <div class="container-fluid footer_content_block row">
                <div class="footer_content_img" id="envelope"></div>
                <div class="footer_content_line_block">
                    <p class="footer_content_line">toyshop@gmail.com</p>
                </div>
                <div class="col"></div>
                <div class="footer_content_img" id="telephone"></div>
                <div class="footer_content_line_block">
                    <p class="footer_content_line">+13 674 567 75 54</p>
                </div>
            </div>
        </div>
        <div class="main_h footer_txt col-4" id="footer_txt2">
            <!-- <div class="footer_txt2h_wrap"> -->
            <p class="footer_h">Мы находимся</p>
            <!-- </div> -->
            <div class=" container-fluid footer_content_block row">
                <div class="container-fluid row align-items-center">
                    <div class="footer_content_img pin"></div>
                    <div class="footer_content_line_block col-10">
                        <p class="footer_content_line">Boston, Central Street, 1st (Entrance from the store)</p>
                    </div>
                </div>
                <div class="col"></div>
                <div class="footer_content_img pin"></div>
                <div class="footer_content_line_block">
                    <p class="footer_content_line">London, South Park, 18st</p>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>

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
    <title>Заказ №</title>
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
                <c:if test="${!empty(user)}">
                    <form action="${pageContext.request.contextPath}/account" method="post">
                        <li class="nav-item">
                            <input class="nav-link li_button" type="submit" value="Мой кабинет"
                                   style="color:#545454;">
                        </li>
                    </form>
                </c:if>
                <c:if test="${empty(user)}">
                    <form action="${pageContext.request.contextPath}/">
                        <li class="nav-item">
                            <input class="nav-link li_button" type="submit" value="Войти"
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
        <p class="main_pets_h col-4 ">Заказ № ${order_id}</p>
    </div>
    <form action="${pageContext.request.contextPath}/account" method="post">
        <input  class="bttn_gettoknow bttn" type="submit" value="назад">
    </form>
    <table class="table">
        <tr>
            <th>id товара</th>
            <th>Название</th>
            <th>Количество</th>
            <th>Цена</th>
        </tr>
        <c:forEach items="${products}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.amountOnStorage}</td>
                <td>${p.price}</td>
            </tr>
        </c:forEach>
    </table>
    <div class="total">total = ${order_total}</div>

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
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="js/vendor/underscore.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="slick/slick.js"></script>
<script src="js/modules/main.js"></script>

<script type="text/javascript">
    <%@include file="js/modules/registration.js"%>
</script>
</body>
</html>
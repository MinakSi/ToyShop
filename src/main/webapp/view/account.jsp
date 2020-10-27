<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="main.css"%>
    </style>
    <title>Мой кабинет</title>
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
        <p class="main_pets_h col-4 ">Мой кабинет</p>
    </div>
    <form action="${pageContext.request.contextPath}/account" method="post">
        <input  class="bttn_gettoknow bttn" name="logout" type="submit" value="logout">
    </form>
    <table class="table">
        <tr>
            <th>id Заказа</th>
            <th>Дата</th>
            <th>Статус</th>
            <th>Номер накладной</th>
            <th>Сумма</th>
        </tr>
        <c:forEach items="${orders}" var="o">
            <tr>
                <td>${o.id}</td>
                <td>${o.date}</td>
                <td>${o.status.name}</td>
                <td>${o.invoiceNumber}</td>
                <td>${o.total}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/order" method="post">
                        <input name="order_id" type="hidden" value="${o.id}">
                        <input name="order_total" type="hidden" value="${o.total}">
                        <input class="catalog-item-btn bttn_learn bttn" type="submit" value="подробнее"
                               style="color:#545454;">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>

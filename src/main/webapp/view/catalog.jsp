<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="main.css"%>
    </style>
    <title>Каталог</title>
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
                            <input class="nav-link li_button" type="submit" value="Каталог"
                            style="color:#545454;">
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
        <p class="main_pets_h col-4 ">Каталог товаров ${user.firstName}</p>
        <div class="col-9">
            <form action="${pageContext.request.contextPath}/catalog" method="post">
                <label>Сортировка по:
                    <select name="sortBy">
                        <option<c:if test="${sortBy eq 'id'}">
                            selected
                        </c:if>>id</option>
                        <option<c:if test="${sortBy eq 'price up'}">
                            selected
                        </c:if>>price up</option>
                        <option<c:if test="${sortBy eq 'price down'}">
                            selected
                        </c:if>>price down</option>
                        <option<c:if test="${sortBy eq 'name a-z'}">
                            selected
                        </c:if>>name a-z</option>
                        <option<c:if test="${sortBy eq 'name z-a'}">
                            selected
                        </c:if>>name z-a</option>

                    </select>
                </label><br>
                <input name="sort" class="catalog-item-btn bttn_learn bttn col-2"
                       type="submit" value="Сортировать"/> <br><br>
                <input name="-" style="margin-right: 0;"
                       class="catalog-item-btn bttn_learn bttn col-1" type="submit" value="<"/>
                <input name="page" type="hidden" value="${page}"/>
                <span style="margin: 0 50px;">${page}</span>
                <input name="+" class="catalog-item-btn bttn_learn bttn col-1" type="submit" value=">"/>
            </form>
        </div>

    </div>
    <!-- //////////////////////////////////////////////////////// -->
    <div class="container-fluid catalog-list justify-content-around">
        <div class="container-fluid row">
            <c:forEach items="${products}" var="p">
                <div class="catalog-wrapper col-4 container">
                    <div class="container catalog-item col-6 ">

                        <div class="container catalog-item-img inner" style =
                                "background-image: url(${pageContext.request.contextPath}/view/img/petroll${p.getId()}.png); background-color: #f6f6f6;"></div>

                    <div class="container catalog-item-text">
                        <p class="catalog-item-name">${p.getName()}</p>
                    </div>
                        <form action="${pageContext.request.contextPath}/about" method="post">
                            <input name="product_id" type="hidden" value="${p.getId()}">
                            <input class="catalog-item-btn bttn_learn bttn" type="submit" value="подробнее"
                                   style="color:#545454;">
                        </form>
                     </div>

                </div>
            </c:forEach>
        </div>
    </div>
<!-- //////////////////////////////////////////////////////// -->
</div>


</body>
</html>

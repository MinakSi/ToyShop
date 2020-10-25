<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="main.css"%>
        <%@include file="slick/slick.css"%>
        <%@include file="slick/slick-theme.css"%>
    </style>
    <title>Admin main</title>
</head>
<body>

<div class="container-fluid" id="home_pets">
    <nav class="navbar navbar-expand-lg navbar-dark home_nav row">
        <a class="navbar-brand col-1 offset-1" href="#">
            <p class="logtop" style="color:#545454;">ToyShop</p>
            <p class="logbot" style="color:#545454;">admin</p>
        </a>
        <div class="collapse navbar-collapse col-6 offset-2 justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
<%--                <form action="${pageContext.request.contextPath}/admin/main" method="post">--%>
<%--                    <li class="nav-item">--%>
<%--                        <input class="nav-link" name="go to main" type="submit" value="Вернуться"--%>
<%--                               style="border: none;--%>
<%--                                    outline: none;--%>
<%--                                     background-color: #fafafa;--%>
<%--                                     color:#545454;--%>
<%--                                      cursor: pointer;">--%>
<%--                    </li>--%>
<%--                </form>--%>
                <form action="${pageContext.request.contextPath}/admin/main" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" name="logout" type="submit" value="Выйти"
                               style="color:#545454;">
                    </li>
                </form>
            </ul>
        </div>

    </nav>
</div>
<div class="container-fluid" id="main_pets">
    <div class="container-fluid row justify-content-center main_pets_wrapper">
        <p class="main_pets_h col-4 ">Заказы</p>
<%--        <form action="${pageContext.request.contextPath}/admin/main" method="post">--%>
<%--            <input class="bttn_gettoknow bttn" name="logout" type="submit" value="logout">--%>
<%--        </form>--%>
        <table class="table">
            <tr>
                <th>id Заказа</th>
                <th>Дата</th>
                <th>Заказчик</th>
                <th>Телефон</th>
                <th>Статус</th>
                <th>Номер накладной</th>
                <th>Сумма</th>
            </tr>
            <c:forEach items="${orders}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.date}</td>
                    <td>${o.user.firstName} ${o.user.secondName}</td>
                    <td>${o.user.phoneNumber}</td>
                    <td>${o.status.name}</td>
                    <td>${o.invoiceNumber}</td>
                    <td>${o.total}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/order" method="post">
                            <input name="order_id" type="hidden" value="${o.id}">
                            <input name="order_status" type="hidden" value="${o.status.id}">
                            <input name="user_phone" type="hidden" value="${o.user.phoneNumber}">
                            <input name="invoice" type="hidden" value="${o.invoiceNumber}">
                            <input class="catalog-item-btn bttn_learn bttn" type="submit" value="изменить"
                                   style="color:#545454;">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>


    </div>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="js/vendor/underscore.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="slick/slick.js"></script>
<script src="js/modules/main.js"></script>

</body>
</html>


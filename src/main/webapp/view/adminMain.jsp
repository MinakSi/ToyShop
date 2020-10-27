<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="main.css"%>
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
                <form action="${pageContext.request.contextPath}/admin/add" method="post">
                    <li class="nav-item">
                        <input class="nav-link" name="go to main" type="submit" value="Добавить товар"
                               style="border: none;
                                    outline: none;
                                     background-color: #fafafa;
                                     color:#545454;
                                      cursor: pointer;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/admin/logout" method="get">
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

</body>
</html>


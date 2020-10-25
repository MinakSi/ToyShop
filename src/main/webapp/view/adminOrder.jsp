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
    <title>Изменить | Заказ № ${order.id}</title>
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
                <form action="${pageContext.request.contextPath}/admin/main" method="post">
                    <li class="nav-item">
                        <input class="nav-link" name="go to main" type="submit" value="Вернуться"
                               style="border: none;
                                    outline: none;
                                     background-color: #fafafa;
                                     color:#545454;
                                      cursor: pointer;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/admin/main" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" name="logout" type="submit" value="Выйти"
                               style="color:#545454;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/admin/order" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" name="blockUser" type="submit" value="заблокировать пользователя"
                               style="color:#545454;">
                    </li>
                </form>
            </ul>
        </div>

    </nav>
</div>
<div class="container-fluid" id="main_pets">
    <div class="container-fluid row justify-content-center main_pets_wrapper">
        <p class="main_pets_h col-4 ">Заказ № ${order.id}</p>

        <form action="${pageContext.request.contextPath}/admin/order" method="post">
            <c:if test="${checking}">
                QWERTYUIOP[
            </c:if>
            <span>Id клиента: ${client.id}</span><br>
            <span>Имя: ${client.firstName}</span><br>
            <span>Фамилия: ${client.secondName}</span><br>
            <span>Телефон: ${client.phoneNumber}</span><br>
            <span>Адрес: ${client.address}</span><br>

            <label> Статус:
                <select name="order_status_name">
                    <c:if test="${order.status.id==1}">
                        <option selected>new</option>
                        <option>in progress</option>
                        <option>rejected</option>
                    </c:if>
                    <c:if test="${order.status.id==2}">
                        <option selected>in progress</option>
                        <option>completed</option>
                    </c:if>
                    <c:if test="${order.status.id==3}">
                        <option title="Невозможно изменить статус с такого положения"
                                selected disabled>completed</option>
                    </c:if>
                    <c:if test="${order.status.id==4}">
                        <option title="Невозможно изменить статус с такого положения"
                                selected disabled>rejected</option>
                    </c:if>
                </select>
            </label><br>
            <c:if test="${empty(order.invoiceNumber)}">
                <input name="invoiceInput" placeholder="invoice number" pattern="^\d{14}$">
            </c:if>
            <c:if test="${!empty(order.invoiceNumber)}">
                <span>Номер накладной: ${order.invoiceNumber}</span>
            </c:if>
            <br>
<%--            <input name="invoice" value="${invoice}" type="hidden">--%>
            <input name="user_phone" value="${client.phoneNumber}" type="hidden">
<%--            <input name="order_status" value="${order_status}" type="hidden">--%>
            <input name="order_id" value="${order.id}" type="hidden">
            <c:if test="${empty(order.invoiceNumber) || order.status.id<3}">
                <input type="submit" value="Подтвердить" name="commitSaves">
            </c:if>

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


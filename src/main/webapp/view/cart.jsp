<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:if test="${locale eq 'ru'}">
    <fmt:setLocale value="ru"/>
</c:if>
<fmt:bundle basename="prop" prefix="cart.">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="css/main.css"%>
    </style>
    <title><fmt:message key="h"/></title>
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
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <li class="nav-item">
                        <input class="nav-link li_button" name="rus" type="submit" value="Ru"
                               style="color:#545454;">
                    </li>
                </form>
                <form action="${pageContext.request.contextPath}/cart" method="post">
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
        <c:if test="${empty(products)}">
            <p class="main_pets_h col-4 "><fmt:message key="cart_is_empty"/></p>
        </c:if>
        <c:if test="${!empty(products)}">
            <p class="main_pets_h col-4 "><fmt:message key="h"/></p>
        </c:if>
    </div>
    <c:if test="${!empty(products)}">

        <table class="table">
            <tr>
                <th><fmt:message key="vendor"/></th>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="price"/></th>
                <th><fmt:message key="amount"/></th>
                <th><fmt:message key="sum"/></th>
            </tr>
            <c:forEach items="${products}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${cartSession.get(p.id)}</td>
                    <c:set var="sum" value="${p.getPrice()*cartSession.get(p.id)}"/>
                    <c:set var="total" value="${total= total + sum}"/>
                    <td>${sum}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/about" method="post">
                            <input name="product_id" type="hidden" value="${p.id}">
                            <input class="catalog-item-btn bttn_learn bttn" type="submit" value="<fmt:message key="change"/>"
                                   style="color:#545454;">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="total"><fmt:message key="total"/> = ${total}</div>
        <c:if test="${!empty(user)}">
            <form action="${pageContext.request.contextPath}/thanks" method="post">
                <input class="bttn bttn_gettoknow" type="submit" value="<fmt:message key="order"/>">
            </form>
        </c:if>

    </c:if>


</div>


</body>
</fmt:bundle>
</html>

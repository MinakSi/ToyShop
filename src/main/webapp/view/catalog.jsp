<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Каталог</title>
</head>
<body>
<h1>Каталог товаров</h1>
<c:if test="${!full}">Каталог пуст ${full}</c:if>
<c:if test="${full}">

    <table>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>Amount on storage</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${products}" var="p">
            <tr>
                <td>${p.getId()}</td>
                <td>${p.getName()}</td>
                <td>${p.getAmountOnStorage()}</td>
                <td>${p.getPrice()}</td>
            </tr>
        </c:forEach>
    </table>




</c:if>


</body>
</html>

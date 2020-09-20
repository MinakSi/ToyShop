<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World! I am <p><c:out value="${key}"/></p></h2>
<%--<c:forEach var="i" items="${key}">--%>

<%--    <p><c:out value="${i}"/></p>--%>

<%--</c:forEach>--%>
<form method="post" action="<c:url value='/'/>">
    <input type="text" hidden name="name" value="customer"/>
    <input type="submit" value="Я клиент">
</form>
<br>
<form method="post" action="<c:url value='/'/>">
    <input type="text" hidden name="name" value="admin"/>
    <input type="submit" value="Я администратор">
</form>
</body>
</html>

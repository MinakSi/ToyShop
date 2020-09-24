<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World! I am <c:out value="${user}"/></h2>
<%--<c:forEach var="i" items="${key}">--%>

<%--    <p><c:out value="user = ${user}"/></p>--%>

<%--</c:forEach>--%>
<%--<sql:query var="rs" dataSource="jdbc/toyshop_db">--%>
<%--    select id, first_name from user--%>
<%--</sql:query>--%>
<%--<c:forEach var="row" items="${rs.rows}">--%>
<%--    ${row.id}<br/>--%>
<%--    ${row.first_name}<br/>--%>
<%--</c:forEach>--%>

<form action="<c:url value='/'/>" method="post">
    phone:
    <label>
        <input name="phone" />
    </label>
    <br><br>
    <input type="submit" value="Submit" />
</form>
<%--<p><c:out value="user = ${user}"/></p>--%>
<%--<form method="post" action="<c:url value='/'/>">--%>
<%--    <input type="text" hidden name="name" value="customer"/>--%>
<%--    <input type="submit" value="Я клиент">--%>
<%--</form>--%>
<%--<br>--%>
<%--<form method="post" action="<c:url value='/'/>">--%>
<%--    <input type="text" hidden name="name" value="admin"/>--%>
<%--    <input type="submit" value="Я администратор">--%>
<%--</form>--%>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add product admin</title>
</head>
<body>
<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/add" method="post">
    <label>
        <input name="name" placeholder="name" required/>
    </label>
    <label>
        <input name="price" placeholder="price" required/>
    </label>
    <label>
        <input name="amount" placeholder="amount on storage" required/>
    </label>
    <label>
        <input name="description" placeholder="description" type="text"/>
    </label>
    <input name="file" type="file" required/>
    <input type="submit" value="submit" name="createProduct">

</form>

</body>
</html>

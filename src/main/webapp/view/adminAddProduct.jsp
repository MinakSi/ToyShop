<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="main.css"%>
    </style>
    <title>Admin add product</title>
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
        <p class="main_pets_h col-4 ">Добавить товар</p>
        <div class="col-12">
            <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/add" method="post">
                <div class="form-group">
                    <label>
                        <input name="name" class="form-control" placeholder="name" required/>
                    </label>
                </div>

                <div class="form-group">
                    <label>
                        <input name="price" class="form-control" placeholder="price" required/>
                    </label>
                </div>

                <div class="form-group">
                    <label>
                        <input name="amount" class="form-control" placeholder="amount on storage" required/>
                    </label>
                </div>

                <div class="form-group">
                    <label>
                        <input name="description" class="form-control" placeholder="description" type="text"/>
                    </label>
                </div>

                <div class="form-group">
                    <input name="file" class="form-control-file" type="file" required/>
                </div>
                <input type="submit" class="catalog-item-btn bttn_learn bttn col-2"
                       value="submit" name="createProduct">

            </form>

        </div>


    </div>
</div>

</body>
</html>


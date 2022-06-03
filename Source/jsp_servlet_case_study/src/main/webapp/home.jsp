<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 5/25/2022
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
    a{
        text-decoration: none;
        color: black;
    }
</style>
</head>
<body class="container-fluid">
<header class="border">
    <%@include file="header.jsp"%>
</header>
<nav class="border">
  <%@include file="navBar.jsp"%>
</nav>
<main>
    <div class="row border h-75">
       <%@include file="sideBar.jsp"%>
        <div class="col-md-9 border">
            ...
        </div>
    </div>
</main>
<footer class="border">
    <%@include file="footer.jsp"%>
</footer>
</body>
</html>

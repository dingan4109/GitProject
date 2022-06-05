<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 6/1/2022
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Side Bar</title>
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
<body>
<div class="col-md-3">
    <ul class="list-unstyled">
        <li><a href="/customerServlet?action=viewCustomerTypes">Customer Type</a></li>
        <li><a href="/serviceServlet?action=viewRentTypes">Rent Type</a></li>
        <li><a href="/serviceServlet?action=viewServiceTypes">Service Type</a></li>
        <li><a href="/employeeServlet?action=user">User</a></li>
    </ul>
</div>
</body>
</html>
